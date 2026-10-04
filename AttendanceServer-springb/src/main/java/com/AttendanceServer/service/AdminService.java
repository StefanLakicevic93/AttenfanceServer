package com.AttendanceServer.service;

import com.AttendanceServer.dto.UserDto;
import com.AttendanceServer.entity.Project;
import com.AttendanceServer.entity.User;
import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.repository.ProjectRepository;
import com.AttendanceServer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    public UserDto createUser(UserDto dto) {
        boolean exists = userRepository.findFirstByEmail(dto.getEmail()).isPresent();

        if(exists) {
            throw new EntityExistsException("User already exists");
        }
        Optional<Project> optionalProject = projectRepository.findById(dto.getProjectId());
        if(optionalProject.isPresent()) {
            User user = new User();
            user.setUserRole(dto.getUserRole());
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setProject(optionalProject.get());
            User createdUser = userRepository.save(user);
            return createdUser.getDto();
        }
        throw new EntityNotFoundException("Project not found");
    }

    public List<UserDto> getAllManagers() {
        List<User> users = userRepository.findAllByUserRole(UserRole.MANAGER);
        return users.stream().map(User::getDto).toList();
    }

    public List<UserDto> getAllEmployees() {
        List<User> users = userRepository.findAllByUserRole(UserRole.EMPLOYEE);
        return users.stream().map(User::getDto).toList();
    }



}
