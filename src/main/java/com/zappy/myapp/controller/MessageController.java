package com.zappy.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zappy.myapp.dao.MessageDAO;
import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.Message;
import com.zappy.myapp.pojo.User;
import com.zappy.myapp.validator.MessageValidator;

@Controller
public class MessageController {
	
	@Autowired
	@Qualifier("messageValidator")
	MessageValidator validator;
	
	@Autowired
	MessageDAO messageDAO;
	UserDAO userDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value= "/sendMessage.htm", method = RequestMethod.POST)
	public String doSubmit(@ModelAttribute("message") Message message, BindingResult result, HttpServletRequest request) {
		validator.validate(message, result);
		if (result.hasErrors()) {
			return "sendMessage";
		}
		
		try {
			User fromUser = (User) request.getSession().getAttribute("user");
			User toUser = userDAO.getUserByUsername(message.getToUser().getUsername());
			if(messageDAO.sendMessage(fromUser, toUser, message.getTitle(), message.getMessage())) {
				System.out.println("!!!!!!!!!!!!!message sent");
				User newUser = userDAO.getUserByUsername(fromUser.getUsername());
				request.getSession().setAttribute("user",newUser);
				return "addSuccess";
			}
		} catch (Exception e) {
			System.out.println("Send Message in controller Exception: " + e.getMessage());
		}
		
		System.out.println("from message controller: "+message.getFromUser().getUsername()+", "+message.getMessage());
		return null;
		
	}
	
	@RequestMapping(value= "/newMessage.htm", method = RequestMethod.GET)
	public String showMessageWindow(Model model) {
		model.addAttribute("message", new Message());
		return "sendMessage";
	}
}
