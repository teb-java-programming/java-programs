package com.teb.practice.bean;

import lombok.Data;
import lombok.NonNull;

// @Data invokes @Getter @Setter and @RequiredArgsConstructor
@Data
public class Author {
    @NonNull private String authorId;
    private String authorName;
}
