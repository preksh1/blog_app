package com.blog.blog_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataFoundException extends RuntimeException {
private String resourceName;
private String fieldName;
private String fieldValue;
public NoDataFoundException(String resourceName, String fieldName, String fieldValue) {
	super(String.format("%s resource with %s field: %s not found", resourceName, fieldName, fieldValue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
}


}
