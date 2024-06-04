package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Log4j2
class ItemRepositotyTest {

    @Autowired
    ItemRepository itemRepositoty;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();

        item.setItemNm("라면323");
        item.setPrice(1500);
        item.setItemDetail("라면 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStoackNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        Item savedItem = itemRepositoty.save(item);
    }

    @Test
    @DisplayName("id번 검색")
    public void findByIdTest(){
        Optional<Item> result = itemRepositoty.findById(2L);

        Item item = result.get();

        log.info(item);
    }

    @Test
    @DisplayName("라면 검색")
    public void findByNameTest(){
        Item item = itemRepositoty.findByItemNm("라면323");

        log.info("라면 : " + item);
    }

    @Test
    @DisplayName("라면 상세 설명")
    public void findByItemDetailTest(){
        Item item = itemRepositoty.findByItemDetail("라면 상세 설명");

        log.info("라면 상세 설명 : " + item);
    }

    @Test
    @DisplayName("라면 & 라면 상세 설명")
    public void findByItemNmAndItemDetailTest() {
        Item item = itemRepositoty
                .findByItemNmAndItemDetail("라면2", "라면 상세 설명3");

        log.info("라면 상세 설명 : " + item);
    }

}