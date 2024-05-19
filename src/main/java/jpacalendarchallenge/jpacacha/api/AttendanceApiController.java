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

    @GetMapping(value = "/test/attendance/{memberId}")
    public ResponseEntity<List<Attendance>> testGetAttendance(@PathVariable("memberId") Long memberId) {
        List<Attendance> attendances = attendanceService.getAttendanceByMemberId(memberId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }
}
