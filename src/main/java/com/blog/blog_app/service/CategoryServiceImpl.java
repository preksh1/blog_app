package com.blog.blog_app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.CategoryDao;
import com.blog.blog_app.entity.Category;
import com.blog.blog_app.exception.NoDataFoundException;
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

	@Override
	public List<CategoryModel> getAllCategories() {
		List<Category> res = categoryDao.findAll();
		List<CategoryModel> response = new ArrayList<>();
		for(Category r: res) {response.add(modelMapper.map(r, CategoryModel.class));}
		return response;
	}

	@Override
	public CategoryModel getCategoryById(Integer id) {
		Category c = categoryDao.findById(id).orElseThrow(()-> new NoDataFoundException("Category", "No categoryId found", id.toString()));
		return modelMapper.map(c, CategoryModel.class);
	}

	@Override
	public CategoryModel updateCategory(CategoryModel model, Integer id) {
		//
		Category c = categoryDao.findById(id).orElseThrow(()-> new NoDataFoundException("Category", "No categoryId found", id.toString()));
		c.setCatagoryName(model.getCatagoryName());
		c.setCategoryDescription(model.getCategoryDescription());
	    Category response = categoryDao.save(c);
		return modelMapper.map(response, CategoryModel.class);
	}

	@Override
	public void deleteById(Integer id) {
		Category c = categoryDao.findById(id).orElseThrow(()-> new NoDataFoundException("Category", "No categoryId found", id.toString()));
		
		categoryDao.delete(c);
	}

}
