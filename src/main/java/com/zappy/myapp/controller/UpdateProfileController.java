package com.zappy.myapp.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.zappy.myapp.validator.ProfileValidator;

@Controller
@RequestMapping("/updateProfile.htm")
public class UpdateProfileController {
	@Autowired
	@Qualifier("profileValidator")
	ProfileValidator validator;
	
	@Autowired
	UserDAO userDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doSubmit(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "updateProfile";
		}
		try {
			boolean flag = false;
			if(request.getSession().getAttribute("user") != null) {
				User userInSession = (User) request.getSession().getAttribute("user");
//				if(userDao.updateProfile(userInSession.getUsername())) {
//					return "details";
//				}
				flag=userDao.updateProfile(userInSession.getPersonID(), user.getAboutMe(), user.getPersonality(),
										user.getAboutLookingFor(), user.getHeight(), user.getWeight(), user.getBodyStyle(),
										user.getHairColor(), user.getEyeColor(), user.getReligion(), user.getHometown(),
										user.getEducationLevel(), user.getOccupation(), user.getFavoriteActivity(), 
										user.getFavoriteFood(), user.getDrinking(), user.getSmoking());
				if(flag) { 
					User newUser = (User) userDao.checkUser(userInSession.getEmail().getEmailAdd(), userInSession.getPassword());
					request.setAttribute("profile", newUser);
					request.getSession().setAttribute("user",newUser);
					return "details";
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "updateProfile";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {
		return "updateProfile";
	}
}
