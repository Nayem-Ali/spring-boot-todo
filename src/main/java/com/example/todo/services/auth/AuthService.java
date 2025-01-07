package com.example.todo.services.auth;

import com.example.todo.exception.UserNotFoundException;
import com.example.todo.model.Role;
import com.example.todo.model.User;
import com.example.todo.repository.auth.AuthRepository;
import com.example.todo.requests.auth.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    final private AuthRepository authRepository;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(AddUserRequest addUserRequest) {
        User user = User.builder()
                .email(addUserRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                .role(Role.USER)
                .build();
        return authRepository.save(user);
    }

    @Override
    public Optional<User> login(AddUserRequest addUserRequest) {
        return Optional.ofNullable(authRepository.findByEmailAndPassword(addUserRequest.getEmail(), addUserRequest.getPassword()).orElseThrow(() -> new UserNotFoundException("User Not Found")));
    }
}
