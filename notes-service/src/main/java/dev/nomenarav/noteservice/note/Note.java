package dev.nomenarav.noteservice.note;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

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
}
