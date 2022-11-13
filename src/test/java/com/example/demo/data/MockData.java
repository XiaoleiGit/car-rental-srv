package com.example.demo.data;

import com.example.demo.entity.OrderHistory;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockData {

    public static List<Map<String, Object>> carList() {
        List<Map<String, Object>> cars = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("carId", "car001");
        map1.put("storeId", "store001");
        map1.put("storeName", "Car rental company");
        map1.put("pricePerDay", BigDecimal.valueOf(123));
        map1.put("currency", "CNY");
        map1.put("type", "Toyota Camry");
        cars.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("carId", "car002");
        map2.put("storeId", "store002");
        map2.put("storeName", "Car rental company");
        map2.put("pricePerDay", BigDecimal.valueOf(220));
        map2.put("currency", "CNY");
        map2.put("type", "Toyota Camry");
        cars.add(map2);
        return cars;
    }

    public static List<OrderHistory> orderHistoryList() {
        OrderHistory order = OrderHistory.builder().orderId("124567890").carId("car001")
                .customerId("customer001").status("ON").bookStartTime(LocalDateTime.of(2022,11,11,12,0,0,0))
                .bookEndTime(LocalDateTime.now()).build();
        List<OrderHistory> list = new ArrayList<>();
        list.add(order);
        return list;
    }
}
