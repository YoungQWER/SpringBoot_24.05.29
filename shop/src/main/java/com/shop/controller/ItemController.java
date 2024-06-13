package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
@Getter
@Setter
@ToString
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //상품 등록 폼
    @GetMapping("/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    //상품 등록
    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model
            , @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        log.info("===========================");
        log.info("itemFormDto: " + itemFormDto);
        itemImgFileList.forEach(list -> log.info(list));
        log.info("===========================");

        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다");
            return "item/itemForm";
        }
        //상품 등록 서비스 호출
        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다");
            return "item/itemForm";
        }
        return "redirect:/";
    }

    //상품 상세조회
    @GetMapping(value = "/admin/item/{itemid}")
    public String itemDtl(@PathVariable("itemid") Long itemid, Model model) {
        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemid);   //상품 상세조회 서비스 호출
            model.addAttribute("itemFormDto", itemFormDto); //호출된 정보를 모델에 추가해서 뷰에 전달
        } catch (Exception e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }
        return "item/itemForm";
    }

    //상품 수정처리
    @PostMapping(value = "/admin/item/{itemid}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,
                             BindingResult bindingResult, @RequestParam("itemImgFile") List<MultipartFile>
                             itemImgFileList, Model model) {
        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다");
            return "item/itemForm";

        }
        try{
         itemService.updateItem(itemFormDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
}