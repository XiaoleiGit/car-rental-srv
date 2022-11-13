package com.example.demo.repository;

import com.example.demo.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    @Query(value =
            "select ci.car_id as carId, ci.store_id as storeId, s.name as storeName, ci.price_per_day as pricePerDay," +
            " ci.currency as currency, ci.type as type \n" +
            " from car_info ci\n" +
            " left join store s on ci.store_id = s.store_id\n" +
            " where s.city = ?1\n" +
            " and ci.car_id not in (\n" +
            "   select distinct(car_id) from order_history\n" +
            "   where status = 'ON'\n" +
            "   and (book_start_time between ?2 and ?3\n" +
            "       or book_end_time between ?2 and ?3)\n" +
            " )", nativeQuery = true)
    List<Map<String, Object>> findAvailableCars(String city, LocalDateTime start, LocalDateTime end);

    List<OrderHistory> findByCustomerIdOrderByBookStartTimeDesc(String customerId);

    OrderHistory findByOrderId(String orderId);
}


