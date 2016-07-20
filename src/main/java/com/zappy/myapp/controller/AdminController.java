package com.zappy.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zappy.myapp.dao.AdminDAO;
import com.zappy.myapp.pojo.Admin;
import com.zappy.myapp.validator.AdminValidator;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminValidator")
	AdminValidator validator;
	
	@Autowired
	AdminDAO adminDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value="/loginAdmin1.htm", method = RequestMethod.POST)
	protected @ResponseBody String doSubmitAction1(@ModelAttribute("admin") Admin admin, HttpServletRequest request) throws Exception {
		if(adminDao.checkAdmin(admin.getEmail().getEmailAdd(), admin.getPassword())!=null) {
			return "success";
		}
		else return "error";
	}
	
	@RequestMapping(value="/loginAdmin.htm", method = RequestMethod.POST)
	protected String loginAdmin(@ModelAttribute("admin") Admin admin, HttpServletRequest request) throws Exception {
		Admin loginAdmin = adminDao.checkAdmin(admin.getEmail().getEmailAdd(), admin.getPassword());
		HttpSession session = request.getSession();
		session.setAttribute("admin", loginAdmin);
		
		return "staffWorkPage";
	}
	
	@RequestMapping(value="/loginAdmin.htm", method = RequestMethod.GET)
	protected String loginAdminGET(@ModelAttribute("admin") Admin admin, HttpServletRequest request) throws Exception {
		if(request.getSession().getAttribute("admin")==null) {
			return "error";
		}
		return "staffWorkPage";
	}
	
	
	@RequestMapping(value="/staffLogout.htm", method = RequestMethod.GET)
	public String staffLogOut(HttpServletRequest request, Model model) {
//		if(request.getSession().getAttribute("admin")==null) {
//			return "error";
//		}
		request.getSession().removeAttribute("admin");
		model.addAttribute("admin", new Admin());
		return "adminPage";
	}

	@RequestMapping(value="/adminModel.htm", method = RequestMethod.GET)
	public String goToAdminModel(Model model, HttpServletRequest request) {
//		if(request.getSession().getAttribute("admin")==null) {
//			return "error";
//		}
		model.addAttribute("admin", new Admin());
		return "adminPage";
	}
	
	
	@RequestMapping(value="/addStaffNew1.htm", method = RequestMethod.POST)
	public @ResponseBody String addNewStaff1(@ModelAttribute("admin") Admin admin, BindingResult result, HttpServletRequest request) {
		validator.validate(admin, result);
		if (result.hasErrors()) {
			return "error";
		}
		if(request.getSession().getAttribute("admin") != null) {
			return "success";
		}

			if(adminDao.emailExists(admin.getEmail().getEmailAdd())){
				return "warning1";
			}
			
			if(adminDao.usernameExists(admin.getUsername())) {
				return "warning2";
			}
			
		//check two password
		if(!admin.getPassword().equals(admin.getConfirmPassword())) {
			return "notsame";
		}
			
			
		return "success";
	}
	
	@RequestMapping(value="/addStaffNew.htm", method = RequestMethod.POST)
	public String addNewStaff(@ModelAttribute("admin") Admin admin, BindingResult result, HttpServletRequest request) {
		validator.validate(admin, result);
		if (result.hasErrors()) {
			return null;
		}
		if(request.getSession().getAttribute("admin") != null) {
			return "staffWorkPage";
		}
		
		try {
//			System.out.println("Yes come in!!!!! ........."+admin.getPhotoName());
			if(admin.getPhoto() != null) {
			
				admin.setPhotoName(admin.getPhoto().getOriginalFilename());
                byte[] bytes = admin.getPhoto().getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File("/Users/ZappyZhao/STS/WebToolsProject/src/main/webapp/resources/img/" + admin.getPhotoName())));
                buffStream.write(bytes);
                buffStream.close();
//                System.out.println("You have successfully uploaded " + admin.getPhotoName());
			}
			Admin staff = adminDao.createStaff(admin);
			if(staff==null) {
				return null;
			}
			HttpSession session = request.getSession();
			session.setAttribute("admin", staff);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "staffWorkPage";
	}
}
