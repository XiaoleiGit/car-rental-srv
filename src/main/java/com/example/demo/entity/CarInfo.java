package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "car_info")
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String carId;
    private String storeId;
    private BigDecimal pricePerDay;
    private String currency;
}
