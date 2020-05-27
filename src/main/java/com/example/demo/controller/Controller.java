package com.example.demo.controller;

import com.example.demo.entity.BlogPost;
import com.example.demo.entity.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

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
        return blog.getBlogId();
    }

    @PostMapping("/blog/{id}")
    private int updateBlog(@PathVariable("id") int id, BlogPost blog) {
        blogService.updateBlog(blog, id);
        return blog.getBlogId();
    }

    // COMMENT API

    @GetMapping("/comment/")
    private List<Comment> getComment() {
        return commentService.retrieveAllComments();
    }

    @PostMapping("/comment")
    private int saveComment(Comment comment) {
        // Check if the blog is present
        try {
            blogService.getBlog(Integer.parseInt(comment.getBlogId()));
            commentService.saveComment(comment);
            return comment.getCommentId();
        }
        // Handle if blog isn't present
        catch (Exception e){
            logger.log(Level.SEVERE, "Failed to save comment as no such blog exists.");
            return 0;
        }
    }

    @PostMapping("/comment/{id}")
    private int updateComment(@PathVariable("id") int id, Comment comment) {
        // Check if the blog is present
        try {
            blogService.getBlog(Integer.parseInt(comment.getBlogId()));
            commentService.updateComment(comment, id);
            return comment.getCommentId();
        }
        // Handle if blog isn't present
        catch (Exception e){
            logger.log(Level.SEVERE, "Failed to save comment as no such blog exists.");
            return 0;
        }
    }

    @GetMapping("/commentByBlogId/{id}")
    private List<Comment> getCommentByBlogId(@PathVariable("id") String id) {
        return commentService.getCommentByBlogId(id);
    }

    @DeleteMapping("/comment/{id}")
    private void deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
    }
}
