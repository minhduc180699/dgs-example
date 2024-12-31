package com.me.dgs.datafetcher;

import com.me.dgs.codegen.DgsConstants;
import com.me.dgs.codegen.types.Comment;
import com.me.dgs.codegen.types.InputPost;
import com.me.dgs.codegen.types.Post;
import com.me.dgs.mapper.PostMapper;
import com.me.dgs.repository.PostRepository;
import com.netflix.graphql.dgs.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Ducpm56
 * @date : 29/12/2024
 **/
@DgsComponent
@RequiredArgsConstructor
public class PostFetcher {
  private final PostMapper postMapper;
  private final PostRepository postRepository;
  @DgsQuery
  public List<Post> getPosts(@InputArgument("titleFilter") String titleFilter) {
    if (titleFilter == null) {
      titleFilter = "";
      throw new IllegalArgumentException("titleFilter must not be null");
    }
    var postEntities = postRepository.findAllByTitleContainingIgnoreCase(titleFilter);
    return postMapper.toTypes(postEntities);
  }
  @DgsQuery
  public Post getPost(@InputArgument("id") String id) {
    var entity = postRepository.getById(id);
    return postMapper.toType(entity);
  }
  @DgsMutation
  public Post createPost(@InputArgument InputPost inputPost) {
    var postEntity = postMapper.toEntity(inputPost);
    postEntity = postRepository.save(postEntity);
    return postMapper.toType(postEntity);
  }
  @DgsMutation
  public List<Post> createPosts(@InputArgument @Valid List<@Valid InputPost> inputPosts) {
    var postEntities = postMapper.toEntities(inputPosts);
    postEntities = postRepository.saveAll(postEntities);
    return postMapper.toTypes(postEntities);
  }
  @DgsMutation
  public Post updatePost(@InputArgument InputPost inputPost) {
    var postEntity = postMapper.toEntity(inputPost);
    postEntity = postRepository.save(postEntity);
    return postMapper.toType(postEntity);
  }
  @DgsMutation
  public String deletePost(@InputArgument String id) {
    postRepository.deleteById(id);
    return id;
  }
  @DgsMutation
  public Integer deleteAll() {
    var entities = postRepository.findAll();
    postRepository.deleteAll();
    return entities.size();
  }

}
