package com.noteify.note.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "notes")
public class Note {
    @Id
    private String id;
    private String title;
    private String description;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
