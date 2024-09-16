package com.noteify.note.service.note;

import com.noteify.note.entity.Note;
import com.noteify.note.entity.User;
import com.noteify.note.model.WebResponse;
import com.noteify.note.model.note.request.CreateNoteRequest;
import com.noteify.note.model.note.request.SearchContactRequest;
import com.noteify.note.model.note.request.UpdateNoteRequest;
import com.noteify.note.model.note.response.NoteResponse;
import com.noteify.note.repository.NoteRepository;
import com.noteify.note.service.ValidationService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    public NoteResponse createNote(User user, CreateNoteRequest noteRequest) {
        validationService.validate(noteRequest);
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setTitle(noteRequest.getTitle());
        note.setDescription(noteRequest.getDescription());
        note.setIsArchieved(noteRequest.getIsArchieved());
        note.setCreatedAt(currentDateFormatter());
        note.setUpdatedAt(currentDateFormatter());
        note.setUser(user);
        noteRepository.save(note);

        return toNoteResponse(note);
    }

    @Transactional(readOnly = true)
    public NoteResponse getNote(User user, String id) {
        Note note = noteRepository.findFirstByUserAndId(user, id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note tidak ada"));
        return toNoteResponse(note);
    }

    @Transactional
    public NoteResponse updateNote(User user, UpdateNoteRequest updateNoteRequest) {
        Note note = noteRepository.findFirstByUserAndId(user, updateNoteRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note tidak ada"));
        validationService.validate(updateNoteRequest);
        note.setTitle(updateNoteRequest.getTitle());
        note.setDescription(updateNoteRequest.getDescription());
        note.setIsArchieved(updateNoteRequest.getIsArchieved());
        note.setUpdatedAt(currentDateFormatter());
        noteRepository.save(note);
        return toNoteResponse(note);
    }

    @Transactional
    public void deleteNote(User user, String id) {
        Note note = noteRepository.findFirstByUserAndId(user, id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note is not found"));
        noteRepository.delete(note);
    }

    @Transactional(readOnly = true)
    public Page<NoteResponse> search(User user, SearchContactRequest request) {
        Specification<Note> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("user"), user));
            if (Objects.nonNull(request.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (Objects.nonNull(request.getDescription())) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + request.getDescription() + "%"));
            }
            if (Objects.nonNull(request.getIsArchieved())) {
                predicates.add(criteriaBuilder.equal(root.get("isArchieved"), request.getIsArchieved()));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Note> notes = noteRepository.findAll(specification, pageable);
        List<NoteResponse> noteResponses = notes.getContent().stream().map(this::toNoteResponse).toList();
        return new PageImpl<>(noteResponses, pageable, notes.getTotalElements());
    }

    public String currentDateFormatter() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return localDateTime.format(formatter);
    }

    private NoteResponse toNoteResponse(Note note) {
        return NoteResponse.builder()
                .title(note.getTitle())
                .isArchieved(note.getIsArchieved())
                .description(note.getDescription())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .id(note.getId())
                .build();
    }


}
