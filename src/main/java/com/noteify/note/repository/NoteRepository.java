package com.noteify.note.repository;

import com.noteify.note.entity.Note;
import com.noteify.note.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository  extends JpaRepository<Note, String>, JpaSpecificationExecutor<Note> {
    Optional<Note> findFirstByUserAndId(User user, String id);
}
