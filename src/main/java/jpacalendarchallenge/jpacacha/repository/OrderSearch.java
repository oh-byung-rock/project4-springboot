package jpacalendarchallenge.jpacacha.repository;

import jpacalendarchallenge.jpacacha.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
