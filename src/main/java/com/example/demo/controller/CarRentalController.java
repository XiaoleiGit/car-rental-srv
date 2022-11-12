package com.example.demo.controller;

import com.example.demo.controller.response.CarListPerStore;
import com.example.demo.controller.response.CustomerRentalHistory;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.CarInfoService;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/test")
    public int test() {
        return carInfoService.getCarsByType("BMW 650");
    }

    @GetMapping(path = "/cities")
    public List<String> getCities() {
        return storeService.getCities();
    }

    // http://localhost:8080/cars?city=Shenzhen&startTime=2022-11-01T00:00:00&endTime=2022-11-11T00:00:00
    @GetMapping(path = "/cars")
    public List<CarListPerStore> getAvailableCars(@RequestParam String city, @RequestParam String startTime, @RequestParam String endTime) {
        return orderHistoryService.getCars(city, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }

    // http://localhost:8080/customers/1000000001/rental-history
    @GetMapping(path = "/customers/{customerId}/rental-history")
    public List<CustomerRentalHistory> getCustomerRentalHistory(@PathVariable String customerId) {
        return orderHistoryService.getCustomerRentalHistory(customerId);
    }
}
