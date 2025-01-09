package dev.nomenarav.noteservice.note;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table( name = "notes")
public class Note {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        String title;

        @Column(columnDefinition = "TEXT")
        String content;

        @Column(nullable = false)
        private Integer userId;

        @CreatedDate
        @Column(nullable = false, updatable = false)
        LocalDateTime createdAt;

        @LastModifiedDate
        @Column(nullable = false)
        LocalDateTime updatedAt;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Note() {
        }

        public Note(String title, String content, Integer userId) {
                this.title = title;
                this.content = content;
                this.userId = userId;
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
                this.updatedAt = LocalDateTime.now();
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
                this.updatedAt = LocalDateTime.now();
        }

        public Integer getUserId() {
                return userId;
        }

        public void setUserId(Integer userId) {
                this.userId = userId;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }
}
