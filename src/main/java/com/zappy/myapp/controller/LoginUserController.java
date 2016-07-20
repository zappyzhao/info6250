package com.zappy.myapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
import com.zappy.myapp.pojo.Message;
import com.zappy.myapp.pojo.User;
import com.zappy.myapp.validator.UserLoginValidator;

@Controller
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
	
	@RequestMapping(value="/loginUser.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "index";
		}

		if (userDao.checkUser(user.getEmail().getEmailAdd(), user.getPassword()) != null) {
			User loginUser = userDao.checkUser(user.getEmail().getEmailAdd(), user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			
			// ModelAndView mv=new ModelAndView("addSuccess","user",user);
			// return mv;

			// getMessages
			Set<Message> messageIreceived = loginUser.getMessageIreceived();
			List<Message> messageList = new ArrayList<Message>(messageIreceived);
			Collections.sort(messageList, new Comparator<Message>() {

				@Override
				public int compare(Message o1, Message o2) {
					// TODO Auto-generated method stub
					int id1=(int) o1.getId();
					int id2=(int) o2.getId();
					return id2-id1;
				}
				
			});
			session.setAttribute("messageList", messageList);

			return "addSuccess";
		}

		else {
			return "index";
		}
			
	}
	
	@RequestMapping(value="/loginUser1.htm", method = RequestMethod.POST)
	protected @ResponseBody String doSubmitAction1(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "error";
		}

		if (userDao.checkUser(user.getEmail().getEmailAdd(), user.getPassword()) != null) {
			
			return "success";
		}

		else {
			return "error";
		}
			
	}
	
	@RequestMapping(value="/loginUser.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			User thisUser = (User)session.getAttribute("user");
//			System.out.println(thisUser.getMessageIreceived().size());
//			session.setAttribute("messageList", thisUser.getMessageIreceived());
			// getMessages
						Set<Message> messageIreceived = thisUser.getMessageIreceived();
						List<Message> messageList = new ArrayList<Message>(messageIreceived);
						Collections.sort(messageList, new Comparator<Message>() {

							@Override
							public int compare(Message o1, Message o2) {
								// TODO Auto-generated method stub
								int id1=(int) o1.getId();
								int id2=(int) o2.getId();
								return id2-id1;
							}
							
						});
						session.setAttribute("messageList", messageList);
						
			return "addSuccess";
		}
		else
			return "index";
	}

}
