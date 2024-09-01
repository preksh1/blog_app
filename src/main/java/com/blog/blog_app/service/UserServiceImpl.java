package com.blog.blog_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.UserDao;
import com.blog.blog_app.entity.User;
import com.blog.blog_app.exception.NoDataFoundException;
import com.blog.blog_app.model.UserModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserModel createUser(UserModel user) {
		User u = modelToEntity(user);
		User response = userDao.save(u);
	    UserModel model = this.modelMapper.map(u, UserModel.class);
		return model;
	}
	@Override
	public List<UserModel> getAllUsers() {
		List<UserModel> response=new ArrayList<>();
		List<User> list = userDao.findAll();
		
		/*
		 * for(User u : list) { response.add(entityToModel(u)); }
		 */
		
		list.stream().map((u) -> {return response.add(modelMapper.map(u, UserModel.class));}).collect(Collectors.toList());
		
		return response;
	}
	
	private User modelToEntity(UserModel user) {
		User u = new User();
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAbout(user.getAbout());
		return u;
	}
	
	private UserModel entityToModel(User user) {
		UserModel model =new UserModel();
		model.setId(user.getId());
		  model.setName(user.getName());
			model.setEmail(user.getEmail());
			model.setPassword(user.getPassword());
			model.setAbout(user.getAbout());
			return model;
	}
	@Override
	public UserModel getUserById(Integer id) {
		User u = userDao.findById(id).orElseThrow(() -> new NoDataFoundException("User", "userId", id.toString()));
		return modelMapper.map(u, UserModel.class);
	}
	@Override
	public UserModel updateUser(UserModel user, Integer id) {
		User u = userDao.findById(id).orElseThrow(() -> new NoDataFoundException("User", "userId", id.toString()));
		
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAbout(user.getAbout());
		
		User res = userDao.save(u);
		
		return modelMapper.map(res, UserModel.class);
		
	}
	@Override
	public void deleteById(Integer id) {
		User u = userDao.findById(id).orElseThrow(() -> new NoDataFoundException("User", "userId", id.toString()));
		
		userDao.delete(u);
		
	}

}
