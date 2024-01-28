package com.example.springbootblogwebapp.service;

import com.example.springbootblogwebapp.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
    void createPost(PostDto postDto);
    PostDto findPostById(Long postId);
    void updatePost(PostDto postDto);
    void deletePost(long postId);
    List<PostDto> searchPosts(String query);

    PostDto findPostByUrl(String postUrl);
    List<PostDto> findPostsByUser();
}
