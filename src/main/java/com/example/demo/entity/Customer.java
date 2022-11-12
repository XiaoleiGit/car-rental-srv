package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerId;
    private String name;
    private String idCardNum;
    private String mobile;
    private String email;
}
