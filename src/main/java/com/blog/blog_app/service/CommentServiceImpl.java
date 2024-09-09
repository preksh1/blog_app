package com.blog.blog_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.CommentDao;
import com.blog.blog_app.dao.PostDao;
import com.blog.blog_app.dao.UserDao;
import com.blog.blog_app.entity.Comment;
import com.blog.blog_app.entity.Post;
import com.blog.blog_app.entity.User;
import com.blog.blog_app.exception.NoDataFoundException;
import com.blog.blog_app.model.CommentModel;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentModel createComment(CommentModel model, Integer userId, Integer postId) {
		User u = userDao.findById(userId).orElseThrow(() -> new NoDataFoundException("User", "userId", userId.toString()));
		Post p = postDao.findById(postId).orElseThrow(() -> new NoDataFoundException("Post", "postId", postId.toString()));
		Comment c = new Comment();
		c.setContent(model.getContent());
		c.setPost(p);
		c.setUser(u);
		
		return this.modelMapper.map(commentDao.save(c), CommentModel.class);
	}

	@Override
	public void deleteCommentById(Integer commentId) {
		Comment c = commentDao.findById(commentId).orElseThrow(() -> new NoDataFoundException("User", "userId", commentId.toString()));
		
		commentDao.delete(c);
		
	}
	
	
}            
