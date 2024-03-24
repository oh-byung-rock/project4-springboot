package jpacalendarchallenge.jpacacha.repository;


import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
    // JpaRepository 에는 findById(), findAll(), findbyid(), save, create, read, update, delete 등등.. 기능들이 추가 코딩없이 사용가능
    List<Cell> findByConnectCell(int connectCell);
}
