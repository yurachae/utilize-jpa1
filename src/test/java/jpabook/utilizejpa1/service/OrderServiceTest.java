package jpabook.utilizejpa1.service;

import jpabook.utilizejpa1.domain.Address;
import jpabook.utilizejpa1.domain.Member;
import jpabook.utilizejpa1.domain.Order;
import jpabook.utilizejpa1.domain.OrderStatus;
import jpabook.utilizejpa1.domain.item.Book;
import jpabook.utilizejpa1.domain.item.Item;
import jpabook.utilizejpa1.exception.NotEnoughStockException;
import jpabook.utilizejpa1.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @DisplayName("상품주문")
    public void OrderServiceTest() throws Exception{
        //given
        //엔티티 생성
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);

        //주문수량
        int orderCount = 2;

        //when
        //주문 생성
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
        Assertions.assertThat(1).isEqualTo(getOrder.getOrderItems().size());
        Assertions.assertThat(10000*2).isEqualTo(getOrder.getTotalPrice());
        Assertions.assertThat(8).isEqualTo(item.getStockQuantity());

    }
     
     @Test
     @DisplayName("재고수량초과")
     public void OrderServiceTest2() throws Exception{
         //given
         Member member = createMember();
         Item item = createBook("JPA", 10000, 10);
         //주문수량(재고>주문)
         int orderCount = 12;

         //when

         //then
         assertThrows(NotEnoughStockException.class, ()->{
             orderService.order(member.getId(), item.getId(), orderCount);
         });
      }
      
      @Test
      @DisplayName("주문취소")
      public void OrderServiceTest3() throws Exception{
          //given
          Member member = createMember();
          Item item = createBook("JPA", 10000, 10);
          //주문수량(재고>주문)
          int orderCount = 5;
          Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

          //when
          orderService.cancelOrder(orderId);

          //then
          Order getOrder = orderRepository.findOne(orderId);
          Assertions.assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
          Assertions.assertThat(10).isEqualTo(item.getStockQuantity());

       }


    private Member createMember(){
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("seoul","guro", "123123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int quantity){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }
}