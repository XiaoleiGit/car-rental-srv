package com.example.demo.controller.response;

import com.example.demo.constant.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CustomerRentalHistory {
    private String orderId;
    private String carId;
    private LocalDateTime bookStartTime;
    private LocalDateTime bookEndTime;
    private LocalDateTime actualStartTime;
    private LocalDateTime actualEndTime;
    private OrderStatus status;
    private boolean canCancel;
}
