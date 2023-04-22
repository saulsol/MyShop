package com.example.myshop_new.entity;

import com.example.myshop_new.constant.ItemSellStatus;
import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "item")
public class Item {

    @Id @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Setter
    private LocalDateTime regTime; // 상품 등록시간

    @Setter
    private LocalDateTime updateTime; // 상품 수정 시간

    @Builder
    public Item(String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }
}
