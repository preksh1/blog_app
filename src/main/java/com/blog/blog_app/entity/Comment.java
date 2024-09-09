package com.blog.blog_app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Setter
@Getter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String content;

@ManyToOne
private Post post;


@ManyToOne
private User user;


}
