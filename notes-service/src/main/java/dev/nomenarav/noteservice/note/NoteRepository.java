package dev.nomenarav.noteservice.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
