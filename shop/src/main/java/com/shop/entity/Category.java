package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    // Item을 추가하는 메서드
    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this); // 양방향 연관관계 설정
    }

    // Item을 제거하는 메서드
    public void removeItem(Item item) {
        items.remove(item);
        item.setCategory(null); // 양방향 연관관계 제거
    }
}
