package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    //상품 등록
    public Long saveItem(ItemFormDto itemFormDto,
               List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for (int i=0; i<itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i == 0)
                itemImg.setRepimgYn("Y");   //첫 번째 이미지를 대표 이미지로 설정
            else
                itemImg.setRepimgYn("N");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    //상품 상세 정보 조회
    public ItemFormDto getItemDtl(Long itemid){

        //상품 이미지 조회
       List<ItemImg> itemImgList =
               itemImgRepository.findByItemIdOrderByIdAsc(itemid);
       List<ItemImgDto> itemImgDtoList = new ArrayList<>();

       //ItemImg(상품이미지) -> ItemImgDto 리스트에 추가
       for (ItemImg itemImg : itemImgList) {
           ItemImgDto itemImgDto = ItemImgDto.ItemImgofItemImgDto(itemImg);
           itemImgDtoList.add(itemImgDto);
       }

       //Item(상품정보) -> itemFormDto 전달
       Optional<Item> result = itemRepository.findById(itemid);
       Item item = result.orElseThrow(() -> new EntityNotFoundException());

       //item -> itemFormDto 변환
       ItemFormDto itemFormDto = ItemFormDto.of(item);
       itemFormDto.setItemImgDtoList(itemImgDtoList);

       return itemFormDto;
    }

    //상품 수정
    public Long updateItem(ItemFormDto itemFormDto,
                           List<MultipartFile> itemImgFileList) throws Exception{

        //수정할 상품 정보 조회
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        //상품 정보 업데이트
        item.updateItem(itemFormDto);

        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        //이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }
        return item.getId();
    }

    //검색 조건 & 페이징 처리
    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
}
