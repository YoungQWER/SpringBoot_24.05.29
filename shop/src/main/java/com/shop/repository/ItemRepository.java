package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByItemNm(String itemNm);
    Item findByItemDetail(String str);
    Item findByItemNmAndItemDetail(String itemNm, String itemDetail);

}