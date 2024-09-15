package com.noteify.note.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    @NotBlank
    @Size(max = 100)
    private String username;
    @NotBlank
    @Size(max = 100)
    private String password;
    @NotBlank
    @Size(max = 100)
    private String fullName;
    @NotBlank
    @Size(max = 100)
    private String address;
    @NotBlank
    @Size(max = 100)
    private String email;
    @NotBlank
    @Size(max = 100)
    private String phone;

}
