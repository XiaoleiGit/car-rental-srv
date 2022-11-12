package com.example.demo.service;

import com.example.demo.entity.CarInfo;
import com.example.demo.repository.CarInfoRepository;
import com.example.demo.repository.OrderHistoryRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private CarInfoRepository carInfoRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public List<String> getCities() {
        return storeRepository.getCities();
    }

    public int getCarsByType(String type) {
        List<CarInfo> list = carInfoRepository.findByType(type);
        return list.size();
    }

    public List<CarInfo> getCars(String city, LocalDateTime start, LocalDateTime end) {
        List<CarInfo> list = orderHistoryRepository.findCars(city, start, end);
        return list;
    }
}
