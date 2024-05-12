package jpacalendarchallenge.jpacacha.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jpacalendarchallenge.jpacacha.domain.mandalart.Mandalart;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // 창고(DB)에 저장할 물건들의 분류명 지정
@Getter @Setter // Getter : 값을 가져온다(사물함에서 물건을 꺼낸다.) , Setter : 값을 변경한다.(사물함에서 물건을 넣는다.)
public class Member {
    @Id @GeneratedValue // ▲ id라는 key와 GeneratedValue라는 value를 사용
    @Column(name = "member_id")  //  ▲ 회원테이블명을 member_id가 되도록
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberStatus role;

    @OneToMany(mappedBy = "member")
    // 일대다 관계이며, member라는 필드를 통해 매핑 관계를 접근할수있다.
    private List<Order> orders = new ArrayList<>();
    // 일대다 관계에서 한명의 회원(member)이 많은 주문(order)을 할수있기에 list로 선언한다.

    @OneToOne(mappedBy = "member") // Mandalart 엔티티의 member 필드를 참조
    private Mandalart mandalart;
}
