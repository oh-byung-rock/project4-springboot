package jpacalendarchallenge.jpacacha.domain.mandalart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CellTemplate {
    @Id @GeneratedValue
    @Column(name = "cell_template_id")
    private Long id;

    @Column(name = "`row`")
    private int row;
    private int column;
    private int connectCell;

    public CellTemplate() {}
}
