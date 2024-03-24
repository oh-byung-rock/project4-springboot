package jpacalendarchallenge.jpacacha.domain;

import jakarta.persistence.*;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//하나의 카테고리에는 여러개의 카테고리 상품이 존재할수있다.
//예시 : 스포츠 카테고리에는 축구공,농구공,럭비공 등등.. 이 존재한다.
//하나의 아이템은 여러개의 카테고리 상품에 해당될수있다.
//예시 : 축구공은 스포츠 카테고리, 공 카테고리, 축구 카테고리에 해당될수있다.
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;
//    @ManyToMany
//    @JoinTable(name = "category_item",
//        joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id")) // 중간 테이블 매핑
//    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // CTRL + SHIFT + F 로 @Manytoone 을 다
    @JoinColumn(name = "parent_id")
    private Category parent; // enum 처럼 parent를 child로 구분

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>(); // enum 은 정적이지만 child는 동적으로 값 추가가능

    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
        categoryItem.setCategory(this);
    }

}
