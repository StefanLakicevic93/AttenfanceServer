package com.AttendanceServer.dto;

import com.AttendanceServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;

    private Long projectId;

    private String projectName;

}
