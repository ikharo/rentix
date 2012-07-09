package com.rentix.service;

import com.rentix.dao.UsersDAO;

public class AuthenticationServiceImpl {
	
	private UsersDAO userDAO;


	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}



}
