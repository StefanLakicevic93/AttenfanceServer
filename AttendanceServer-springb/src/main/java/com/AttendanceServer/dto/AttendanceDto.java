package com.AttendanceServer.dto;

import com.AttendanceServer.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDto {

    private Long id;

    private LocalDate date;

    private AttendanceStatus attendanceStatus;

    private Long projectId;

    private String projectName;

    private Long employeeId;

    private String employeeName;

    private Long managerId;

    private String managerName;

}
