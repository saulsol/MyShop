package com.example.myshop_new.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id") // OneToOne 관계도 FK를 하나만 갖는다.
    private Member member;
}
