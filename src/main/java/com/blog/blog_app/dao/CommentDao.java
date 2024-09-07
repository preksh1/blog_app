package com.blog.blog_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_app.entity.Category;
import com.blog.blog_app.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
