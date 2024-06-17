package com.livecommerce1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "product_img")
public class ProductImg extends BaseEntity {

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName; //이미지명
    private String oriImgName; //원본이미지명
    private String imgUrl; //이미지 경로

    private String repimgYn; //대표이미지(이미지가 여러장일 때 , 메인페이지에서 보이는 이미지)

    @ManyToOne(fetch = FetchType.LAZY)   //외래키 설정
    @JoinColumn(name="product_id")
    private Product product;
    
    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;;
    }
}
