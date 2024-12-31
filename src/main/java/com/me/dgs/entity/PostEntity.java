package com.me.dgs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {
    @Id
    private String id;
    private String bannerUri;
    private String title;
    private String description;
    private String publishedDate;

}