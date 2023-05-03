package com.example.myshop_new.entity;

import com.example.myshop_new.constant.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    // 양방향 연결관계 설정
    // 영속성 전이 설정 -> 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이
    // 고아 엔티티 삭제 옵션

    private LocalDateTime regTime; // 주문 등록 시간

    private LocalDateTime updateTime; // 주문 수정 시간

    // 연관관계 편의 메소드 추가해야 함

}
