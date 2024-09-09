package com.blog.blog_app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_app.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
Optional<User> findUserByName(String user);
}
