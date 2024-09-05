package com.blog.blog_app.service;

import java.util.List;

import com.blog.blog_app.model.UserModel;

public interface UserService {
UserModel createUser(UserModel user);
List<UserModel> getAllUsers();
UserModel getUserById(Integer id);
UserModel updateUser(UserModel user, Integer id);
void deleteById(Integer id);

}
