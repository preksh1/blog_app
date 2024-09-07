package com.blog.blog_app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_app.model.CommentModel;
import com.blog.blog_app.model.PostModel;
import com.blog.blog_app.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	@PostMapping("/create")
	ResponseEntity<CommentModel> createComment(@Valid @RequestBody CommentModel comment, @RequestParam(required=true) Integer userId, @RequestParam Integer postId){
	      CommentModel response =  commentService.createComment(comment, userId, postId);
	      return new ResponseEntity<>(response,HttpStatus.CREATED); //201
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> delete(@PathVariable Integer id){
		commentService.deleteCommentById(id);
		return ResponseEntity.ok(Map.of("message",String.format("Comment with %d id is deleted!!!", id)));
	}
}
