package com.blog.blog_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.blog_app.entity.Category;
import com.blog.blog_app.entity.Post;
import com.blog.blog_app.entity.User;

public interface PostDao extends JpaRepository<Post, Integer>{
List<Post> findPostByUser(User user);
List<Post> findPostByCategory(Category category);
List<Post> getPostByTitleContaining(String keyword);
//@Query("select p from Post p where p.title like %:keyword%")
//List<Post> getPostByTitleContaining(String keyword);
}
