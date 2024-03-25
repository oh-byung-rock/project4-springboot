package jpacalendarchallenge.jpacacha.repository;

import jpacalendarchallenge.jpacacha.domain.mandalart.Cell;
import jpacalendarchallenge.jpacacha.domain.mandalart.Mandalart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandalartRepository extends JpaRepository<Mandalart, Long> {
    // JpaRepository 에는 findById(), findAll(), findbyid(), save, create, read, update, delete 등등.. 기능들이 추가 코딩없이 사용가능
    Optional<Mandalart> findByMemberId(Long memberId);
}