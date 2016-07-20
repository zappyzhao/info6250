package com.zappy.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;



@Controller
public class AddUserController {

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	UserDAO userDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/addUser1.htm", method = RequestMethod.POST)
	protected @ResponseBody String doSubmitAction1(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "error";
		}
		
		if(request.getSession().getAttribute("user") != null) {
			return "success";
		}
		
//		System.out.println(request.getAttribute("password"));

		try {
			if(userDao.emailExists(user.getEmail().getEmailAdd())){
				return "warning1";
			}
			
			if(userDao.usernameExists(user.getUsername())) {
				return "warning2";
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "success";
	}
	
	@RequestMapping(value="/addUser.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "index";
		}
		
		if(request.getSession().getAttribute("user") != null) {
			return "addSuccess";
		}

		try {
			User loginUser = userDao.createUser(user.getUsername(), user.getEmail().getEmailAdd(), user.getPassword(), user.getGender(), user.getDateOfBirth(), user.getCity(), user.getState(), user.getCountry());
			
			if(loginUser==null) return "index";
			
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addSuccess";
	}
	
	@RequestMapping(value="/addUser.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {
		
		return "index";
	}
}
