package com.example.springbootblogwebapp.mapper;

import com.example.springbootblogwebapp.dto.PostDto;
import com.example.springbootblogwebapp.entity.Post;

public class PostMapper {
    //map post entity to postDto
    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .url(post.getUrl())
                .title(post.getTitle())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }

    // map postDto to post entity
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .url(postDto.getUrl())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createdOn(postDto.getCreatedOn())
                .shortDescription(postDto.getShortDescription())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
