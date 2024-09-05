package com.blog.blog_app.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PostResponse {
List<PostModel> posts;
private Integer pageNum;
private Integer pageSize;
private Integer totalElements;
private Integer totalPages;
private boolean lastPage;
}
