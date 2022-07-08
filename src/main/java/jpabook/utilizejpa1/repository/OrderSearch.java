package jpabook.utilizejpa1.repository;

import jpabook.utilizejpa1.domain.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearch {

    private String memberName; //회원이름
    private OrderStatus orderStatus; //[ORDER, COMPLETION]

}
