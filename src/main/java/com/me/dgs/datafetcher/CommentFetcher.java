package com.me.dgs.datafetcher;

import com.me.dgs.codegen.DgsConstants;
import com.me.dgs.codegen.types.Comment;
import com.me.dgs.codegen.types.InputComment;
import com.me.dgs.codegen.types.Post;
import com.me.dgs.mapper.CommentMapper;
import com.me.dgs.repository.CommentRepository;
import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@DgsComponent
@RequiredArgsConstructor
public class CommentFetcher {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    @DgsData(parentType = DgsConstants.POST.TYPE_NAME, field = DgsConstants.POST.Comments)
    public CompletableFuture<List<Comment>> comments(DgsDataFetchingEnvironment dataFetchingEnvironment) {
        Post post = dataFetchingEnvironment.getSource();
        DataLoader<String, List<Comment>> dataLoader = dataFetchingEnvironment.getDataLoader("comments");
        return dataLoader.load(post.getId());
    }
    @DgsMutation
    public Comment createComment(@InputArgument InputComment inputComment) {
        var entity =  commentRepository.save(commentMapper.toEntity(inputComment));
        return commentMapper.toType(entity);
    }
    @DgsQuery
    public List<Comment> getCommentByPostId(@InputArgument String postId) {
        var commentEntities = commentRepository.findByPostId(postId);
        return commentMapper.toTypes(commentEntities);
    }
}
