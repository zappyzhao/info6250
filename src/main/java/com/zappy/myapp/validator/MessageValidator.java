package com.zappy.myapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.Message;


public class MessageValidator implements Validator {
	

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Message.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Message ms = (Message)obj;
		// TODO Auto-generated method stub
		String charExp = "[A-Za-z0-9\\_!&%#\\(\\)\\s]*";
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromUser.username", "error.invalid.fromUser.username", "fromUser Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toUser.username", "error.invalid.toUser.username", "toUser Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Subject Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.message", "Message Content Required");
		
		if(!ms.getMessage().matches(charExp)) {
			errors.rejectValue("message", "error.invalid.message", "Please enter valid Message Content");
		}
//
//		if(userDao.getUserByUsername(ms.getToUser().getUsername())==null) {
//			errors.rejectValue("toUser.username", "error.invalid.toUser.username", "Please enter valid User Receiver");
//		}
//		if(userDao.getUserByUsername(ms.getFromUser().getUsername())==null) {
//			errors.rejectValue("fromUser.username", "error.invalid.fromUser.username", "Please enter valid User Sender");
//		}
	}

}
