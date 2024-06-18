package com.LiveCommerce.controller;

import com.LiveCommerce.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.LiveCommerce.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@Log4j2
public class MainContoller {

    private final ItemService itemService;

    public MainContoller(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);

        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        log.info("-----------------main---------------------");
        log.info(items.getTotalElements());
        items.getContent().forEach(list-> log.info(list));

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}
