package jpabook.utilizejpa1.repository;

import jpabook.utilizejpa1.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    /**
     * 검색조건으로 조회하기
     */
//    public List<Order> findBySearch(Search orderSearch)
}
