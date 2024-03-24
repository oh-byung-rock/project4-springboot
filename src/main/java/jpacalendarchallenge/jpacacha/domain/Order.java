package jpacalendarchallenge.jpacacha.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
// ▲ 그냥 Table만 하면 order로 name되서 orders로 지정
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // onetomany 클릭후 ctrl + B 누르면 기본값이 EAGER 임을 확인할수있음
    @JoinColumn(name = "member_id")
    // member필드로 접근한뒤 Member테이블 기본키인 member_id를 외래키로 사용하며, 외래키에서만 수정가능
    private Member member; // Member객체에서 member라는 필드로 접근가능하니 Member객체에 member필드 선언

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // onetomany 클릭후 ctrl + B 누르면 기본값이 LAZY 임을 확인할수있음
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL) // Cascade : 한 엔티티의 변경이 다른 엔티티에도 영향을 미치는 것
    @JoinColumn(name = "delivery_id") // Order 와 Delivery 간 갑과 을 정리 (갑)
    private Delivery delivery;

    private String orderDate; // 주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ ORDER, CANCEL ]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member; // 현재 Order 객체의 member 필드 (=private Member member;)
        member.getOrders().add(this); // member.java의 orders가 list라서 get쓴다
    } // 값 설정 메서드 set
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);  // 요소 추가 매서드 add
        orderItem.setOrder(this); // 값 설정 메서드 set
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this); // 값 설정 메서드 set
    }

    // 생성 메서드 (특수한 Order 인스턴스(객체))
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); // OrderStatus 에 ORDER , CANCEL 중 ORDER

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH시mm분");
        String formattedDate = LocalDateTime.now().format(formatter);

        order.setOrderDate(formattedDate);
        return order;
    }

    // 주문취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // 전체 주문가격
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
