package com.zappy.myapp.dao;

import java.util.List;

import org.hibernate.Query;

import com.zappy.myapp.pojo.Admin;
import com.zappy.myapp.pojo.Email;

public class AdminDAO extends DAO {

	private void admindao() {
		// TODO Auto-generated method stub
	}
	
	public Admin checkAdmin(String emailAdd, String password) {
		try {
			begin();
            Query q = getSession().createQuery("from Admin a where a.email.emailAdd = ? and a.password=?");
            q.setParameter(0, emailAdd);
            q.setParameter(1, password);
            List list = q.list();
            
            if((list != null) && (list.size()>0)) {
            	Admin admin = (Admin) q.uniqueResult();
	            commit();
	            return admin;
            }
            else {
            	return null;
            }
		} catch(Exception e) {
			rollback();
			System.out.println("Could not log in with " + emailAdd + ", Exception: " + e);
		}
		return null;
	}
	
	public boolean usernameExists(String username) {
		try {
			begin();
			Query q = getSession().createQuery("from Admin a where a.username=:username");
			q.setString("username", username);
			List list = q.list();
			if((list != null) && (list.size()>0)) {
				return true;
			}
			else {
//            	commit();
            	return false;
            }
		} catch(Exception e) {
			rollback();
			System.out.println("Could not judge if "+username+" exists...");
		}
		return false;
	}
	
	public boolean emailExists(String emailAdd) {
		try {
			begin();
			Query q = getSession().createQuery("from Admin a where a.email.emailAdd =:emailAdd");
			q.setString("emailAdd", emailAdd);
			List list = q.list();
			if((list != null) && (list.size()>0)) {
				return true;
			}
			else {
//            	commit();
            	return false;
            }
		} catch(Exception e) {
			rollback();
			System.out.println("Could not judge if "+emailAdd+" exists...");
		}
		return false;
	}

	public Admin createStaff(Admin staff) {
		if(usernameExists(staff.getUsername())) {
			System.out.println("Username already exists!");
			return null;
		}
		else if(emailExists(staff.getEmail().getEmailAdd())) {
			System.out.println("Email Address already exists!");
			return null;
		}
		else {
			System.out.println("inside of adminDao!!!... Staff photo name: "+staff.getPhotoName());
			try {
				begin();
				Email email = new Email(staff.getEmail().getEmailAdd());
				Admin newStaff = new Admin();
				newStaff.setUsername(staff.getUsername());
				newStaff.setPassword(staff.getConfirmPassword());
				newStaff.setEmail(email);
				newStaff.setGender(staff.getGender());
				newStaff.setDateOfBirth(staff.getDateOfBirth());
				newStaff.setCountry(staff.getCountry());
				newStaff.setState(staff.getState());
				newStaff.setCity(staff.getCity());
				newStaff.setFirstName(staff.getFirstName());
				newStaff.setLastName(staff.getLastName());
				newStaff.setDepartment(staff.getDepartment());
				newStaff.setPhotoName(staff.getPhotoName());
				
				email.setPerson(newStaff);
				getSession().save(newStaff);
				commit();
				return newStaff;
	
			} catch (Exception e) {
				rollback();
				System.out.println("AdminDAO create Staff Exception: " + e.getMessage());
			}
			return null;
		}
	}
	
	
}
