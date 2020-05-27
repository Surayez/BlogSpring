package com.example.demo.repository;

import com.example.demo.entity.BlogPost;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<BlogPost, Integer> {}
