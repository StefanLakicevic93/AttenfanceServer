package com.AttendanceServer.entity;

import com.AttendanceServer.dto.ProjectDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String duration;

    private Date startDate;

    public ProjectDto getDto() {
        ProjectDto dto = new ProjectDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDuration(duration);
        dto.setStartDate(startDate);

        return dto;
    }

}
