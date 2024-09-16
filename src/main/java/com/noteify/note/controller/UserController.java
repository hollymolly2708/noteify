package com.noteify.note.controller;

import com.noteify.note.entity.User;
import com.noteify.note.model.WebResponse;
import com.noteify.note.model.user.request.UpdateUserRequest;
import com.noteify.note.model.user.response.UserResponse;
import com.noteify.note.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(path = "/noteify/users/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> getUser(User user) {
        UserResponse userResponse = userService.getUser(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(path = "/noteify/users/current-user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> updateUser(User user,
                                                @RequestParam String fullName,
                                                @RequestParam String email,
                                                @RequestParam String address,
                                                @RequestParam String phone

    ) {

        UpdateUserRequest request = new UpdateUserRequest();
        request.setFullName(fullName);
        request.setAddress(address);
        request.setEmail(email);
        request.setPhone(phone);
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }


}
