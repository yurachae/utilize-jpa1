package jpabook.utilizejpa1.domain.item;

import jpabook.utilizejpa1.domain.Category;
import jpabook.utilizejpa1.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**[비즈니스 로직]
     * 재고수량 증가
     */
    public void increaseStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**[비즈니스 로직]
     * 재고수량 감소
     * 수량 0개 이하 예외처리
     */
    public void decreaseStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
