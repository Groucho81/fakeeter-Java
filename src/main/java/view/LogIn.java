package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.UserController;
import model.User;


@Named("loginMb")
@SessionScoped
public class LogIn implements Serializable{
	private static final long serialVersionUID = 791515424619865689L;
	
	@Inject
	private UserController usrCont;
	private String usr=null;
	private String pwd=null;
	private String email=null;
	
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String checkLogin() {
		System.out.println("###### ACA ESTOY: Check in ejecutado");
		if(usrCont.Login(usr, pwd)) {
			return "home?faces-redirect=true";
		}else {
			return "register?faces-redirect=true";
			
		}
	}
	public String register(){
		if (usrCont.isValidUser(usr, email)) {
			User u = new User(usr,pwd,email);
			usrCont.create(u);
			return "index?faces-redirect=true";
		}
		return "register?faces-redirect=true";
		
	}
	
	
}
