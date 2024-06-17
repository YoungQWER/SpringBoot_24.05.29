package com.livecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 장바구니 ID

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user; // 사용자 ID

}
