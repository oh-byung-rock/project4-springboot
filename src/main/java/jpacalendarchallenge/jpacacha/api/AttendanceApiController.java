package jpacalendarchallenge.jpacacha.api;

import jpacalendarchallenge.jpacacha.domain.Attendance;
import jpacalendarchallenge.jpacacha.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*; // getmapping & postmapping

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AttendanceApiController {

    private final AttendanceService attendanceService;

    @GetMapping(value = "/attendance/{memberId}")
    public List<Attendance> getAttendance(@PathVariable("memberId") Long memberId) {
        return attendanceService.getAttendanceByMemberId(memberId);
    }

    @PostMapping("/attendance")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping(value = "/test/attendance/{memberId}") // 현재 사용자의 모든 출석기록에서 '오늘'날짜만 find
    public ResponseEntity<List<Attendance>> testGetAttendance(@PathVariable("memberId") Long memberId) {
        LocalDate today = LocalDate.now();
        List<Attendance> attendances = attendanceService.getAttendanceByMemberId(memberId);
        List<Attendance> todayAttendances = attendances.stream()
                .filter(attendance -> attendance.getDate().isEqual(today))
                .collect(Collectors.toList());
        return new ResponseEntity<>(todayAttendances, HttpStatus.OK);
    }

    @GetMapping(value = "/attendance/all/{memberId}") // 현재 사용자의 모든 출석기록을 달력에 표시
    public ResponseEntity<List<Attendance>> getAllAttendances(@PathVariable("memberId") Long memberId) {
        List<Attendance> attendances = attendanceService.getAttendanceByMemberId(memberId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }
}
