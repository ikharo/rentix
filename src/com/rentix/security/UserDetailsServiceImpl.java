package com.rentix.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rentix.beans.Users;
import com.rentix.dao.UsersDAO;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UsersDAO userDao;

	
	public UserDetailsServiceImpl(UsersDAO userDao){
		this.setUserDao(userDao);
		
	}


	public UsersDAO getUserDao() {
		return userDao;
	}


	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private org.springframework.security.core.userdetails.User makeUser(Users user) {
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user
				.getPassword(), true, true, true, true,makeGrantedAuthorities(user));
		
	
	}
	
	
	 private GrantedAuthority[] makeGrantedAuthorities(Users user) {
		 	      System.out.println("make grantedAuthorities");
		 	         GrantedAuthority[] result = new GrantedAuthority[1];
		 	         
		 	            result[0] = new GrantedAuthorityImpl("admin");
		 	         
		 	         return result;
		 	     }
	
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		
		Users user = userDao.findByUsername(userName).get(0);
		
		return makeUser(user);
	}


}
