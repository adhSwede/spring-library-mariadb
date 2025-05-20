package dev.jonas.library.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    // #################### [ ID ] ####################
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    // #################### [ Basic Info ] ####################
    @Column(nullable = false, length = 200)
    private String title;

    private Integer publicationYear;

    private long availableCopies = 1;

    private long totalCopies = 1;

    // #################### [ Relationships ] ####################
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    // #################### [ Lifecycle Hooks ] ####################
    // N/A
}