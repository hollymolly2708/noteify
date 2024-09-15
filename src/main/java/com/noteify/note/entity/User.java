package com.noteify.note.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String token;
    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;
    @OneToMany(mappedBy = "user")
    private List<Note> notes;
}
