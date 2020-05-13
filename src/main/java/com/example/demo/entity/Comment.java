package com.example.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="TBL_COMMENTS", schema = "BLOG")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="COMMENT_ID")
    @ApiModelProperty(hidden=true)
    private int comment_id;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BLOG_ID")
    private String blogId;

    //getters and setters


    public int getComment_id() {
        return comment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog_id() {
        return blogId;
    }

    public void setBlog_id(String blog_id) {
        this.blogId = blog_id;
    }
}