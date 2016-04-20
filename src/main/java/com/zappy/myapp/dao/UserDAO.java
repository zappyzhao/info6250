package com.zappy.myapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.zappy.myapp.pojo.Email;
import com.zappy.myapp.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean updateProfile(long personID, String aboutMe, String personality, String aboutLookingFor, 
									float height, float weight, String bodyStyle, String hairColor, String eyeColor,
									String religion, String hometown, String educationLevel, String occupation, 
									String favoriteActivity, String favoriteFood, String drinking, String smoking) {
		try {
			begin();
			Query q = getSession().createQuery("update User u set u.aboutMe=:aboutMe, u.personality=:personality, "
					+ "u.aboutLookingFor=:aboutLookingFor, u.height=:height, u.weight=:weight, u.bodyStyle=:bodyStyle, "
					+ "u.hairColor=:hairColor, u.eyeColor=:eyeColor, u.religion=:religion, u.hometown=:hometown, "
					+ "u.educationLevel=:educationLevel, u.occupation=:occupation, u.favoriteActivity=:favoriteActivity, "
					+ "u.favoriteFood=:favoriteFood, u.drinking=:drinking, u.smoking=:smoking where u.personID=:personID");
			q.setParameter("aboutMe", aboutMe);
			q.setParameter("personality", personality);
			q.setParameter("aboutLookingFor", aboutLookingFor);
			q.setParameter("height", height);
			q.setParameter("weight", weight);
			q.setParameter("bodyStyle", bodyStyle);
			q.setParameter("hairColor", hairColor);
			q.setParameter("eyeColor", eyeColor);
			q.setParameter("religion", religion);
			q.setParameter("hometown", hometown);
			q.setParameter("educationLevel", educationLevel);
			q.setParameter("occupation", occupation);
			q.setParameter("favoriteActivity", favoriteActivity);
			q.setParameter("favoriteFood", favoriteFood);
			q.setParameter("drinking", drinking);
			q.setParameter("smoking", smoking);
			q.setParameter("personID", personID);
			
//			Query q = getSession().createQuery("update User u set u.aboutMe=1, u.personality=2, "
//					+ "u.aboutLookingFor=3, u.height=4, u.weight=5, u.bodyStyle=6, "
//					+ "u.hairColor=7, u.eyeColor=8, u.religion=9, u.hometown=10, "
//					+ "u.educationLevel=11, u.occupation=12, u.favoriteActivity=13, "
//					+ "u.favoriteFood=14, u.drinking=15, u.smoking=16 where u.personID=1");
			int ifUpdate = q.executeUpdate();
			
			if(ifUpdate!=0) {
				commit();
				return true;
			}
			else {
				System.out.println("Can not update Profile!");
            }
		} catch(Exception e) {
			rollback();
			System.out.println("Could not update profile of "+personID);
		}
		return false;
	}
	
	public boolean usernameExists(String username) {
		try {
			begin();
			Query q = getSession().createQuery("from User u where u.username=:username");
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
			Query q = getSession().createQuery("from User u where u.email.emailAdd =:emailAdd");
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
	
	public User getUserByUsername(String username) {
		try {
			begin();
            Query q = getSession().createQuery("from User u where u.username=:username");
            q.setParameter("username", username);
            List list = q.list();
            
            if((list != null) && (list.size()>0)) {
            	User user = (User) q.uniqueResult();
	            commit();
	            return user;
            }
            else {
            	return null;
            }
		} catch(Exception e) {
			rollback();
			System.out.println("Could not get User of name " + username + ", Exception: " + e);
		}
		return null;
	}
	
	public User checkUser(String emailAdd, String password) {
		
		try {
			begin();
            Query q = getSession().createQuery("from User u where u.email.emailAdd = ? and u.password=?");
            q.setParameter(0, emailAdd);
            q.setParameter(1, password);
            List list = q.list();
            
            if((list != null) && (list.size()>0)) {
            	User user = (User) q.uniqueResult();
	            commit();
	            return user;
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
	
	public User createUser(String username, String emailAdd, String password, String gender, String dateOfBirth, String city, String state, String country) throws Exception  {
		try {
			if(usernameExists(username)) {
				System.out.println("Username already exists!");
				return null;
			}
			else if(emailExists(emailAdd)) {
				System.out.println("Email Address already exists!");
				return null;
			}
			else {
				begin();
				Email email = new Email(emailAdd);
				User user = new User();
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password);
				user.setGender(gender);
				user.setDateOfBirth(dateOfBirth);
				user.setCity(city);
				user.setState(state);
				user.setCountry(country);
				
				email.setPerson(user);
				
				getSession().save(user);
				commit();
				return user;
			}
			
		} catch (Exception e) {
			rollback();
			System.out.println("UserDAO create User Exception: " + e.getMessage());
		}
		return null;
	}

}
