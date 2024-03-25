package jpacalendarchallenge.jpacacha.repository;

import jpacalendarchallenge.jpacacha.domain.mandalart.CellTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellTemplateRepository extends JpaRepository<CellTemplate, Long> {
    // 필요한 경우 추가 쿼리 메서드를 여기에 정의할 수 있습니다.
}