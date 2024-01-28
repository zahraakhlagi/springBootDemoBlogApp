package com.example.springbootblogwebapp.service.impl;

import com.example.springbootblogwebapp.dto.PostDto;
import com.example.springbootblogwebapp.entity.Post;
import com.example.springbootblogwebapp.entity.User;
import com.example.springbootblogwebapp.mapper.PostMapper;
import com.example.springbootblogwebapp.repository.PostRepository;
import com.example.springbootblogwebapp.repository.UserRepository;
import com.example.springbootblogwebapp.service.PostService;
import com.example.springbootblogwebapp.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts= postRepository.findAll();
        //convert posts to postdto
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User user=userRepository.findByEmail(email);
        Post post= PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);

    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User createdBy= userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts=postRepository.searchPosts(query);
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User createdBy= userRepository.findByEmail(email);
        Long userId= createdBy.getId();
       List<Post> posts= postRepository.findPostByUser(userId);
       return posts.stream()
               .map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
    }
}
