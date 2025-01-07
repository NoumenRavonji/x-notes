package dev.nomenarav.noteservice.note;

import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(String title, String content, Integer userId){
        return noteRepository.save(new Note(null, title, content, null, null));
    }
}
