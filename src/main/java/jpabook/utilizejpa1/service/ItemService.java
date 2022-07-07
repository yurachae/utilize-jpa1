package jpabook.utilizejpa1.service;

import jpabook.utilizejpa1.domain.item.Item;
import jpabook.utilizejpa1.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);

    }

    /**
     * 상품 조회
     */
    public Item findOneItem(Long itemId){
        return itemRepository.findOne(itemId);
    }

    /**
     * 상품 전체 조회
     */
    public List<Item> findAllItem(){
        return itemRepository.findAll();
    }
}
