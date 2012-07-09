package rentix.managedbeans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import com.rentix.dao.UsersDAO;
import com.rentix.beans.Proyecto;
import com.rentix.beans.Scene;
import com.rentix.beans.Users;

@ManagedBean(name="RegisterMB")
@RequestScoped
public class RegisterManagedBean {

	private Users users;
	private String passwordConfirm;
	private String captchaLetters;
	@ManagedProperty(value = "#{UsersDAO}")
	private UsersDAO userDAO;
	
	@PostConstruct
	public void initComponents(){
		users = new Users();
	}
	
	public String saveUser(){
		
		userDAO.save(users);
		return "";
	}
	public void saveUser(ActionEvent evt){
		
		
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}

	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String getCaptchaLetters() {
		return captchaLetters;
	}

	public void setCaptchaLetters(String captchaLetters) {
		this.captchaLetters = captchaLetters;
	}
}
