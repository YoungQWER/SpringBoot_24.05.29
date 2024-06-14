package com.shop.service;

import com.shop.dto.OrderDto;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public Long order(OrderDto orderDto, String email) {

//        Optional<Item> results = itemRepository.findById(orderDto.getItemId());
//        Item item = results.orElseThrow(() -> new EntityNotFoundException());

        //주문 상품을 DB에 조회, 존재하지 않으면 예외 발생
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        //회원 이메일 조회
        Member member = memberRepository.findByEmail(email);

        //주문 상품 리스트 생성
        List<OrderItem> orderItemList = new ArrayList<>();

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());

        //주문 상품 리스트 추가
        orderItemList.add(orderItem);

        // 주문 생성
        Order order = Order.createOrder(member, orderItemList);

        //주문을 DB에 저장
        orderRepository.save(order);

        return order.getId();
    }
}
