package com.hakimsprojects.portfolio.controller.publiq;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakimsprojects.portfolio.entity.AppUser;
import com.hakimsprojects.portfolio.entity.LoginRequest;
import com.hakimsprojects.portfolio.security.services.TokenService;
import com.hakimsprojects.portfolio.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/public")
public class UserController {

    
    private UserService userService;
    private final TokenService tokenService;
    
    public UserController(UserService userService, TokenService tokenService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

    @PostMapping("/register")
    public ResponseEntity<AppUser> newUser(@RequestBody() AppUser user) {
    	AppUser newUser = userService.addUser(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

            if (isAuthenticated) {
                AppUser user = userService.getUserByUsername(loginRequest.getUsername());
                Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

                String jwtToken = tokenService.generateToken(auth);

                session.setAttribute("user", loginRequest.getUsername());
                return ResponseEntity.ok(jwtToken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred");
        }
    }
}

