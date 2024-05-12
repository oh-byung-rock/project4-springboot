package jpacalendarchallenge.jpacacha.service;

import jpacalendarchallenge.jpacacha.domain.item.Book;
import org.springframework.transaction.annotation.Transactional;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import jpacalendarchallenge.jpacacha.repository.ItemRepository;
import jpacalendarchallenge.jpacacha.controller.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true) // 전체 코드 읽기전용 선언
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    @Transactional // 전체코드설정보다 더 높은권한을 가짐 , 해당 코드만 수정가능하게 선언
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

//    240407
@Transactional
    public void updateItem(Long itemId, BookForm form) {
        Item item = itemRepository.findOne(itemId); // 엔티티 조회
            Book book = (Book) item;
            book.setName(form.getName());
            book.setPrice(form.getPrice());
            book.setStockQuantity(form.getStockQuantity());
//            book.setBrand(form.getBrand());
            // 변경 감지 기능으로 인해 데이터베이스에 자동 반영
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

// 서비스는 레포지토리에게 일을 시키는 본사 직원 입니다.
