package jpacalendarchallenge.jpacacha.domain.mandalart;

import jakarta.persistence.*;
import jpacalendarchallenge.jpacacha.domain.Member;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Mandalart {
    @Id @GeneratedValue
    @Column(name = "mandalart_id")
    private Long id;

    @OneToMany(mappedBy = "mandalart")
    private List<Cell> cells = new ArrayList<>();

    public Mandalart() {}

    // 연관관계 메소드
    public void addCell(Cell cell) {
        cells.add(cell);
        cell.setMandalart(this);
    }

    @OneToOne
    @JoinColumn(name = "member_id") // 외래 키 관리
    private Member member;
}
