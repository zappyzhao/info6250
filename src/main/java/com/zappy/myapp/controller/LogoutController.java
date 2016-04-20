package com.zappy.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zappy.myapp.pojo.User;

@Controller
@RequestMapping("/logout.htm")
public class LogoutController {
	@RequestMapping(method = RequestMethod.GET)
	public String logout(@ModelAttribute("user") User user, HttpServletRequest request) {
		if(request.getSession().getAttribute("user")!=null) {
			request.getSession().removeAttribute("user");
		}
		return "index";
	}
}
