package com.example.myshop_new.repository;

import com.example.myshop_new.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByPriceLessThan(Integer price);


    @Query(value = "SELECT i FROM Item i WHERE i.itemDetail LIKE %:itemDetail% ORDER BY i.price DESC ")
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

    // nativeQuery 옵션을 사용하면 실제 쿼리문 작성하듯이 작성을 해야함
    // 달지 않는 경우 객체지향적인 JPQL 사용
}
