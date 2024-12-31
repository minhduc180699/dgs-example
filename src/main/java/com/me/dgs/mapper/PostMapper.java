package com.me.dgs.mapper;

import com.me.dgs.codegen.types.InputPost;
import com.me.dgs.codegen.types.Post;
import com.me.dgs.entity.PostEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Ducpm56
 * @date : 29/12/2024
 **/
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface PostMapper {
  Post toType(PostEntity entity);
  PostEntity toEntity(Post post);
  List<PostEntity> toEntities(List<InputPost> posts);
  PostEntity toEntity(InputPost inputPost);
  List<Post> toTypes(List<PostEntity> entities);
}
