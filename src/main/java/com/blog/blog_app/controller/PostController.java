package com.blog.blog_app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog_app.model.CategoryModel;
import com.blog.blog_app.model.PostModel;
import com.blog.blog_app.model.PostResponse;
import com.blog.blog_app.model.UserModel;
import com.blog.blog_app.service.FileService;
import com.blog.blog_app.service.PostService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
@Autowired
private PostService postService;

@Value("project.image")
private String path;

@Autowired
private FileService fileService;


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

@DeleteMapping("/delete/{id}")
ResponseEntity<?> delete(@PathVariable Integer id){
	postService.deletePostById(id);
	return ResponseEntity.ok(Map.of("message",String.format("Post with %d id is deleted!!!", id)));
}

@GetMapping("/getPost/{id}")
ResponseEntity<PostModel> getPostByPostId(@PathVariable Integer id){
	PostModel response = postService.getPostByPostId(id);
	return ResponseEntity.ok(response);
}

@GetMapping("/getPosts")
ResponseEntity<List<PostModel>> getPostByEntity(@RequestParam(required = false) Integer userId,
		@RequestParam(required = false) Integer categoryId){
	List<PostModel> response = null;
	if(null!=userId) {
		response = postService.getPostByUser(userId);
	}else {
		response = postService.getPostByCategory(categoryId);
	}
	
	return ResponseEntity.ok(response);
}

@GetMapping("/search/{keyword}")
ResponseEntity<List<PostModel>> getPostByTileContaining(@PathVariable String keyword){
	List<PostModel> response = postService.searchPosts(keyword);
	return ResponseEntity.ok(response);
}


@PostMapping("/image/upload/{postId}")
public ResponseEntity<PostModel> uploadPostImage(
		@RequestPart("file") MultipartFile image,
		@PathVariable Integer postId) throws IOException
		{
			PostModel post = postService.getPostByPostId(postId);
			String fileName = fileService.uploadImage(path, image);
			post.setImageName(fileName);
			PostModel updatedPost = postService.updatePost(post, postId);
			return new ResponseEntity<>(updatedPost, HttpStatus.OK);
		}
@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
	InputStream resource = fileService.downlaodImage(path, imageName);
	response.setContentType(MediaType.IMAGE_PNG_VALUE);
	StreamUtils.copy(resource, response.getOutputStream());
}


}