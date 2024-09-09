package com.blog.blog_app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog_app.dao.UserDao;
import com.blog.blog_app.entity.User;
import com.blog.blog_app.exception.NoDataFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db logic
		
		User u = userDao.findUserByName(username).orElseThrow(() -> new NoDataFoundException("User", "userId", username));
		return u;
		
	}

}
