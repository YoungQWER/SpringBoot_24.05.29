package com.livecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "order")
@ToString
@Getter
@Setter
public class Order {

    @Id

    private int id; // 주문 ID
    private int userID; // 주문한 사용자의 ID
    private int productID; // 주문한 제품의 ID
    private int quantity; // 주문 수량
    private String shippingAddress; // 배송 주소
    private String shippingPostalCode; // 배송 우편번호
    private Timestamp orderDate; // 주문 일자
}
