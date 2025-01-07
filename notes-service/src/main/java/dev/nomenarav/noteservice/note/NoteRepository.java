package dev.nomenarav.noteservice.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
