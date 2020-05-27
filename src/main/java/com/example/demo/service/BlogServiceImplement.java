package com.example.demo.service;

import com.example.demo.entity.Comment;
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
    public List<BlogPost> getBlogsByUserId(int userId){
        List<BlogPost> blogList = blogRepository.findByUserId(userId);
        return blogList;
    }

    @Override
    public void saveBlog(BlogPost blog) {
        Date currentDate = new Date();
        blog.setCreatedAt(currentDate);
        blog.setUpdatedAt(currentDate);
        blogRepository.save(blog);
    }

    @Override
    public void updateBlog(BlogPost blog, int blogId) {
        BlogPost prevBlog = getBlog(blogId);
        prevBlog.setEmail(blog.getEmail());
        prevBlog.setFirst_name(blog.getFirst_name());
        prevBlog.setLast_name(blog.getLast_name());
        prevBlog.setBlog(blog.getBlog());
        prevBlog.setUpdatedAt(new Date());
        blogRepository.save(prevBlog);
    }

    @Override
    public void upvoteBlog(int blogId){
        BlogPost blog = getBlog(blogId);
        blog.setVotes(blog.getVotes() + 1);
        blogRepository.save(blog);
    }

    @Override
    public void downvoteBlog(int blogId){
        BlogPost blog = getBlog(blogId);
        blog.setVotes(blog.getVotes() - 1);
        blogRepository.save(blog);
    }


    @Override
    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }

}
