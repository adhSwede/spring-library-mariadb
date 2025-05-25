package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BookInputDTO {
    private String title;
    private Integer publicationYear;
    private long availableCopies;
    private long totalCopies;
    private Long authorId;
}
