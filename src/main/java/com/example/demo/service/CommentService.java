package com.example.demo.service;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {

    // Comments
    List<Comment> retrieveAllComments();
    public Comment getComment(int commentId);
    public void saveComment(Comment comment);
    public void deleteComment(int commentId);
    public void updateComment(Comment comment);

}