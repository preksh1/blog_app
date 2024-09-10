package com.blog.blog_app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {
private String email;
private String password;
}
