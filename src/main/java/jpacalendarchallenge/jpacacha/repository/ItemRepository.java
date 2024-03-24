package jpacalendarchallenge.jpacacha.repository;

import jakarta.persistence.EntityManager;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // 없던 정보면 새로운 아이템을 저장
        } else {
            em.merge(item); // 있던 아이템이면 최신정보로 업데이트
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findAll() { // 모든 아이템 검색
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

}

// 레포지토리는 DB와 상호작용을 할수있는 유일한 직원이다.
// 능력1 SAVE : 없던정보면 새로 저장 , 있던 정보면 새로 업데이트
// 능력2 finedOne : 특정 아이템 조회
// 능력3 fineAll : 모든 아이템 조회
