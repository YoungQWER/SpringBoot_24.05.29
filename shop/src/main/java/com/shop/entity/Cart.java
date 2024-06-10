package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본 키의 값을 데이터베이스가 자동으로 생성하도록 지정합니다
        private Long id;    //상품코드

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
