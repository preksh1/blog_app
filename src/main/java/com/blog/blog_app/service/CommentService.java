package com.blog.blog_app.service;

import com.blog.blog_app.model.CommentModel;

public interface CommentService {
CommentModel createComment(CommentModel model, Integer userId, Integer postId);
void deleteCommentById(Integer commentId);
}
