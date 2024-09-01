package com.blog.blog_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_app.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
