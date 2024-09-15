package com.noteify.note.service;

import com.noteify.note.entity.User;
import com.noteify.note.model.request.UpdateUserRequest;
import com.noteify.note.model.response.UserResponse;
import com.noteify.note.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationService validationService;

    public UserResponse getUser(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .address(user.getAddress())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request) {
        validationService.validate(request);
        if (Objects.nonNull(request.getFullName())) {
            user.setFullName(request.getFullName());
        }
        if(Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getPhone())){
            user.setPhone(request.getPhone());
        }
        if(Objects.nonNull(request.getAddress())){
            user.setAddress(request.getAddress());
        }


        userRepository.save(user);
        return UserResponse.builder()
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .username(user.getUsername())
                .address(user.getAddress())
                .email(user.getEmail())
                .build();
    }


}
