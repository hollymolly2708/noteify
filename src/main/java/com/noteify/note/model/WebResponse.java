package com.noteify.note.model;

import com.noteify.note.model.note.response.PagingResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private T data;
    private String errors;
    private PagingResponse paging;
}
