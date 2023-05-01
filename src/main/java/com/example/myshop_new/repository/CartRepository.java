package com.example.myshop_new.repository;

import com.example.myshop_new.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
