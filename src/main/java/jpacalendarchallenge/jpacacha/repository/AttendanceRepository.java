package jpacalendarchallenge.jpacacha.repository;

import jpacalendarchallenge.jpacacha.domain.Attendance;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByMemberId(Long memberId);
}