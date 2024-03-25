package jpacalendarchallenge.jpacacha.domain.mandalart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserCell {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_template_id")
    private CellTemplate cellTemplate;


    @Enumerated(EnumType.STRING)
    private CellStatus status; // 셀의 상태 (SUCCESS, FAIL

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandalart_id")
    private Mandalart mandalart;
}