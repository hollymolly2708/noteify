package com.noteify.note.controller;

import com.noteify.note.entity.User;
import com.noteify.note.model.WebResponse;
import com.noteify.note.model.request.LoginUserRequest;
import com.noteify.note.model.request.RegisterUserRequest;
import com.noteify.note.model.response.TokenResponse;
import com.noteify.note.service.AuthService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(path = "/noteify/auth/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> register(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam String fullName,
                                        @RequestParam String address,
                                        @RequestParam String email,
                                        @RequestParam String phone) {

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername(username);
        registerUserRequest.setPassword(password);
        registerUserRequest.setEmail(email);
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhone(phone);
        registerUserRequest.setFullName(fullName);
        authService.register(registerUserRequest);
        return WebResponse.<String>builder().data("User berhasil dibuat").build();
    }

    @PostMapping(path = "/noteify/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<TokenResponse> login(@RequestParam String username, @RequestParam String password) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername(username);
        loginUserRequest.setPassword(password);
        TokenResponse tokenResponse = authService.login(loginUserRequest);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @DeleteMapping(path = "/noteify/auth/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> logout(User user) {
        authService.logout(user);
        return WebResponse.<String>builder().data("Logout berhasil").build();
    }

}
