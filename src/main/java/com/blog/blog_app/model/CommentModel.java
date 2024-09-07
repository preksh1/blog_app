package com.blog.blog_app.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentModel {
private Integer id;

private String content;

private Integer userId;
}
