package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String storeId;
    private String name;
    private String phone;
    private String city;
    private String address;
}
