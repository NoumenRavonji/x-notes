package dev.nomenarav.noteservice.note;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public record Note(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,

        String title,
        String content,

        @CreatedDate
        @Column(nullable = false, updatable = false)
        LocalDateTime createdAt,

        @LastModifiedDate
        @Column(nullable = false)
        LocalDateTime updatedAt
) {
        public Note {
        }
}
