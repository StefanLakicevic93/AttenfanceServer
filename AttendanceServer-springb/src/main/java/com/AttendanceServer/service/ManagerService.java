package com.AttendanceServer.service;

import com.AttendanceServer.dto.UserDto;
import com.AttendanceServer.entity.User;
import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final UserRepository userRepository;

    public List<UserDto> getAllEmployeesByProject(Long projectId) {
        List<User> users = userRepository.findAllByProjectIdAndUserRole(
                projectId, UserRole.EMPLOYEE);
        return users.stream().map(User::getDto).toList();
    }

}
