package com.noteify.note.model.note.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteResponse {
    private String id;
    private String title;
    private String description;
    private Boolean isArchieved;
    private String createdAt;
    private String updatedAt;
}
