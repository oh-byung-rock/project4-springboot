package jpacalendarchallenge.jpacacha.service;

import jpacalendarchallenge.jpacacha.domain.Attendance;
import jpacalendarchallenge.jpacacha.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<Attendance> getAttendanceByMemberId(Long memberId) {
        return attendanceRepository.findByMemberId(memberId);
    }

    @Transactional
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
}
