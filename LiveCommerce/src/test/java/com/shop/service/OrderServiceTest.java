package com.LiveCommerce.service;

import com.LiveCommerce.dto.OrderDto;
import com.LivceCommerce.repository.ItemRepository;
import com.LiveCommerce.repository.MemberRepository;
import com.LiveCommerce.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    public void order(){

        OrderDto orderDto = new OrderDto();
        orderDto.setItemId(1L);
        orderDto.setCount(5);
        String email = "user@aaa.com";

        Long orderId = orderService.order(orderDto, email);

    }
}