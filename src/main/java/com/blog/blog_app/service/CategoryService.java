package com.blog.blog_app.service;

import java.util.List;

import com.blog.blog_app.model.CategoryModel;

public interface CategoryService {
CategoryModel createCategory(CategoryModel category);
List<CategoryModel> getAllCategories();
CategoryModel getCategoryById(Integer id);
CategoryModel updateCategory(CategoryModel model , Integer id);
void deleteById(Integer id);
}
