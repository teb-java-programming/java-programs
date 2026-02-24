package com.teb.practice.model;

import lombok.Data;
import lombok.NonNull;

// @Data - invokes @Getter @Setter and @RequiredArgsConstructor
@Data
public class Author {
    @NonNull private String authorId;
    private String authorName;
}
