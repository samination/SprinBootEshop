package com.samination.orderservice.Repository;

import com.samination.orderservice.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
