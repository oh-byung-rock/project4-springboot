package jpacalendarchallenge.jpacacha.api;

import jakarta.validation.Valid;
import jpacalendarchallenge.jpacacha.controller.BookForm;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.domain.item.Book;
import jpacalendarchallenge.jpacacha.service.ItemService;
import jpacalendarchallenge.jpacacha.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @PutMapping("api/ver2/updateitem/{id}")
    public UpdateItemResponse updateMemberV2(@PathVariable Long id, @RequestBody @Valid UpdateItemRequest request) {
        BookForm form = new BookForm();
        form.setId(id);  // URL에서 받은 id를 사용
        form.setName(request.getName());
        form.setPrice(request.getPrice());
        form.setStockQuantity(request.getStockQuantity());

        itemService.updateItem(id, form);  // URL에서 받은 id를 사용
        return new UpdateItemResponse(request.getName(), request.getPrice(), request.getStockQuantity());
    }

    @Data // "api/ver2/members" 관련 신규 요청폼
    static class UpdateItemRequest {
        private Long itemId;
        private String name;
        private int price;
        private int stockQuantity;
    }

    @Data @AllArgsConstructor // return에대한 값
    static class UpdateItemResponse {
        private String name;
        private int price;
        private int stockQuantity;
    }
}
