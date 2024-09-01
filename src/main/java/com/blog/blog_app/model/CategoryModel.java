package com.blog.blog_app.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryModel {
	@NotEmpty
	@Size(min = 3, message = "Minimum 3 letters required")	
private String catagoryName;
	
	@NotEmpty
private String categoryDescription;
}
