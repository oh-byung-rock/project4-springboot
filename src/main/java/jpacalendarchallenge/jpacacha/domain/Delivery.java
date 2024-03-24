package jpacalendarchallenge.jpacacha.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // class order 와 delivery 간 갑과 을 정리 (을)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // EnumType.ordinal 로 쓸경우 ready,comp 에서 ready를 1 comp를 2로 저장한다. 
    // 여기서 ready, xxx, comp로 늘어나는경우 xxx가 2 comp 가 3이 되서 망함 그래서 string 써야됨
    private DeliveryStatus status; // READY, COMP
}
