//package com.example.demo.controller;
//
//import com.example.demo.entity.BlogPost;
//import com.example.demo.entity.Comment;
//import com.example.demo.entity.UserObject;
//import com.example.demo.service.BlogService;
//import com.example.demo.service.CommentService;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@RestController
//public class CommentController {
//    private static final Logger logger = Logger.getLogger(CommentController.class.getName());
//
//    @Autowired
//    private CommentService commentService;
//    public void setCommentService(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    // BLOG SECTION
//
//    @GetMapping("/blogsss")
//    private List<BlogPost> getAllBlog() {
//        return blogService.retrieveAllBlog();
//    }
//}
//
//// Upvotes/Downvotes
//// Threaded Comments
//// Users
//// Paginated API
//
//// SQL Injections
//// ReadMe
//// JUnitTests
//// Images (blobs)
//// Why and How