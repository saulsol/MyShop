package com.example.myshop_new.repository;

import com.example.myshop_new.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {



}
