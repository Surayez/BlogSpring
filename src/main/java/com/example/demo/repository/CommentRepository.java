package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByBlogId(String blogId);
    List<Comment> findByParent(int parent);
}
