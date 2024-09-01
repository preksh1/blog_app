package com.blog.blog_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_app.model.CategoryModel;
import com.blog.blog_app.model.UserModel;
import com.blog.blog_app.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories/")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CategoryModel category){
	      CategoryModel response =  categoryService.createCategory(category);
	      return new ResponseEntity<>(response,HttpStatus.CREATED); //201
	}
	

}
