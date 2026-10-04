package com.AttendanceServer.controller;

import com.AttendanceServer.dto.AttendanceDto;
import com.AttendanceServer.dto.LeaveRequestDto;
import com.AttendanceServer.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/add")
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceDto dto) {
        try {
            return ResponseEntity.ok(attendanceService.markAttendance(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/add/leave")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequestDto dto) {
        try {
            return ResponseEntity.ok(attendanceService.applyLeave(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/leave/employee/{id}")
    public ResponseEntity<?> getAllEmployeeLeaves(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(attendanceService.getAllEmployeeLeaves(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
