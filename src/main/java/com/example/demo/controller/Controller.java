package com.example.demo.controller;

import com.example.demo.entity.BlogPost;
import com.example.demo.entity.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BlogService blogService;
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }


    @Autowired
    private CommentService commentService;
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }


    // BLOG API


    @GetMapping("/blog")
    private List<BlogPost> getAllBlog() {
        return blogService.retrieveAllBlog();
    }

    @GetMapping("/blog/{id}")
    private BlogPost getBlog(@PathVariable("id") int id) {
        return blogService.getBlog(id);
    }

    @DeleteMapping("/blog/{id}")
    private void deleteblog(@PathVariable("id") int id) {
        blogService.deleteBlog(id);
    }

    @PostMapping("/blog")
    private int saveBlog(BlogPost blog) {
        blogService.saveBlog(blog);
        return blog.getBlog_id();
    }

    // COMMENT API

//    @GetMapping("/comment/{id}")
//    private List<Comment> getComment(@PathVariable("id") int id) {
//        return commentService.retrieveAllCommentsByBlogId(id);
//    }

}
