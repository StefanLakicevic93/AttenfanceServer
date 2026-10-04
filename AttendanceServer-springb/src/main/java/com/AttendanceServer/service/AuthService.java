package com.AttendanceServer.service;

import com.AttendanceServer.dto.UserDto;
import com.AttendanceServer.entity.User;
import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminUser() {
        User adminUser = userRepository.findByUserRole(UserRole.ADMIN);

        if(adminUser == null) {
            User admin = new User();

            admin.setName("Admin");
            admin.setEmail("admin@test.com");
            admin.setPassword("adminp");
            admin.setUserRole(UserRole.ADMIN);
            userRepository.save(admin);
            System.out.println("Admin account created successfully!");
        } else {
            System.out.println("Admin account already exists!");
        }
    }

    public UserDto login(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDto.getEmail());

        if(optionalUser.isPresent() && userDto.getPassword().equals(
                optionalUser.get().getPassword())) {
            return optionalUser.get().getDto();
        }
        return null;
    }

}
