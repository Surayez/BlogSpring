package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="TBL_BLOGS", schema = "BLOG")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
    @SequenceGenerator(name = "blog_seq", sequenceName = "blog_sequence", schema = "BLOG")
    @Column(name="BLOG_ID")
    private int blogId;

    @Column(name="FIRST_NAME")
    private String first_name;

    @Column(name="LAST_NAME")
    private String last_name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BLOG")
    private String blog;

    //getters and setters


    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}