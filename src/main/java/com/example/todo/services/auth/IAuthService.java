package com.example.todo.services.auth;

import com.example.todo.model.User;
import com.example.todo.requests.auth.AddUserRequest;

import java.util.Optional;

public interface IAuthService {
    User register(AddUserRequest addUserRequest);
    Optional<User> login(AddUserRequest addUserRequest);
}
