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
	
	@Autowired
	UserDAO userDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/deleteMessage.htm", method = RequestMethod.POST)
	public String deleteMessage(HttpServletRequest request) {
//		System.out.println("Inside of deleteMessage Controller!....");
//		System.out.println("Message ID to be deleted is: "+request.getParameter("messageToBeDeleteId"));
		
		messageDAO.deleteMessage(request.getParameter("messageToBeDeleteId"));
		
		return "addSuccess";
	}
	
	@RequestMapping(value= "/sendMessage.htm", method = RequestMethod.POST)
	public String doSubmit(@ModelAttribute("message") Message message, BindingResult result, HttpServletRequest request) {
		validator.validate(message, result);
		if (result.hasErrors()) {
			return "sendMessage";
		}
		
		User fromUser = userDAO.getUserByUsername(message.getFromUser().getUsername());
		User toUser = userDAO.getUserByUsername(message.getToUser().getUsername());
		
		request.getSession().removeAttribute("messageError");
		if(fromUser==null) {
			request.getSession().setAttribute("messageError", "Please enter valid Sender");
			return "sendMessage";
		}
		if(toUser==null) {
//			System.out.println("inside of toUser null..................");
			request.getSession().setAttribute("messageError", "Please enter valid Receiver");
			return "sendMessage";
		}
		
		messageDAO.sendMessage(fromUser, toUser, message.getTitle(), message.getMessage());
//				System.out.println("!!!!!!!!!!!!!message sent");
				User newUser = userDAO.getUserByUsername(fromUser.getUsername());
				request.getSession().setAttribute("user",newUser);
				return "addSuccess";
		
	}
	
	@RequestMapping(value= "/newMessage.htm", method = RequestMethod.GET)
	public String showMessageWindow(Model model, HttpServletRequest request) {
		if(request.getSession().getAttribute("user")==null) {
			return "error";
		}
		if(request.getSession().getAttribute("messageError")!=null) {
			request.getSession().removeAttribute("messageError");
		}
		
		if(request.getParameter("toUser") != null) {
			request.setAttribute("toUser", request.getParameter("toUser"));
//			System.out.println("inside message Controller, get toUser from request scope: " + request.getParameter("toUser"));
		}
		
		model.addAttribute("message", new Message());
		return "sendMessage";
	}
}
