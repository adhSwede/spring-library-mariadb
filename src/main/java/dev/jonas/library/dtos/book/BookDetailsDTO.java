package dev.jonas.library.dtos.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO {
    private Long bookId;
    private String title;
    private String authorName;
    private Integer publicationYear;
    private long totalCopies;
    private long availableCopies;
}