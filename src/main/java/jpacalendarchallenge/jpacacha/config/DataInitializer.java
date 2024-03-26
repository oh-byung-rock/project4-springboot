package jpacalendarchallenge.jpacacha.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import jpacalendarchallenge.jpacacha.domain.mandalart.Mandalart;
import jpacalendarchallenge.jpacacha.repository.CellRepository;
import jpacalendarchallenge.jpacacha.repository.MandalartRepository;
import jpacalendarchallenge.jpacacha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CellRepository cellRepository;
    private final MemberRepository memberRepository;
    private final MandalartRepository mandalartRepository;

    //    @PostConstruct : 코드를 실행할때마다 Cell 앤티티의 row, column, contents 를 초기화
    @PostConstruct
    public void initData() {
        long count = cellRepository.count();
        System.out.println("Cell count: " + count);
        List<Member> members = memberRepository.findAll();
        System.out.println("membersis " + members.size());

        if (count == 0) {
            for (int j = 0; j < members.size(); j++) {
                Mandalart mandalart = new Mandalart();
                mandalartRepository.save(mandalart);

                for (int row = 1; row <= 9; row++) {
                    for (int col = 1; col <= 9; col++) {
                        Cell cell = new Cell(row, col, "");
                        cell.setMandalart(mandalart);
                        cellRepository.save(cell);
                    }
                }

                setConnectCell(11 + ( 81 * (j) ), 2 + (9*j));
                setConnectCell(31+ ( 81 * (j) ), 2+ (9*j));
                setConnectCell(14+ ( 81 * (j) ), 3+ (9*j));
                setConnectCell(32+ ( 81 * (j) ), 3+ (9*j));
                setConnectCell(17+ ( 81 * (j) ), 4+ (9*j));
                setConnectCell(33+ ( 81 * (j) ), 4+ (9*j));
                setConnectCell(38+ ( 81 * (j) ), 5+ (9*j));
                setConnectCell(40+ ( 81 * (j) ), 5+ (9*j));
                setConnectCell(65+ ( 81 * (j) ), 6+ (9*j));
                setConnectCell(49+ ( 81 * (j) ), 6+ (9*j));
                setConnectCell(50+ ( 81 * (j) ), 7+ (9*j));
                setConnectCell(68+ ( 81 * (j) ), 7+ (9*j));
                setConnectCell(42+ ( 81 * (j) ), 8+ (9*j));
                setConnectCell(44+ ( 81 * (j) ), 8+ (9*j));
                setConnectCell(71+ ( 81 * (j) ), 9+ (9*j));
                setConnectCell(51+ ( 81 * (j) ), 9+ (9*j));
                setConnectCell(41+ ( 81 * (j) ), 10+ (9*j));
            }
        }
        long count2 = cellRepository.count();
        System.out.println("Cell counttt: " + count2);
    }

    private void setConnectCell(long cellId, int connectCellValue) {
        Cell cell = cellRepository.findById(cellId).orElseThrow(() -> new RuntimeException("Cell not found"));
        cell.setConnectCell(connectCellValue);
        cellRepository.save(cell);
    }

}
