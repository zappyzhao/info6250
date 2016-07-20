package com.zappy.myapp.dao;


import org.hibernate.Query;

import com.zappy.myapp.pojo.Message;
import com.zappy.myapp.pojo.User;

public class MessageDAO extends DAO {
	
	private void messagedao() {
		// TODO Auto-generated method stub
	}
	
	public void deleteAllMessages(long id) {
		try {
			begin();
			Query q = getSession().createQuery("delete from Message where toUser=:toUser");
			q.setParameter("toUser", id);
			int toBeDelete = q.executeUpdate();
			if(toBeDelete!=0) {
				commit();				
			}
		} catch (Exception e) {
			rollback();
			System.out.println("MessageDAO Exception when deleting All Messages: " + e);
		}
	}
	
	public boolean deleteMessage(String messageId) {
		try {
			begin();
			long id = Long.parseLong(messageId);
			Query q = getSession().createQuery("delete from Message where id=:id");
			q.setParameter("id", id);
			int toBeDelete = q.executeUpdate();
			if(toBeDelete!=0) {
				commit();
				return true;				
			}
		} catch (Exception e) {
			rollback();
			System.out.println("MessageDAO Exception when deleting Message: " + e);
		}
		return false;
	}
	
	public boolean sendMessage(User fromUser, User toUser, String title, String message) {
		try {
			begin();
			Message newMessage = new Message(title, message, fromUser, toUser);
//			if(getCurrentSession().isOpen())
//				getCurrentSession().save(newMessage);
//			else
				getSession().save(newMessage);
			commit();
			return true;
		} catch (Exception e) {
			rollback();
			System.out.println("MessageDAO Exception when sending Message: " + e);
		}
		return false;
	}
	
	
}
