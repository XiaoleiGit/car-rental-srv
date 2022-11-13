package com.example.demo.controller;

import com.example.demo.controller.request.OrderCreateRequest;
import com.example.demo.controller.response.CarListPerStore;
import com.example.demo.controller.response.CustomerRentalHistory;
import com.example.demo.entity.OrderHistory;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.CarInfoService;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CarRentalController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private CarInfoService carInfoService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/car-rental/cities")
    public List<String> getCities() {
        return storeService.getCities();
    }

    // http://localhost:80/car-rental/cities/Shenzhen/stores/-/cars?startTime=2022-11-01T00:00:00&endTime=2022-11-11T00:00:00
    @GetMapping(path = "/car-rental/cities/{city}/stores/-/cars")
    public List<CarListPerStore> getAvailableCars(@PathVariable String city, @RequestParam String startTime, @RequestParam String endTime) {
        return orderHistoryService.getCarsByCityAndTime(city, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }

    // http://localhost:80/customers/1000000001/order/history
    @GetMapping(path = "/customers/{customerId}/order/history")
    public List<CustomerRentalHistory> getCustomerRentalHistory(@PathVariable String customerId) {
        return orderHistoryService.getCustomerRentalHistory(customerId);
    }

    // http://localhost:80/customers/1000000001/order/create
    @PostMapping(path = "/customers/{customerId}/order/create")
    public boolean rentalOrderCreate(@PathVariable String customerId, @RequestBody OrderCreateRequest request) {
        return orderHistoryService.createOrder(customerId, request.getCarId(), LocalDateTime.parse(request.getStartTime()), LocalDateTime.parse(request.getEndTime()));
    }

    // http://localhost:80/customers/1000000001/order/cancel
    @PostMapping(path = "/customers/{customerId}/order/cancel")
    public boolean rentalOrderCancel(@RequestParam String orderId) {
        return orderHistoryService.cancelOrder(orderId);
    }

    // http://localhost:80/customers/1000000001/order/detail?orderId=3556cc4149154b85938edc0f35523170
    @GetMapping(path = "/customers/{customerId}/order/detail")
    public OrderHistory rentalOrderDetail(@RequestParam String orderId) {
        return orderHistoryService.getOrder(orderId);
    }
}
