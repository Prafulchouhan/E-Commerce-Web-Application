package com.server.api.contoller.auth;

import com.server.api.dto.RegistrationBody;
import com.server.exception.UserAlreadyExistsException;
import com.server.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody body){
        try {
            userService.registerUser(body);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
