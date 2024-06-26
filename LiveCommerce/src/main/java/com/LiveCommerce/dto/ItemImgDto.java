package com.LiveCommerce.dto;

import com.LiveCommerce.entity.ItemImg;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@ToString
@Log4j2
@Getter@Setter
public class ItemImgDto {

    private Long id;

    private String imgName; //이미지명
    private String oriImgName; //원본이미지명
    private String imgUrl; //이미지 경로

    private String repimgYn; //대표이미지(이미지가 여러장일 때 , 메인페이지에서 보이는 이미지)

    private static ModelMapper modelMapper = new ModelMapper();

    //ItemImg 엔티티를 받아서 ItemImgDto 변환
    public static ItemImgDto ItemImgOfItemImgDto(ItemImg itemImg) {

        return modelMapper.map(itemImg, ItemImgDto.class);


    }
}
