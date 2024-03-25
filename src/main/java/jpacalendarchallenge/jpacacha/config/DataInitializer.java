package jpacalendarchallenge.jpacacha.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import jpacalendarchallenge.jpacacha.domain.mandalart.Mandalart;
import jpacalendarchallenge.jpacacha.repository.CellRepository;
import jpacalendarchallenge.jpacacha.repository.MandalartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CellRepository cellRepository;

    private final MandalartRepository mandalartRepository;

    //    @PostConstruct : 코드를 실행할때마다 Cell 앤티티의 row, column, contents 를 초기화
    @PostConstruct
    public void initData() {

        long count = cellRepository.count();
        System.out.println("Cell count: " + count);

        if (count == 0) {
        Mandalart mandalart = new Mandalart();
        mandalartRepository.save(mandalart);

        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                Cell cell = new Cell(row, col, "");
                cell.setMandalart(mandalart);
                cellRepository.save(cell);
            }
        }

        setConnectCell(11, 2);
        setConnectCell(31, 2);
        setConnectCell(14, 3);
        setConnectCell(32, 3);
        setConnectCell(17, 4);
        setConnectCell(33, 4);
        setConnectCell(38, 5);
        setConnectCell(40, 5);
        setConnectCell(65, 6);
        setConnectCell(49, 6);
        setConnectCell(50, 7);
        setConnectCell(68, 7);
        setConnectCell(42, 8);
        setConnectCell(44, 8);
        setConnectCell(71, 9);
        setConnectCell(51, 9);
        setConnectCell(41, 10);
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
