package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookWithAuthorDTO {
    private String title;
    private String authorName;
}