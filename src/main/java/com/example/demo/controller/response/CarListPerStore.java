package com.example.demo.controller.response;

import com.example.demo.entity.CarInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CarListPerStore {
    String storeId;
    String storeName;
    List<CarInfo> cars;
}
