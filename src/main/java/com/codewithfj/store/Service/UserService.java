package com.codewithfj.store.Service;


import com.codewithfj.store.Dto.*;
import com.codewithfj.store.Entity.Role;
import com.codewithfj.store.Entity.User;
import com.codewithfj.store.Repository.UserRepository;
import com.codewithfj.store.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public ApiResponse<UserResponse> register(RegisterRequest request) {
//        Find if email exists
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException("Invalid credentials");
        });

        // Create user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(userResponse)
                .build();
    }

    public ApiResponse<LoginResponse> login (LoginRequest request){
        var userOptional = userRepository.findByEmail(request.getEmail());

        System.out.println(userOptional.isPresent());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        var user = userOptional.get();
        String accessToken = jwtService.generateToken(user.getEmail());

        LoginResponse loginResponse = LoginResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(loginResponse)
                .build();

    }

}
