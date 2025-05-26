package dev.jonas.library.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InputValidator {

    public static void requireAtLeastOneSearchParam(String title, String author) {
        if (title == null && author == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "At least one search parameter (title or author) is required."
            );
        }
    }
    
}
