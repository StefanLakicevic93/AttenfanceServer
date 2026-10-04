package com.AttendanceServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {

    private Long id;

    private String name;

    private String duration;

    private Date startDate;

}
