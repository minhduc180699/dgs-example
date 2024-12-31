package com.me.dgs.repository;

import com.me.dgs.entity.CommentEntity;
import com.me.dgs.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    List<CommentEntity> findByPostId(String postId);
    List<CommentEntity> findByPostIdIn(Collection<String> postId);
}
