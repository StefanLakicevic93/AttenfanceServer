package com.AttendanceServer.controller;

import com.AttendanceServer.dto.UserDto;
import com.AttendanceServer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        UserDto dbUser = authService.login(userDto);

        if(dbUser == null) {
            return new ResponseEntity<>("Wrong credentials!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

}
