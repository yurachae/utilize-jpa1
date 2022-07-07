package jpabook.utilizejpa1.repository;

import jpabook.utilizejpa1.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * 상품 저장
     * 새로 생성하는 객체는 persist
     * 있던 거는 merge
     */
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    /**
     * 상품 단건 조회
     */
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    /**
     * 상품 모두 조회
     */
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
