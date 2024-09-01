package com.blog.blog_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.CategoryDao;
import com.blog.blog_app.entity.Category;
import com.blog.blog_app.model.CategoryModel;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryModel createCategory(CategoryModel category) {
		//
		Category c = modelMapper.map(category, Category.class);
		return modelMapper.map(categoryDao.save(c), CategoryModel.class);
		
	}

}
