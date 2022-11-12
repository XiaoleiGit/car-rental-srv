package com.example.demo.repository;

import com.example.demo.entity.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {

    List<CarInfo> findByType(String type);
}
