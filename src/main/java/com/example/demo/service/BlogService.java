package com.example.demo.service;

import com.example.demo.entity.BlogPost;

import java.util.List;

public interface BlogService {
    public List<BlogPost> retrieveAllBlog();
    public BlogPost getBlog(int blogId);
    public void saveBlog(BlogPost blog);
    public void deleteBlog(int blogId);
    public void updateBlog(BlogPost blog, int blogId);
    public void upvoteBlog(int blogId);
    public void downvoteBlog(int blogId);
}