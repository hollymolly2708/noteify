package com.noteify.note.model.note.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchContactRequest {

    private String title;
    private String description;
    private Boolean isArchieved;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
