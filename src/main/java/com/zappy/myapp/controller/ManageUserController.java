package com.zappy.myapp.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zappy.myapp.dao.MessageDAO;
import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;

@Controller
public class ManageUserController {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	MessageDAO messageDao;
	
	@RequestMapping(value="/manageUser.htm", method=RequestMethod.GET)
	public String InitializePage(HttpServletRequest request) {
		if(request.getSession().getAttribute("admin")==null) {
			return "error";
		}
		int size = userDao.getUserNumber();
		int userPerPage = 1;
		int pageNumber = 0;
		if(size%userPerPage != 0)
			pageNumber = size/userPerPage + 1;
		else
			pageNumber = size/userPerPage;
//		System.out.println(pageNumber + "This is page number!!!!!!!!!!!");
		
		request.getSession().setAttribute("pageNumber", pageNumber);

		request.getSession().setAttribute("userList", userDao.getAllUsers(1, userPerPage));
		return "manageUser";
	}
	
	@RequestMapping(value="getPage.htm", method=RequestMethod.POST)
	public String changePage(HttpServletRequest request) {
		
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		
		int size = userDao.getUserNumber();
		int userPerPage = 1;
		int pageNumber = 0;
		if(size%userPerPage != 0)
			pageNumber = size/userPerPage + 1;
		else
			pageNumber = size/userPerPage;
//		System.out.println(pageNumber + "This is page number!!!!!!!!!!!");
		
		request.getSession().setAttribute("pageNumber", pageNumber);

		request.getSession().setAttribute("userList", userDao.getAllUsers(page, userPerPage));
		return "manageUser";
	}
	
	@RequestMapping(value="deleteUser.htm", method=RequestMethod.POST)
	public String deleteUser(HttpServletRequest request, Model model) {
		
		if(!userDao.deleteUser(request.getParameter("userName"))) {
			model.addAttribute("errorDelete","Can not delete User!");
		}
		else {
//			User userToBeDelete = userDao.getUserByUsername(request.getParameter("userName"));
//			System.out.println("!!!!!!!!!!!!!!!...........");
//			System.out.println(userToBeDelete.getUsername());
//			System.out.println(userToBeDelete.getMessageIreceived().size());
//			if(userToBeDelete.getMessageIreceived()!=null) {
//				
//				messageDao.deleteAllMessages(userToBeDelete.getPersonID());
//			}

			userDao.deleteUser(request.getParameter("userName"));
			//After Delete
			int size = userDao.getUserNumber();
			int userPerPage = 1;
			int pageNumber = 0;
			if(size%userPerPage != 0)
				pageNumber = size/userPerPage + 1;
			else
				pageNumber = size/userPerPage;
	//		System.out.println(pageNumber + "This is page number!!!!!!!!!!!");
			
			request.getSession().setAttribute("pageNumber", pageNumber);
	
			request.getSession().setAttribute("userList", userDao.getAllUsers(1, userPerPage));
		}
		return "manageUser";
	}
	
}
