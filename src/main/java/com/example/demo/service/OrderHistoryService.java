package com.example.demo.service;

import com.example.demo.constant.OrderStatus;
import com.example.demo.controller.response.CarListPerStore;
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

    public List<CarListPerStore> getCars(String city, LocalDateTime start, LocalDateTime end) {
        log.info("Start retrieving available cars from DB, city {}, startTime {}, endTime {}", city, start, end);
        List<Map<String, Object>> carList = orderHistoryRepository.findCars(city, start, end);
        log.info("Retrieved available cars from DB, size: {}", carList.size());
        List<CarListPerStore> response = new ArrayList<>();
        HashMap<String, CarListPerStore> map = new HashMap<>();
        for (Map<String, Object> car : carList) {
            String storeId = car.get("storeId").toString();
            CarListPerStore item = map.getOrDefault(storeId, new CarListPerStore());
            item.setStoreId(car.get("storeId").toString());
            item.setStoreName(car.get("storeName").toString());
            List<CarInfo> list = item.getCars() == null ? new ArrayList<>() : item.getCars();
            list.add(buildCarInfo(car));
            item.setCars(list);
            map.put(storeId, item);
        }
        log.info("CarListPerStore response is built, size: {}", map.size());
        return new ArrayList<>(map.values());
    }

    public CarInfo buildCarInfo(Map<String, Object> car) {
        return CarInfo.builder()
                .carId(car.get("carId").toString())
                .type(car.get("type").toString())
                .pricePerDay(new BigDecimal(car.get("pricePerDay").toString()))
                .currency(car.get("currency").toString())
                .build();
    }

    public List<CustomerRentalHistory> getCustomerRentalHistory(String customerId) {
        log.info("Start retrieving rental history, customerId: {}", customerId);
        List<OrderHistory> orderHistoryListList = orderHistoryRepository.findByCustomerId(customerId);
        List<CustomerRentalHistory> response = new ArrayList<>();
        for (OrderHistory orderHistory : orderHistoryListList) {
            CustomerRentalHistory item = CustomerRentalHistory.builder()
                    .orderId(orderHistory.getOrderId())
                    .carId(orderHistory.getCarId())
                    .bookStartTime(orderHistory.getBookStartTime())
                    .bookEndTime(orderHistory.getBookEndTime())
                    .actualStartTime(orderHistory.getActualStartTime())
                    .actualEndTime(orderHistory.getActualEndTime())
                    .status(orderHistory.getStatus())
                    .canCancel(orderHistory.getStatus().equals(OrderStatus.ON) && LocalDateTime.now().compareTo(orderHistory.getBookStartTime()) < 0)
                    .build();
            response.add(item);
        }
        log.info("CustomerRentalHistory is built, customerId {}, size {}", customerId, response.size());
        return response;
    }
}
