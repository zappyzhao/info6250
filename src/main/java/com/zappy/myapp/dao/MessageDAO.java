package com.zappy.myapp.dao;

import com.zappy.myapp.pojo.Message;
import com.zappy.myapp.pojo.User;

public class MessageDAO extends DAO {
	
	private void messagedao() {
		// TODO Auto-generated method stub
	}
	
	public boolean sendMessage(User fromUser, User toUser, String title, String message) {
		try {
			begin();
			Message newMessage = new Message(title, message, fromUser, toUser);
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
