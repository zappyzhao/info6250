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

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;



@Controller
@RequestMapping("/addUser.htm")
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
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "index";
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {

		return "index";
	}
}
