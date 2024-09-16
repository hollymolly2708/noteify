package com.noteify.note.model.note.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateNoteRequest {
    @NotBlank
    @JsonIgnore
    private String id;
    @Size(max = 100)
    private String title;
    @Size(max = 255)
    private String description;
    private Boolean isArchieved;
}
