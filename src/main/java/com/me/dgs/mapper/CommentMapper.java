package com.me.dgs.mapper;

import com.me.dgs.codegen.types.InputComment;
import com.me.dgs.codegen.types.Comment;
import com.me.dgs.entity.CommentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author : Ducpm56
 * @date : 29/12/2024
 **/
@Mapper(componentModel = "spring")
public interface CommentMapper {
  Comment toType(CommentEntity entity);
  CommentEntity toEntity(Comment Comment);
  List<CommentEntity> toEntities(List<InputComment> Comments);
  CommentEntity toEntity(InputComment inputComment);
//  @Mapping(source = "releaseYear", target = "releaseYear")
  List<Comment> toTypes(List<CommentEntity> entities);
}
