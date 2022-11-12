package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "car_info")
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String carId;
    private String storeId;
}
