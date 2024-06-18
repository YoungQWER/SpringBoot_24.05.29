package com.LiveCommerce.repository;

import com.LiveCommerce.dto.CartDetailDto;
import com.LiveCommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
