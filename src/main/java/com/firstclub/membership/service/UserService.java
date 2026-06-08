package com.firstclub.membership.service;

import com.firstclub.membership.dto.CreateUserRequest;
import com.firstclub.membership.entity.User;
import com.firstclub.membership.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }
}