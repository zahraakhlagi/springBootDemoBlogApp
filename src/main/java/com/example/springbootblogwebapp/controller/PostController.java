package com.example.springbootblogwebapp.controller;

import com.example.springbootblogwebapp.dto.PostDto;
import com.example.springbootblogwebapp.service.PostService;
import com.example.springbootblogwebapp.util.Role;
import com.example.springbootblogwebapp.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //create handler method, Get request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        String role= SecurityUtils.getRole();
        List<PostDto> posts=null;
        if(Role.ROLE_ADMIN.name().equals(role)){
            posts=postService.findAllPosts();
        }else {
            posts= postService.findPostsByUser();
        }
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model){
        PostDto postDto= new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";

    }
    //handle method to handle from submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }
    // handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,
                               Model model){

        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    // handler method to handle edit post form submit request

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", post);
            return "admin/edit_post";
        }

        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }
    // handler method to handle search post
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }
    // handler method to handle delete post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }



    private static String getUrl(String postTitle){
        // OOPS Concepts Explained in Java
        // oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }
}
