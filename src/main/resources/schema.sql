CREATE SCHEMA IF NOT EXISTS BLOG AUTHORIZATION sa;

DROP TABLE IF EXISTS BLOG.TBL_BLOGS;

CREATE TABLE BLOG.TBL_BLOGS (
  blog_id INT NOT NULL PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  blog VARCHAR(2000) DEFAULT NULL,
  votes INT DEFAULT 0,
  user_id INT DEFAULT 0,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  accessibility VARCHAR(50)
);

CREATE TABLE BLOG.TBL_COMMENTS (
  comment_id INT NOT NULL,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  comment_text TEXT DEFAULT NULL,
  blog_id INT NOT NULL,
  votes INT DEFAULT 0,
  parent INT DEFAULT 0,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  accessibility VARCHAR(50),
  CONSTRAINT comment_primary_key PRIMARY KEY (comment_id)
);


CREATE TABLE BLOG.TBL_USERS (
    user_id INT NOT NULL PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) DEFAULT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL
);

ALTER TABLE BLOG.TBL_COMMENTS
ADD FOREIGN KEY (blog_id) REFERENCES BLOG.TBL_BLOGS(blog_id);

CREATE SEQUENCE BLOG.blog_sequence
    INCREMENT BY 1
    MINVALUE 1;

CREATE SEQUENCE BLOG.comment_sequence
    INCREMENT BY 1
    MINVALUE 1;

CREATE SEQUENCE BLOG.user_sequence
    INCREMENT BY 1
    MINVALUE 1;

COMMIT;