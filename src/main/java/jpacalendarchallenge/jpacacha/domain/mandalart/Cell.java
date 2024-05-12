package jpacalendarchallenge.jpacacha.domain.mandalart;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter
public class Cell {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "`row`")
    private int row;

    @Column(name = "`column`")
    private int column;
    private String content; // 셀의 내용

    @Enumerated(EnumType.STRING)
    private CellStatus status; // 셀의 상태 (SUCCESS, FAIL)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandalart_id")
    private Mandalart mandalart;

    public Cell() {}

    public Cell(int row, int column, String content) {
        this.row = row;
        this.column = column;
        this.content = content;
    }

    private int connectCell;
}
