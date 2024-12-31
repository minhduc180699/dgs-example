package com.me.dgs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity {
    @Id
    private String id;
    private String postId;
    private String content;
    private String publishedDate;

}