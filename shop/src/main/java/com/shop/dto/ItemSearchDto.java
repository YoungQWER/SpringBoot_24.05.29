package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String SearchDateType;

    private ItemSellStatus searchSellStatus;

    //itemNm : 상품명, createBy : 작성자 -> 조회
    private String searchBy;

    //searchBy 따라 itemNm : 상품명 검색
    //searchBy 가 작성자 상품등록자 아이디로 검색
    private String searchQuery = "";
}
