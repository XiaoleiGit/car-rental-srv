package com.example.demo.service;

import com.example.demo.controller.response.CarListPerStore;
import com.example.demo.controller.response.CustomerRentalHistory;
import com.example.demo.data.MockData;
import com.example.demo.repository.OrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderHistoryServiceTest {

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @Test
    public void shouldGetCarsByCityAndTime() {
        when(orderHistoryRepository.findAvailableCars(any(), any(), any())).thenReturn(MockData.carList());
        List<CarListPerStore> response = orderHistoryService.getCarsByCityAndTime("Shenzhen", LocalDateTime.now(), LocalDateTime.now());

        assertEquals(2, response.size()); // number of store
        assertEquals(1, response.get(0).getCars().size());
        assertEquals(1, response.get(1).getCars().size());
    }

    @Test
    public void shouldGetCustomerRentalHistory() {
        when(orderHistoryRepository.findByCustomerIdOrderByBookStartTimeDesc(any())).thenReturn(MockData.orderHistoryList());
        List<CustomerRentalHistory> response = orderHistoryService.getCustomerRentalHistory("customerId");

        assertEquals(1, response.size()); // number of store
        assertEquals(true, response.get(0).isCanCancel());
    }

}
