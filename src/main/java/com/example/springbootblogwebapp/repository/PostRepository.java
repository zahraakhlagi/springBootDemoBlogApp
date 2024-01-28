package com.example.springbootblogwebapp.repository;

import com.example.springbootblogwebapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post>findByUrl(String url);
     @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);
    @Query(value = "select * from posts p where p.created_by=:userId", nativeQuery = true)
    List<Post> findPostByUser(Long userId);
}
