package com.example.demo.controller;

import com.example.demo.entity.BlogPost;
import com.example.demo.entity.Comment;
import com.example.demo.entity.UserObject;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
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

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // BLOG SECTION

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
        try {
            if (blog.getUserId() != 0) {
                UserObject user = userService.getUser(blog.getUserId());
                blog.setEmail(user.getEmail());
                blog.setFirst_name(user.getFirst_name());
                blog.setLast_name(user.getLast_name());
            }
            blogService.saveBlog(blog);
            return blog.getBlogId();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to save blog as no such user exists.");
            return 0;
        }
    }

    @PostMapping("/blog/{id}")
    private int updateBlog(@PathVariable("id") int id, BlogPost blog) {
        blogService.updateBlog(blog, id);
        return blog.getBlogId();
    }

    @PostMapping("/blogUp/{id}")
    private int upvoteBlog(@PathVariable("id") int id) {
        try{
            blogService.upvoteBlog(id);
            return 1;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to upvote as no such blog exists.");
            return 0;
        }
    }

    @PostMapping("/blogDown/{id}")
    private int downVote(@PathVariable("id") int id) {
        try{
            blogService.downvoteBlog(id);
            return 1;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to upvote as no such blog exists.");
            return 0;
        }
    }

    // BLOG PAGINATION

    @GetMapping("/blogPaginated/{size}/{page}")
    private List<BlogPost> getPaginatedBlog(@RequestParam("size") int size, @RequestParam("page") int page) {
        return blogService.retrieveAllPagedBlogs(page, size);
    }

    @GetMapping("/blogPaginated/{size}")
    private int getTotalBlogPages(@RequestParam("size") int size) {
        return blogService.getTotalPages(size);
    }

    // COMMENT SECTION

    @GetMapping("/comment/")
    private List<Comment> getComment() {
        return commentService.retrieveAllComments();
    }

    @PostMapping("/comment")
    private int saveComment(Comment comment) {
        // Check if the blog is present
        try {
            blogService.getBlog(Integer.parseInt(comment.getBlogId()));
            if (comment.getParent() != 0) {
                commentService.getComment(comment.getParent());
            }
            commentService.saveComment(comment);
            return comment.getCommentId();
        }
        // Handle if blog isn't present
        catch (Exception e){
            logger.log(Level.SEVERE, "Failed to save comment as no such blog/parent comment exists.");
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

    @PostMapping("/commentUp/{id}")
    private int upvoteComment(@PathVariable("id") int id) {
        try{
            commentService.upvoteComment(id);
            return 1;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to upvote as no such comment exists.");
            return 0;
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
            return 0;
        }
    }

    @DeleteMapping("/comment/{id}")
    private void deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
    }


    // USERS SECTION

    @GetMapping("/user")
    private List<UserObject> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @GetMapping("/user/{id}")
    private UserObject getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/user")
    private int saveUser(UserObject user) {
        userService.saveUser(user);
        return user.getUserId();
    }

    @PostMapping("/user/{id}")
    private int updateUser(@PathVariable("id") int id, UserObject user) {
        userService.updateUser(user, id);
        return user.getUserId();
    }

    @PostMapping("/userBlogs/{id}")
    private List<BlogPost> getUserBlogs(@PathVariable("id") int id) {
        return blogService.getBlogsByUserId(id);
    }
}

// Upvotes/Downvotes
// Threaded Comments
// Users
// Paginated API

// SQL Injections
// ReadMe
// JUnitTests
// Images (blobs)
// Why and How