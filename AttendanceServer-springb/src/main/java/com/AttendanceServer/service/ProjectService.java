package com.AttendanceServer.service;

import com.AttendanceServer.dto.ProjectDto;
import com.AttendanceServer.entity.Project;
import com.AttendanceServer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDto addProject(ProjectDto dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDuration(dto.getDuration());
        project.setStartDate(dto.getStartDate());

        return projectRepository.save(project).getDto();
    }

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(Project::getDto)
                .toList();
    }

}
