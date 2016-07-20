package com.zappy.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zappy.myapp.dao.UserDAO;
import com.zappy.myapp.pojo.User;

@Controller
public class SearchController {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(value="/quickSearchCheckProfile.htm", method = RequestMethod.POST)
	public String quickSearchShowProfile(HttpServletRequest request) {
		User u = (User) userDao.getUserById(request.getParameter("LookupMemberID"));
		request.setAttribute("profile", u);
		return "details";
	}
	
	@RequestMapping(value="/searchByName.htm", method = RequestMethod.POST)
	public void showProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User u = (User) userDao.getUserByUsername(request.getParameter("name"));
		if(u==null) {
//			request.setAttribute("error", "No User Found!");
			JSONObject obj = new JSONObject();
	        obj.put("errormsg", "No User Found!"); 
	        PrintWriter out = response.getWriter();
	        out.println(obj);
		}
		else {
//			request.setAttribute("profile", u);
			JSONObject obj = new JSONObject();
	        obj.put("successmsg", "yes"); 
	        PrintWriter out = response.getWriter();
	        out.println(obj);
//			RequestDispatcher rd = request.getRequestDispatcher("details.jsp");
//            rd.forward(request, response);	
		}
	}
	
	@RequestMapping(value="/searchByName1.htm", method = RequestMethod.POST)
	public String showProfile1(HttpServletRequest request, HttpServletResponse response) {
		User u = (User) userDao.getUserByUsername(request.getParameter("LookupMemberName"));
//		System.out.println(u.getUsername()+"!!!!!!!!!!!!!!!!!!!!!!!!!");
		request.setAttribute("profile", u);
		return "details";
		
	}
	
	@RequestMapping(value="/lookupByNumber.htm", method = RequestMethod.POST)
	public void showIdProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User u = (User) userDao.getUserById(request.getParameter("ID"));
		if(u==null) {
			JSONObject obj = new JSONObject();
	        obj.put("errormsg", "No User Found!"); 
	        PrintWriter out = response.getWriter();
	        out.println(obj);
		}
		else {
			JSONObject obj = new JSONObject();
	        obj.put("successmsg", "yes"); 
	        PrintWriter out = response.getWriter();
	        out.println(obj);
		}
	}
	
	@RequestMapping(value="/lookupByNumber1.htm", method = RequestMethod.POST)
	public String showIdProfile1(HttpServletRequest request, HttpServletResponse response) {
		User u = (User) userDao.getUserById(request.getParameter("LookupMemberID"));
		request.setAttribute("profile", u);
		return "details";
	}
	
	@RequestMapping(value="/quickSearch1.htm", method = RequestMethod.POST)
	public @ResponseBody void quickSearchAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
		List<User> quickSearchResults = new ArrayList<User>();
		quickSearchResults = userDao.getQuickSearch(request.getParameter("SeekingGenderID"), request.getParameter("CountryRegionID"), 
				request.getParameter("stateName"), request.getParameter("cityName"));
		JSONObject obj = new JSONObject();
        PrintWriter out = response.getWriter();
		if(quickSearchResults == null) {
	        obj.put("error", "error"); 	    
		}
		else {
			obj.put("sizeList", quickSearchResults.size());
			
			for(int i=0; i<quickSearchResults.size(); i++) {
				obj.put("user"+String.valueOf(i)+"name", quickSearchResults.get(i).getUsername());
				obj.put("user"+String.valueOf(i)+"DOB", quickSearchResults.get(i).getDateOfBirth());
				obj.put("user"+String.valueOf(i)+"ID", quickSearchResults.get(i).getPersonID());
			}

		}
		out.println(obj);
		}
		catch(Exception e) {
			System.out.println("Putting results into JSON" + e);
		}
	}
	
	
	
	
}
