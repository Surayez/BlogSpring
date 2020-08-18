package com.example.demo.service;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {

    // Comment Services

    List<Comment> retrieveAllComments();
    Comment getComment(int commentId);
    void saveComment(Comment comment);
    void deleteComment(int commentId);
    void updateComment(Comment comment, int commentId);
    List<Comment> getCommentByBlogId(String blogId);
    void upvoteComment(int commentId);
    void downvoteComment(int commentId);

}