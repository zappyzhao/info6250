package com.zappy.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;

@Controller
@RequestMapping("/details.htm")
public class ViewProfileController {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showProfile(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
//		System.out.println(u.getHometown()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		request.setAttribute("profile", u);
		
		return "details";
	}
}
