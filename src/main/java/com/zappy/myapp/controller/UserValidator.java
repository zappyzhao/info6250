package com.zappy.myapp.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zappy.myapp.pojo.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) obj;
//		String regExp = "[^\\dA-Za-z ]";
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAdd", "error.invalid.email.emailAdd", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "error.invalid.dateOfBirth", "Birthday Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.invalid.city", "City Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "error.invalid.state", "State Required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "error.invalid.country", "Country Required");
        
        
        
	}

}
