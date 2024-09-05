package com.blog.blog_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_app.model.CategoryModel;
import com.blog.blog_app.model.PostModel;
import com.blog.blog_app.model.PostResponse;
import com.blog.blog_app.model.UserModel;
import com.blog.blog_app.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
@Autowired
private PostService postService;

@PostMapping("/create")
ResponseEntity<PostModel> createPost(@Valid @RequestBody PostModel post, @RequestParam(required=true) Integer userId, @RequestParam Integer categoryId){
      PostModel response =  postService.createPost(post, userId, categoryId);
      return new ResponseEntity<>(response,HttpStatus.CREATED); //201
}

@PutMapping("/update/{id}")
ResponseEntity<PostModel> updateUser(@Valid @RequestBody PostModel post, @PathVariable Integer id){
	PostModel response =  postService.updatePost(post, id);
    return new ResponseEntity<>(response,HttpStatus.OK); //200
}

//@GetMapping("/getAllPosts")
//ResponseEntity<List<PostModel>> getAllPosts(){
//	List<PostModel> response =  postService.getAllPosts();
//    return new ResponseEntity<>(response,HttpStatus.OK); //200
//}


//Implement Pagination
@GetMapping("/getAllPosts")
ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
		@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
		@RequestParam(value = "sortBy", required = false, defaultValue = "postId") String sortBy,
		@RequestParam(value = "dir", required = false, defaultValue = "asc") String dir
		){
	PostResponse response =  postService.getAllPosts(pageNum, pageSize, sortBy, dir);
    return new ResponseEntity<>(response,HttpStatus.OK); //200
}
}
