package com.noteify.note.service.user;

import com.noteify.note.entity.User;
import com.noteify.note.model.user.request.LoginUserRequest;
import com.noteify.note.model.user.request.RegisterUserRequest;
import com.noteify.note.model.user.response.TokenResponse;
import com.noteify.note.repository.UserRepository;
import com.noteify.note.security.BCrypt;
import com.noteify.note.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationService validationService;


    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);
        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setFullName(request.getFullName());
        user.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));
        user.setPhone(request.getPhone());

        userRepository.save(user);

    }

    @Transactional
    public TokenResponse login(LoginUserRequest request) {

        //validasi
        validationService.validate(request);
        User user = userRepository.findById(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username atau password salah"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username atau password salah");
        }

    }


    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }

    private Long next30days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }
}
