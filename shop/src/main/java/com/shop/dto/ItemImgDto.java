package com.shop.dto;

import com.shop.entity.ItemImg;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ItemImgDto {

    private Long id;

    private String imgName;     //이미지명
    private String oriImgName;  //원본이미지명
    private String oriUrl;      //이미지 경로

    private String repimgYn;    //대표이미지 ( 이미지가 여러장일 때, 메인페이지에서 보이는 이미지)

    private static ModelMapper modelMapper = new ModelMapper();

    //ItemImg 엔티티를 받아서 ItemImgDto 변환
    public static ItemImgDto ItemImgOfItemImgDto(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);

    }
}
