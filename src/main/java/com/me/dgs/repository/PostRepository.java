package com.me.dgs.repository;

import com.me.dgs.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<PostEntity, String> {
    List<PostEntity> findAllByTitleContainingIgnoreCase(String title);

    PostEntity getById(String id);
}
