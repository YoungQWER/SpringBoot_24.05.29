package com.shop.repository;

import com.shop.entity.Category;
import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
    List<Item> findItemsByCategoryId(@Param("categoryId") Long categoryId);
}
