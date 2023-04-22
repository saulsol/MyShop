package com.example.myshop_new.repository;

import com.example.myshop_new.constant.ItemSellStatus;
import com.example.myshop_new.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Rollback(value = false)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    private void createItemList(){
        for(int i=0; i<100; i++){
            Item givenItem = Item.builder()
                    .itemName("상자 " + i)
                    .price(1000)
                    .stockNumber(50)
                    .itemDetail("좋은 상품입니다 ㅎㅎ")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();

            itemRepository.save(givenItem);
        }
    }


    @Test
    @DisplayName("상품 저장 테스트")
    public void createTest(){

        Item givenItem = Item.builder()
                .itemName("상자")
                .price(1000)
                .stockNumber(50)
                .itemDetail("좋은 상품입니다 ㅎㅎ")
                .itemSellStatus(ItemSellStatus.SELL)
                .build();

        givenItem.setRegTime(LocalDateTime.now());
        givenItem.setUpdateTime(LocalDateTime.now());

        itemRepository.save(givenItem);

    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findPriceLessThanTest(){
        this.createItemList();

        List<Item> itemList = itemRepository.findByPriceLessThan(10000); // 만원 미만 상품 전부 찾기
    }


    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByDetailByNative(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("설명");
        for (Item item : itemList) {
            System.out.println(item.getId());
        }
    }




}