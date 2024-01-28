package com.example.springbootblogwebapp.controller;

import com.example.springbootblogwebapp.dto.PostDto;
import com.example.springbootblogwebapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final PostService postService;


    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> postsResponse= postService.findAllPosts();
        model.addAttribute("postsResponse",postsResponse);
        return "blog/view_posts";
    }
    //handler method to handle view post request
    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl,
                            Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        model.addAttribute("post", post);
        return "blog/blog_post";
    }
    //handler method to handle blog search request
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }
}
