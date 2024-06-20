package jpacalendarchallenge.jpacacha.domain;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Getter @Setter
public class Attendance {
    @Id @GeneratedValue
    @Column(name = "attendance_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    private LocalDate date;
    private String status;
}

