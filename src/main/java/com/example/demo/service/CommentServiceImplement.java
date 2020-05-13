package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplement implements CommentService {

    @Autowired
    CommentRepository commentRepository;

//    @Override
//    public List<Comment> retrieveAllCommentsByBlogId(int id) {
//        return commentRepository.findByBlogId(id);
//    }

    @Override
    public Comment getComment(int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.get();
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }
}
