package com.example.demo.service;

import com.example.demo.repository.BlogRepository;
import com.example.demo.entity.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImplement implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<BlogPost> retrieveAllBlog() {
        List<BlogPost> allBlog = (List<BlogPost>) blogRepository.findAll();
        return allBlog;
    }

    @Override
    public BlogPost getBlog(int blogId) {
        Optional<BlogPost> person = blogRepository.findById(blogId);
        return person.get();
    }

    @Override
    public void saveBlog(BlogPost blog) {
        Date currentDate = new Date();
        blog.setCreatedAt(currentDate);
        blog.setUpdatedAt(currentDate);
        blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }

    @Override
    public void updateBlog(BlogPost blog) {
        blogRepository.save(blog);
    }
}
