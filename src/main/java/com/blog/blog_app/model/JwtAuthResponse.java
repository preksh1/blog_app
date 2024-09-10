package com.blog.blog_app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
private String token;
private String username;
}
