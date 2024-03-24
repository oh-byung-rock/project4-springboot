package jpacalendarchallenge.jpacacha.service;

import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.domain.item.Item;
import jpacalendarchallenge.jpacacha.domain.Delivery;
import jpacalendarchallenge.jpacacha.domain.DeliveryStatus;
import jpacalendarchallenge.jpacacha.domain.OrderItem;
import jpacalendarchallenge.jpacacha.domain.Order;
import jpacalendarchallenge.jpacacha.repository.ItemRepository;
import jpacalendarchallenge.jpacacha.repository.MemberRepository;
import jpacalendarchallenge.jpacacha.repository.OrderRepository;
import jpacalendarchallenge.jpacacha.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final로 선언한 값을 해당 특정경로 this로 lombok을 사용해서 지정
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId); // Member.java를 찾는다.
        Item item = itemRepository.findOne(itemId);
        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //주문 저장
        orderRepository.save(order); // orderRepository.save를 통해 order를 저장하면 order에 Cascade로 선언된 delivery, orderitem도 같이 영향받아 save됨
        // 결과 반환
        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

//    검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }


    public List<Order> findOrdersByMemberId(Long memberId) {
        return orderRepository.findOrdersByMemberId(memberId);
    }
}
