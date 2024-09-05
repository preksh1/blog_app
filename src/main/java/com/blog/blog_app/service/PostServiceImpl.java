package com.blog.blog_app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.CategoryDao;
import com.blog.blog_app.dao.PostDao;
import com.blog.blog_app.dao.UserDao;
import com.blog.blog_app.entity.Category;
import com.blog.blog_app.entity.Post;
import com.blog.blog_app.entity.User;
import com.blog.blog_app.exception.NoDataFoundException;
import com.blog.blog_app.model.PostModel;
import com.blog.blog_app.model.PostResponse;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	 private ModelMapper modelMapper;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	

	@Autowired
	private CategoryDao categoryDao;
	
	
	@Override
	public PostModel createPost(PostModel model,Integer userId, Integer categoryId) {
		
		User u = userDao.findById(userId).orElseThrow(() -> new NoDataFoundException("User", "userId", userId.toString()));
		Category c = categoryDao.findById(categoryId).orElseThrow(()-> new NoDataFoundException("Category", "No categoryId found", categoryId.toString()));
		
		Post p = modelMapper.map(model, Post.class);
		
		p.setAddedDate(new Date());
		p.setCategory(c);
		p.setUser(u);
		
		return modelMapper.map(postDao.save(p), PostModel.class);
	}


	@Override
	public PostModel updatePost(PostModel model, Integer postId) {
		Post p = postDao.findById(postId).orElseThrow(() -> new NoDataFoundException("Post", "postId", postId.toString()));
		p.setTitle(model.getTitle());
		p.setContent(model.getContent());
		p.setImageName(model.getImageName());
		
		return modelMapper.map(postDao.save(p), PostModel.class);
		
	}


	@Override
	public PostResponse getAllPosts(Integer pageNum, Integer pageSize, String sortBy, String dir) {
		Sort sort=null;
		if(dir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
	      sort = Sort.by(sortBy).descending();
		}
		PostResponse postResponse = new PostResponse();
		Pageable pr = PageRequest.of(pageNum, pageSize, sort);
		Page<Post> page = postDao.findAll(pr);
		List<PostModel> response = new ArrayList<>();
		
		for(Post p:page) {
			
			response.add(modelMapper.map(p, PostModel.class));
		}
		
		postResponse.setPosts(response);
		postResponse.setPageNum(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElements(page.getNumberOfElements());
		postResponse.setTotalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());
		
		return postResponse;
		
	}


	@Override
	public void deletePostById(Integer postId) {
		Post p = postDao.findById(postId).orElseThrow(() -> new NoDataFoundException("Post", "postId", postId.toString()));
		postDao.delete(p);
	}


	@Override
	public List<PostModel> getPostByUser(Integer userId) {
		User u = userDao.findById(userId).orElseThrow(() -> new NoDataFoundException("User", "userId", userId.toString()));
		List<Post> response = postDao.findPostByUser(u);
		List<PostModel> list = new ArrayList<>();
		for(Post p : response) { list.add(modelMapper.map(p, PostModel.class)); }
		return list;
	}


	@Override
	public List<PostModel> getPostByCategory(Integer categoryId) {
		Category c = categoryDao.findById(categoryId).orElseThrow(()-> new NoDataFoundException("Category", "No categoryId found", categoryId.toString()));
		List<Post> response = postDao.findPostByCategory(c);
		List<PostModel> list = new ArrayList<>();
		for(Post p : response) { list.add(modelMapper.map(p, PostModel.class)); }
		return list;
	}


	@Override
	public PostModel getPostByPostId(Integer postId) {
		Post p = postDao.findById(postId).orElseThrow(() -> new NoDataFoundException("Post", "postId", postId.toString()));
		return modelMapper.map(p, PostModel.class);
	}


	@Override
	public List<PostModel> searchPosts(String keyword) {
		List<Post> response = postDao.getPostByTitleContaining(keyword);
		List<PostModel> list = new ArrayList<>();
		for(Post p : response) { list.add(modelMapper.map(p, PostModel.class)); }
		return list;
	}

	

}