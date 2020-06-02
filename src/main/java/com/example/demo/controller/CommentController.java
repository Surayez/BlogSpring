package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommentController {
    private static final Logger logger = Logger.getLogger(CommentController.class.getName());

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

    // COMMENT API SECTION

    @GetMapping("/commentAll/")
    private List<Comment> getComment() {
        return commentService.retrieveAllComments();
    }

    @PostMapping("/comment")
    private int saveComment(Comment comment) {
        // Check if the blog is present
        try {
            blogService.getBlog(Integer.parseInt(comment.getBlogId()));
            if (comment.getParent() != 0) {
                Comment parentComment = commentService.getComment(comment.getParent());
                if (!parentComment.getBlogId().equals(comment.getBlogId())) {
                    throw new Exception();
                }
            }
            commentService.saveComment(comment);
            return comment.getCommentId();
        }
        // Handle if blog isn't present
        catch (Exception e){
            logger.log(Level.SEVERE, "Failed to save comment as no such blog/parent comment exists.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find blog/parent comment");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find blog/parent comment");
        }
    }

    @GetMapping("/commentByBlogId/{id}")
    private List<Comment> getCommentByBlogId(@PathVariable("id") String id) {
        return commentService.getCommentByBlogId(id);
    }

    @PostMapping("/commentUp/{id}")
    private int upvoteComment(@PathVariable("id") int id) {
        try{
            commentService.upvoteComment(id);
            return 1;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to upvote as no such comment exists.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @PostMapping("/commentDown/{id}")
    private int downvoteComment(@PathVariable("id") int id) {
        try{
            commentService.downvoteComment(id);
            return 1;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to upvote as no such comment exists.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @DeleteMapping("/comment/{id}")
    private void deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
    }

}