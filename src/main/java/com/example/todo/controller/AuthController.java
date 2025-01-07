package com.example.todo.controller;

import com.example.todo.model.Category;
import com.example.todo.model.User;
import com.example.todo.requests.auth.AddUserRequest;
import com.example.todo.response.ApiResponse;
import com.example.todo.services.auth.AuthService;
import com.example.todo.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {
    final private AuthService authService;

    @Autowired
    final private AuthenticationManager authenticationManager;

    @Autowired
    final private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AddUserRequest addUserRequest) {
        try {
            User user = authService.register(addUserRequest);
            return ResponseEntity.ok(new ApiResponse("User Registered", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", e.getMessage())
            );
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody AddUserRequest addUserRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(addUserRequest.getEmail(), addUserRequest.getPassword()));
            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(addUserRequest.getEmail());
                return ResponseEntity.ok(new ApiResponse("Login Success", "Bearer " + token));
            } else{
                return ResponseEntity.ok(new ApiResponse("Login Failed", "User Not Found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", e.getMessage())
            );
        }

    }
}
