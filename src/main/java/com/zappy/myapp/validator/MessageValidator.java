package com.zappy.myapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zappy.myapp.pojo.Message;


public class MessageValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Message.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromUser.username", "error.invalid.fromUser.username", "fromUser Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toUser.username", "error.invalid.toUser.username", "toUser Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Subject Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.message", "Message Content Required");
	}

}
