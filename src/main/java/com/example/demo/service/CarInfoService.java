package com.example.demo.service;

import com.example.demo.entity.CarInfo;
import com.example.demo.repository.CarInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarInfoService {

    @Autowired
    private CarInfoRepository carInfoRepository;

    public int getCarsByType(String type) {
        List<CarInfo> list = carInfoRepository.findByType(type);
        return list.size();
    }
}
