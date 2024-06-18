package com.shop.service;

import com.shop.dto.CategoryDto;
import com.shop.entity.Category;
import com.shop.entity.Item;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    /**
     * 모든 카테고리 조회
     */
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * 카테고리 저장
     */
    public Long saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return categoryRepository.save(category).getId();
    }

    /**
     * 카테고리 조회 by ID
     */
    @Transactional(readOnly = true)
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: " + categoryId));
    }

    /**
     * 카테고리 수정
     */
    public Long updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("수정할 카테고리를 찾을 수 없습니다. ID: " + categoryId));

        category.setCategoryName(categoryDto.getCategoryName());

        return categoryId;
    }

    /**
     * 카테고리 삭제
     */
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("삭제할 카테고리를 찾을 수 없습니다. ID: " + categoryId));

        categoryRepository.delete(category);
    }

    /**
     * 카테고리 ID로 상품 리스트 조회
     */
    @Transactional(readOnly = true)
    public List<Item> getItemsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: " + categoryId));

        return itemRepository.findByCategoryId(category.getId());
    }

    /**
     * CategoryDto를 Category 엔티티로 변환하는 메서드
     */
    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
