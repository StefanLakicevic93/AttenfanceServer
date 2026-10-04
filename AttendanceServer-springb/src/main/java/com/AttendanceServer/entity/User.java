package com.AttendanceServer.entity;

import com.AttendanceServer.dto.UserDto;
import com.AttendanceServer.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToOne
    private Project project;

    public UserDto getDto() {
        UserDto dto = new UserDto();

        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setUserRole(userRole);
        if(project != null) {
            dto.setProjectId(project.getId());
            dto.setProjectName(project.getName());
        }

        return dto;
    }
}
