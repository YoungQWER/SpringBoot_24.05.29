package com.livecommerce1.entity;

import com.livecommerce1.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;     // 제품 ID

    @Column(nullable = false, length = 50)
    private String productName;     // 제품명

    @Lob
    @Column(nullable = false)
    private String description;     // 제품 설명

    @Column(name = "price", nullable = false)
    private int price;      // 제품 가격

    @Column(nullable = false)
    private  int stockNumber;   // 제품 수량

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;      // 판매 상태
}
