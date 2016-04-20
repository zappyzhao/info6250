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
import org.springframework.web.servlet.ModelAndView;

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;
import com.zappy.myapp.validator.UserLoginValidator;

@Controller
@RequestMapping("/loginUser.htm")
public class LoginUserController {
	
	@Autowired
	@Qualifier("userLoginValidator")
	UserLoginValidator validator;
	
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
			if(userDao.checkUser(user.getEmail().getEmailAdd(), user.getPassword()) != null) {
				User loginUser = userDao.checkUser(user.getEmail().getEmailAdd(), user.getPassword());
				HttpSession session = request.getSession();
				session.setAttribute("user", loginUser);
//				ModelAndView mv=new ModelAndView("addSuccess","user",user);
//				return mv;
				return "addSuccess";
			}
			

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			return "addSuccess";
		}
		else
			return "index";
	}

}
