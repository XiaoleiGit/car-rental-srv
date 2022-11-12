package com.example.demo.service;

import com.example.demo.controller.response.CarListResponse;
import com.example.demo.entity.CarInfo;
import com.example.demo.repository.CarInfoRepository;
import com.example.demo.repository.OrderHistoryRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    public List<CarListResponse> getCars(String city, LocalDateTime start, LocalDateTime end) {
        List<CarInfo> carList = orderHistoryRepository.findCars(city, start, end);
        List<CarListResponse> response = new ArrayList<>();
        HashMap<String, List<CarInfo>> map = new HashMap<>();
        for (CarInfo car : carList) {
            String storeId = car.getStoreId();
            List<CarInfo> list = map.getOrDefault(storeId, new ArrayList<>());
            list.add(car);
            map.put(storeId, list);
        }
        for (Map.Entry<String, List<CarInfo>> entry : map.entrySet()) {
            CarListResponse item = CarListResponse.builder()
                    .storeName(entry.getKey()).cars(entry.getValue()).build();
            response.add(item);
        }
        return response;
    }
}
