package com.AttendanceServer.entity;

import com.AttendanceServer.dto.LeaveRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Boolean status;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User employee;

    @ManyToOne
    private User manager;

    public LeaveRequestDto getDto() {
        LeaveRequestDto dto = new LeaveRequestDto();

        dto.setId(id);
        dto.setDate(date);
        dto.setStatus(status);
        dto.setProjectId(project.getId());
        dto.setEmployeeId(employee.getId());
        dto.setManagerId(manager.getId());
        dto.setEmployeeName(employee.getName());
        dto.setManagerName(manager.getName());
        dto.setProjectName(project.getName());

        return dto;
    }

}
