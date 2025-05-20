package dev.jonas.library.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    // #################### [ ID ] ####################
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    // #################### [ Dates ] ####################
    @Column(nullable = false)
    private LocalDate borrowedDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnedDate;

    // #################### [ Relationships ] ####################
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // #################### [ Lifecycle Hooks ] ####################
    // N/A
}