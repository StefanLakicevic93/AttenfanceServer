package com.AttendanceServer.entity;

import com.AttendanceServer.dto.AttendanceDto;
import com.AttendanceServer.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User employee;

    @ManyToOne
    private User manager;

    public AttendanceDto getDto() {
        AttendanceDto dto = new AttendanceDto();

        dto.setId(id);
        dto.setDate(date);
        dto.setAttendanceStatus(attendanceStatus);
        dto.setProjectId(project.getId());
        dto.setProjectName(project.getName());
        dto.setEmployeeId(employee.getId());
        dto.setEmployeeName(employee.getName());
        dto.setManagerId(manager.getId());
        dto.setManagerName(manager.getName());

        return dto;
    }

}
