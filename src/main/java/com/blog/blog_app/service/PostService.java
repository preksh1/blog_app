package com.blog.blog_app.service;

import java.util.List;

import com.blog.blog_app.model.PostModel;
import com.blog.blog_app.model.PostResponse;

public interface PostService {
PostModel createPost(PostModel model, Integer userId, Integer categoryId);

PostModel updatePost(PostModel model, Integer postId);

PostResponse getAllPosts(Integer pageNum, Integer pageSize, String sortBy, String dir);

void deletePostById(Integer id);

List<PostModel> getPostByUser(Integer userId);
List<PostModel> getPostByCategory(Integer categoryId);
PostModel getPostByPostId(Integer postId);
List<PostModel> searchPosts(String keyword);
}
