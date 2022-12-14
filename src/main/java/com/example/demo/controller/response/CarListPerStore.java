package com.example.demo.controller.response;

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
    private String storeId;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private List<CarRentalDetail> cars;
}
