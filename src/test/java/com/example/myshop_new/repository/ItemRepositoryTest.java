package com.example.myshop_new.repository;

import com.example.myshop_new.constant.ItemSellStatus;
import com.example.myshop_new.entity.Item;
import com.example.myshop_new.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Rollback(value = false)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    @Test
    @DisplayName("QueryDsl 조회 테스트1")
    public void queryDslTest(){
        this.createItemList();

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QItem qItem = QItem.item; // Item 엔티티 갖고온다.

        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"좋은"+"%"))
                .orderBy(qItem.price.desc());
        // Repository 에 JPQL 또는 쿼리 메소드를 만들지 않아도 동적으로 쿼리 생성해줌

        List<Item> itemList = query.fetch();
        // fetch() 메소드 실행 시점에 쿼리문이 생성된다.

        System.out.println(itemList);
        for (Item item : itemList) {
            System.out.println(" ::: 여기 : "+item.getId());
        }


    }

    @Test
    @DisplayName("QueryDsl 조회 테스트2")
    public void queryDslTest2(){
        this.createItemList();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem qItem = QItem.item;


    }





}