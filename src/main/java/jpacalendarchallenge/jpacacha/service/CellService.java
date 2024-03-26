package jpacalendarchallenge.jpacacha.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jpacalendarchallenge.jpacacha.controller.CellForm;
import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import jpacalendarchallenge.jpacacha.domain.mandalart.CellStatus;
import jpacalendarchallenge.jpacacha.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
public class CellService {
    private final CellRepository cellRepository;
    @Transactional
    public Cell createOrUpdateCell(CellForm cellForm) {
        Cell cell = cellForm.getId() == null ? new Cell() : cellRepository.findById(cellForm.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cell not found with id: " + cellForm.getId()));
        cell.setContent(cellForm.getContent());
        return cellRepository.save(cell);
    } 
    // upsert : 두가지 상황에 대비한 설계

    public Optional<Cell> findById(Long id) {
        return cellRepository.findById(id);
    }

    @Transactional
    public void updateCellStatus(Long id, CellStatus status) {
        System.out.println("Cell count: " + id);
        Cell cell = cellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found with id: " + id));

        // 연결된 모든 셀을 찾아서 상태 업데이트
        if (cell.getConnectCell() != 0) {
            List<Cell> connectedCells = cellRepository.findByConnectCell(cell.getConnectCell());
            for (Cell connectedCell : connectedCells) {
                connectedCell.setStatus(status);
                cellRepository.save(connectedCell);
            }
        } else {
            // 단일 셀의 상태 업데이트
            cell.setStatus(status);
            cellRepository.save(cell);
        }

        log.info("Cell ID: {}, Status: {}", cell.getId(), cell.getStatus());
    }


    public List<Cell> findAllCells() {
        return cellRepository.findAll(); // JPA Repository의 findAll 메서드를 사용하여 모든 셀을 조회
    }

    public void updateCellContent(Long cellId, String newContent) {
        Cell cell = cellRepository.findById(cellId)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found"));
        if(cell.getConnectCell() != 0) {
            // 연결된 모든 셀을 찾아서 내용 업데이트
            List<Cell> connectedCells = cellRepository.findByConnectCell(cell.getConnectCell());
            for(Cell connectedCell : connectedCells) {
                connectedCell.setContent(newContent);
                cellRepository.save(connectedCell);
            }
        } else {
            // 단일 셀 업데이트
            cell.setContent(newContent);
            cellRepository.save(cell);
        }
    }

    public List<Cell> findCellsByMandalartId(Long mandalartId) {
        return cellRepository.findByMandalartId(mandalartId);
    }
}
