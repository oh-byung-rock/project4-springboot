package jpacalendarchallenge.jpacacha.domain.item;

import jakarta.persistence.*;
import jpacalendarchallenge.jpacacha.domain.Category;
import jpacalendarchallenge.jpacacha.domain.CategoryItem;
import jpacalendarchallenge.jpacacha.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // book,album,movie,item을 하나의 테이블(item)에 저장
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
        categoryItem.setItem(this);
    }

    //재고추가 & 감소//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
