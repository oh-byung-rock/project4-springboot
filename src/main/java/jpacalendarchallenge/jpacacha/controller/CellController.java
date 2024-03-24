package jpacalendarchallenge.jpacacha.controller;

import jakarta.persistence.EntityNotFoundException;
import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import jpacalendarchallenge.jpacacha.domain.mandalart.CellStatus;
import jpacalendarchallenge.jpacacha.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @GetMapping(value = "/mandalart")
    public String viewMandalart(Model model) {
        List<Cell> cells = cellService.findAllCells(); // 모든 셀 조회

        // 9x9 그리드에 맞게 셀 데이터 구조화
        Cell[][] grid = new Cell[9][9];
        for (Cell cell : cells) {
            grid[cell.getRow() - 1][cell.getColumn() - 1] = cell; // 셀 위치에 따라 그리드에 할당
        }

        model.addAttribute("grid", grid); // 구조화된 셀 데이터 모델에 추가
        return "mandalart/mandalart";
    }

    @GetMapping(value = "/mandalart/new")
    public String createCellForm(Model model) {
        model.addAttribute("cellForm", new CellForm());
        return "mandalart/createcellform";
    }

    @PostMapping(value = "/mandalart/new")
    public String createCell(@ModelAttribute CellForm cellForm) {
        cellService.createOrUpdateCell(cellForm);
        return "redirect:/mandalart";
    }

    @GetMapping(value = "/mandalart/{id}/repair")
    public String repairCellForm(@PathVariable("id") Long id, Model model) {
        Cell cell = cellService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cell not found"));
        model.addAttribute("cellForm", new CellForm(cell.getId(), cell.getContent()));
        // repairCellForm.html로 이동, 셀 내용 수정 및 상태 변경 가능
        return "mandalart/repaircellform";
    }

    @PostMapping(value = "/mandalart/{id}/repair")
    public String repairCell(@PathVariable("id") Long id, @ModelAttribute CellForm cellForm,
                             @RequestParam(name = "action", required = false) String action) {
        if ("update".equals(action)) {
            cellService.updateCellContent(id, cellForm.getContent());
        } else if ("complete".equals(action)) {
            cellService.updateCellStatus(id, CellStatus.SUCCESS);
        } else if ("fail".equals(action)) {
            cellService.updateCellStatus(id, CellStatus.FAIL);
        }
        return "redirect:/mandalart";
    }
}
