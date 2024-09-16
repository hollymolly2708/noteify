package com.noteify.note.controller;

import com.noteify.note.entity.User;
import com.noteify.note.model.WebResponse;
import com.noteify.note.model.note.request.CreateNoteRequest;
import com.noteify.note.model.note.request.SearchContactRequest;
import com.noteify.note.model.note.request.UpdateNoteRequest;
import com.noteify.note.model.note.response.NoteResponse;
import com.noteify.note.model.note.response.PagingResponse;
import com.noteify.note.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping(path = "/noteify/notes", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<NoteResponse> createNote(User user, @RequestParam String title, @RequestParam String description, @RequestParam Boolean isArchieved) {
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setDescription(description);
        createNoteRequest.setTitle(title);
        createNoteRequest.setIsArchieved(isArchieved);
        NoteResponse noteResponse = noteService.createNote(user, createNoteRequest);
        return WebResponse.<NoteResponse>builder().data(noteResponse).build();
    }

    @GetMapping(path = "/noteify/notes/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<NoteResponse> getNote(User user, @PathVariable(name = "noteId") String id) {
        NoteResponse noteResponse = noteService.getNote(user, id);
        return WebResponse.<NoteResponse>builder().data(noteResponse).build();
    }

    @PatchMapping(path = "/noteify/notes/{noteId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<NoteResponse> updateNote(User user,
                                                @PathVariable(name = "noteId") String id,
                                                @RequestParam String title,
                                                @RequestParam String description,
                                                @RequestParam Boolean isArchieved) {

        UpdateNoteRequest noteRequest = new UpdateNoteRequest();
        noteRequest.setId(id);
        noteRequest.setDescription(description);
        noteRequest.setTitle(title);
        noteRequest.setIsArchieved(isArchieved);
        NoteResponse noteResponse = noteService.updateNote(user, noteRequest);
        return WebResponse.<NoteResponse>builder().data(noteResponse).build();
    }

    @DeleteMapping(path = "/noteify/notes/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> deleteNote(User user, @PathVariable(name = "noteId") String id) {
        noteService.deleteNote(user, id);
        return WebResponse.<String>builder().data("Note berhasil dihapus").build();

    }

    @GetMapping(path = "/noteify/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<NoteResponse>> search(User user,
                                                  @RequestParam(name = "title", required = false) String title,
                                                  @RequestParam(name = "description", required = false) String description,
                                                  @RequestParam(name = "isArchieved", required = false) Boolean isArchieved,
                                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        System.out.println(title);
        System.out.println(description);
        System.out.println(isArchieved);

        SearchContactRequest request = new SearchContactRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setIsArchieved(isArchieved);
        request.setSize(size);
        request.setPage(page);

        Page<NoteResponse> noteResponses = noteService.search(user, request);
        return WebResponse
                .<List<NoteResponse>>builder()
                .data(noteResponses.getContent())
                .paging(PagingResponse
                        .builder()
                        .currentPage(noteResponses.getNumber())
                        .totalPage(noteResponses.getTotalPages())
                        .size(noteResponses.getSize())
                        .build())
                .build();
    }
}
