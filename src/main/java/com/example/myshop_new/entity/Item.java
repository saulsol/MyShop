package com.example.myshop_new.entity;

import com.example.myshop_new.constant.ItemSellStatus;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "item")
public class Item {

    @Id @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // item 기본 키

    @Column(nullable = false, length = 50)
    private String itemName; // item 이름

    @Column(name = "price", nullable = false)
    private int price; // 아이템 가격

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Lob @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING) // DB에 varchar 타입으로 저장됨
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    private LocalDateTime regTime; // 상품 등록시간

    private LocalDateTime updateTime; // 상품 수정 시간
    




}
