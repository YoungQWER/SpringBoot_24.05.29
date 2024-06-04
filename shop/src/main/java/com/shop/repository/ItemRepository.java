package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByItemNm(String itemNm);
//    Item findByItemDetail(String str);
    Item findByItemNmAndItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(int price);
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

//    @Query("select i.itemNm, i.price from Item i \n " +
//            "where i.itemDetail like %:itemDetail% \n" +
//            "order by i.price desc")

    @Query("select i from Item i \n " +
            "where i.itemDetail like %:itemDetail% \n" +
            "order by i.price desc")

    List<Item> findByItemDetail(@Param("itemDetail") String detail);
}