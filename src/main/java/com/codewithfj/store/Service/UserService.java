package com.codewithfj.store.Service;


import com.codewithfj.store.Dto.RegisterRequest;
import com.codewithfj.store.Entity.Role;
import com.codewithfj.store.Entity.User;
import com.codewithfj.store.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String register(RegisterRequest request) {
//        Find if email exists
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException("Invalid credentials");
        });

        // Create user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return user.getEmail();
    }
}
