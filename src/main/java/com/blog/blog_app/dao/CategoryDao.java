package com.blog.blog_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_app.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
