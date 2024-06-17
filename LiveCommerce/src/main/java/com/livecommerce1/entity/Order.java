package com.livecommerce1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@ToString
@Getter
@Setter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;     // 주문 ID

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member; // 주문한 사용자의 ID

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            ,orphanRemoval = true, fetch = FetchType.LAZY)  ////외래키 설정 하지않는다.
    private List<OrderProduct> products = new ArrayList<>(); // 주문한 제품의 ID

    private int quantity; // 주문 수량

    private String shippingAddress; // 배송 주소

    private LocalDateTime orderDate; // 주문 일자

}
