//package com.me.dgs.dataloader;
//
//import com.me.dgs.codegen.types.Comment;
//import com.me.dgs.mapper.CommentMapper;
//import com.me.dgs.repository.CommentRepository;
//import com.netflix.graphql.dgs.DgsDataLoader;
//import lombok.RequiredArgsConstructor;
//import org.dataloader.DataLoader;
//import org.dataloader.DataLoaderFactory;
//import org.dataloader.MappedBatchLoader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataLoaderConfig {
//    private final CommentRepository commentRepository;
//    private final CommentMapper commentMapper;
//    @DgsDataLoader
//    public DataLoader<String, List<Comment>> comments() {
//        MappedBatchLoader<String, List<Comment>> mappedBatchLoader = keys ->
//                CompletableFuture.supplyAsync(() -> {
//                    System.out.println("BATCH: loading comments for posts: " + keys);
//                    List<Comment>  comments = commentMapper.toTypes(
//                            commentRepository.findByPostIdIn(new ArrayList<>(keys)));
//                    return comments.stream().collect(java.util.stream.Collectors.groupingBy(Comment::getPostId));
//                });
//
//        return DataLoaderFactory.newMappedDataLoader(mappedBatchLoader);
//    }
//}