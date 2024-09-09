package com.blog.blog_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_app.model.CategoryModel;
import com.blog.blog_app.model.UserModel;
import com.blog.blog_app.service.CategoryService;

import javax.validation.Valid;

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
	
	@GetMapping("/getAllCategories")
	ResponseEntity<List<CategoryModel>> getAllCategories(){
		List<CategoryModel> response = categoryService.getAllCategories();
		 return new ResponseEntity<>(response,HttpStatus.OK);   //200
	}

	@GetMapping("/getCategoryById/{id}")
	ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id){
		CategoryModel response =  categoryService.getCategoryById(id);
		 return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	ResponseEntity<CategoryModel> updateCategory(@Valid @RequestBody CategoryModel category, @PathVariable Integer id){
		CategoryModel response =  categoryService.updateCategory(category, id);
	    return new ResponseEntity<>(response,HttpStatus.OK); //200
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteById(@PathVariable Integer id){
	      categoryService.deleteById(id);
		 return new ResponseEntity<>(Map.of("message","Id deleted successfully!"),HttpStatus.OK);
	}
}
