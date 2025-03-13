package com.recruitrooms.controllers;

import com.recruitrooms.dto.LoginRequest;
import com.recruitrooms.dto.LoginResponse;
import com.recruitrooms.models.User;
import com.recruitrooms.services.LogInService;
import com.recruitrooms.services.SignUpService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private LogInService logInService;
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return logInService.login(loginRequest);
    }
    
    @PostMapping("/signup")
    public Mono<User> signup(@RequestBody final User user) {
    	System.out.println("New user with " + user.getUsername() + " has been added to user table.");
    	return signUpService.registerUser(user);
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear authentication context
        SecurityContextHolder.clearContext();

        // Expire the JWT token stored in a cookie
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0); // Expire immediately
        cookie.setPath("/"); // Ensure it's for the entire domain
        response.addCookie(cookie);

        return "Logout successful";
    }
}
