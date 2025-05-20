package dev.jonas.library.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    // #################### [ ID ] ####################
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // #################### [ Basic Info ] ####################
    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private LocalDate registrationDate;

    // #################### [ Lifecycle Hooks ] ####################
    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
    }
}