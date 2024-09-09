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

import com.blog.blog_app.model.UserModel;
import com.blog.blog_app.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/")
public class UserController {

	
@Autowired
private UserService userService;

@PostMapping("/create")
ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel user){
      UserModel response =  userService.createUser(user);
      return new ResponseEntity<>(response,HttpStatus.CREATED); //201
}

@GetMapping("/getAllUsers")
ResponseEntity<List<UserModel>> getAllUsers(){
	List<UserModel> response = userService.getAllUsers();
	 return new ResponseEntity<>(response,HttpStatus.OK);   //200
}

@GetMapping("/getUserById/{id}")
ResponseEntity<UserModel> getUserById(@PathVariable Integer id){
	 UserModel response =  userService.getUserById(id);
	 return new ResponseEntity<>(response,HttpStatus.OK);
}

@PutMapping("/update/{id}")
ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserModel user, @PathVariable Integer id){
	UserModel response =  userService.updateUser(user, id);
    return new ResponseEntity<>(response,HttpStatus.OK); //200
}

@DeleteMapping("/delete/{id}")
ResponseEntity<?> deleteById(@PathVariable Integer id){
      userService.deleteById(id);
	 return new ResponseEntity<>(Map.of("message","Id deleted successfully!"),HttpStatus.OK);
}


}
