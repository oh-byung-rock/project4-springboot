package jpacalendarchallenge.jpacacha.controller;

import jakarta.servlet.http.HttpSession;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.domain.Order;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import jpacalendarchallenge.jpacacha.repository.OrderSearch;
import jpacalendarchallenge.jpacacha.service.ItemService;
import jpacalendarchallenge.jpacacha.service.MemberService;
import jpacalendarchallenge.jpacacha.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @GetMapping(value = "/orders")
    public String orderList(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("member");
        if (loginMember == null) {
            // 로그인이 되어있지 않다면, 로그인 페이지나 다른 처리를 할 수 있습니다.
            return "redirect:/login";
        }

        List<Order> orders = orderService.findOrdersByMemberId(loginMember.getId());
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}