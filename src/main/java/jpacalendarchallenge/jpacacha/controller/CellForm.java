package jpacalendarchallenge.jpacacha.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor  // 기본 생성자를 위해
@AllArgsConstructor // 모든 매개변수를 가진 생성자를 위해
public class CellForm {
    private Long id; // For updates
    private String content;

}