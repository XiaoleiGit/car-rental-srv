package com.example.demo.controller;

import com.example.demo.entity.CarInfo;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CarRentalController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(path = "/test")
    public int test() {
        return bookingService.getCarsByType("BMW 650");
    }

    @GetMapping(path = "/cities")
    public List<String> getCities() {
        return bookingService.getCities();
    }

    @GetMapping(path = "/cars")
    public List<CarInfo> getAvailableCars(@RequestParam String city, @RequestParam String startTime, @RequestParam String endTime) {
        return bookingService.getCars(city, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }
}
