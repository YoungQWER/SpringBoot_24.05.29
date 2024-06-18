package com.shop.controller;

import com.shop.entity.Item;
import com.shop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categories/{id}")
    public List<Item> getItemsByCategoryId(@PathVariable Long id) {
        return categoryService.getItemsByCategoryId(id);
    }
}
