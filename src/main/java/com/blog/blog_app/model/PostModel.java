package com.blog.blog_app.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostModel {
private Integer postId;
private String title;
private String content;
private String imageName;
private Date addedDate;

private UserModel user;
private CategoryModel category;
}
