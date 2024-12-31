package com.me.dgs.dataloader;

import com.me.dgs.codegen.types.Comment;
import com.me.dgs.mapper.CommentMapper;
import com.me.dgs.repository.CommentRepository;
import com.netflix.graphql.dgs.DgsDataLoader;
import lombok.RequiredArgsConstructor;
import org.dataloader.MappedBatchLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsDataLoader(name = "comments")
@RequiredArgsConstructor
public class CommentDataLoader implements MappedBatchLoader<String, List<Comment>> {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CompletionStage<Map<String, List<Comment>>> load(Set<String> postIds) {

        return CompletableFuture.supplyAsync(
                () -> {
                    var result = commentMapper.toTypes(commentRepository.findByPostIdIn(postIds));

                    return result.stream().collect(Collectors.groupingBy(Comment::getPostId));
                }
        );
    }
}