package jpacalendarchallenge.jpacacha.controller;

import jpacalendarchallenge.jpacacha.domain.item.Book;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import jpacalendarchallenge.jpacacha.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

//    상품 등록
    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("bookform", new BookForm());
        return "items/createItemForm";
    }
    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setBrand(form.getBrand());
        itemService.saveItem(book);
        return "redirect:/items";
    }

//    상품목록
    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

//    상품 수정 (데이터 폼에 유지하면서)
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setBrand(item.getBrand());
        model.addAttribute("form", form);
        return "items/updateitemForm";
    }

//    @PostMapping(value = "/items/{itemId}/edit")
//    public String updateItem(@ModelAttribute("form") BookForm form) {
//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setBrand(form.getBrand());
//        itemService.saveItem(book);
//        return "redirect:/items";
//    }

//    240407
@PostMapping(value = "/items/{itemId}/edit")
public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
    itemService.updateItem(itemId, form); // 서비스 메서드 호출
    return "redirect:/items";
}

}