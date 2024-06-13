package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetial;

    private String imgUrl;

    private Integer price;

    //Querydsl실행 결과 값을 MainItemDto 객체로 받아서 객체 생성 
    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetial, String imgUrl, Integer price) {
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetial = itemDetial;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
