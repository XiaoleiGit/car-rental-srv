package com.example.demo.service;

import com.example.demo.Exception.OrderException;
import com.example.demo.constant.OrderStatus;
import com.example.demo.controller.response.CarListPerStore;
import com.example.demo.controller.response.CarRentalDetail;
import com.example.demo.controller.response.CustomerRentalHistory;
import com.example.demo.entity.CarInfo;
import com.example.demo.entity.OrderHistory;
import com.example.demo.repository.OrderHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class OrderHistoryService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public List<CarListPerStore> getCarsByCityAndTime(String city, LocalDateTime start, LocalDateTime end) {
        log.info("Start retrieving available cars from DB, city {}, startTime {}, endTime {}", city, start, end);
        List<Map<String, Object>> carList = orderHistoryRepository.findAvailableCars(city, start, end);
        log.info("Retrieved available cars from DB, size: {}", carList.size());
        HashMap<String, CarListPerStore> map = new HashMap<>();
        for (Map<String, Object> car : carList) {
            String storeId = car.get("storeId").toString();
            CarListPerStore item = map.getOrDefault(storeId, new CarListPerStore());
            item.setStoreId(car.get("storeId").toString());
            item.setStoreName(car.get("storeName").toString());
            item.setStoreAddress(car.get("storeAddress").toString());
            item.setStorePhone(car.get("storePhone").toString());
            List<CarRentalDetail> list = item.getCars() == null ? new ArrayList<>() : item.getCars();
            list.add(buildCarRentalDetail(car));
            item.setCars(list);
            map.put(storeId, item);
        }
        log.info("CarListPerStore response is built, size: {}", map.size());
        return new ArrayList<>(map.values());
    }

    public CarRentalDetail buildCarRentalDetail(Map<String, Object> car) {
        return CarRentalDetail.builder()
                .carId(car.get("carId").toString())
                .type(car.get("type").toString())
                .pricePerDay(new BigDecimal(car.get("pricePerDay").toString()))
                .currency(car.get("currency").toString())
                .build();
    }

    public List<CustomerRentalHistory> getCustomerRentalHistory(String customerId) {
        log.info("Start retrieving rental history, customerId: {}", customerId);
        List<OrderHistory> orderHistoryListList = orderHistoryRepository.findByCustomerIdOrderByBookStartTimeDesc(customerId);
        List<CustomerRentalHistory> response = new ArrayList<>();
        for (OrderHistory orderHistory : orderHistoryListList) {
            CustomerRentalHistory item = CustomerRentalHistory.builder()
                    .orderId(orderHistory.getOrderId())
                    .carId(orderHistory.getCarId())
                    .bookStartTime(orderHistory.getBookStartTime())
                    .bookEndTime(orderHistory.getBookEndTime())
                    .actualStartTime(orderHistory.getActualStartTime())
                    .actualEndTime(orderHistory.getActualEndTime())
                    .status(OrderStatus.valueOf(orderHistory.getStatus()))
                    .canCancel(orderHistory.getStatus().equals(OrderStatus.ON.toString())
                            && orderHistory.getActualStartTime() == null)
                    .build();
            response.add(item);
        }
        log.info("CustomerRentalHistory is built, customerId {}, size {}", customerId, response.size());
        return response;
    }

    public String createOrder(String customerId, String carId, LocalDateTime startTime, LocalDateTime endTime) {
        if (LocalDateTime.now().toLocalDate().plusDays(1).isAfter(endTime.toLocalDate())) {
            throw new OrderException("The book end date must be at least one day after today");
        }
        log.info("Start creating new rental order for customer {}", customerId);
        OrderHistory newOrder = new OrderHistory();
        String orderId = UUID.randomUUID().toString().replace("-","");
        newOrder.setOrderId(orderId);
        newOrder.setCustomerId(customerId);
        newOrder.setCarId(carId);
        newOrder.setBookStartTime(startTime);
        newOrder.setBookEndTime(endTime);
        newOrder.setStatus(OrderStatus.ON.toString());
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setCreatedBy("system");
        newOrder.setUpdatedAt(LocalDateTime.now());
        newOrder.setUpdatedBy("system");
        orderHistoryRepository.save(newOrder);
        log.info("New rental order created. CustomerId: {}, CarId: {}", customerId, carId);
        return orderId;
    }

    public boolean cancelOrder(String orderId) {
        OrderHistory order = orderHistoryRepository.findByOrderId(orderId);
        order.setStatus(OrderStatus.OFF.toString());
        order.setUpdatedAt(LocalDateTime.now());
        orderHistoryRepository.save(order);
        log.info("Order is cancelled. OrderId: {}", orderId);
        return true;
    }

    public OrderHistory getOrder(String orderId) {
        return orderHistoryRepository.findByOrderId(orderId);
    }
}
