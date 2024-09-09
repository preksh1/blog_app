package com.blog.blog_app.model;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserModel {
private Integer id;

@NotEmpty
@Size(min = 3, message = "Minimum 3 letters required")
private String name;
@NotEmpty
@Email(message = "Not a valid Email")
private String email;
@NotEmpty
@Size(min = 8, max = 15, message = "Password length should be of 8 to 15 letters")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid password")
private String password;
@NotNull
private String about;

private Set<CommentModel> comments = new HashSet<>();

}
