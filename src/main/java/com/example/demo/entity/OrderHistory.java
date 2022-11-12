package com.example.demo.entity;

import com.example.demo.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderId;
    private String customerId;
    private String carId;
    private LocalDateTime bookStartTime;
    private LocalDateTime bookEndTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualEndTime;
    private OrderStatus status;
}
