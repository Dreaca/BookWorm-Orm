package org.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private boolean availability;
    private String branchName;
}
