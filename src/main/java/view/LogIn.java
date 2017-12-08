package view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import auth.AuthMb;
import controller.UserController;
import model.User;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@Named("loginMb")
public class LogIn implements Serializable{
	private static final long serialVersionUID = 791515424619865689L;
	
	@Inject
	private UserController usrCont;
	
	@Inject
	private AuthMb authMb;
	
	private User user=new User();

	public String checkLogin() {
		try {
			this.user = usrCont.login(user.getUserName(), user.getPassword());
		}catch(Exception e){
			System.out.println("Error de logueo");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Nombre de Usuario o Password incorrecto",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index?";
		}
		
		if (this.user != null){
			authMb.setUser(user);
			return "home?faces-redirect=true";
		}else{
			return "index?";
		}
	}
	
	public String register(){
		try{
			if (usrCont.isValidUser(user.getUserName(), user.getEmail())) {
				usrCont.create(this.user);
				return "index?faces-redirect=true";
			}
			return "register?faces-redirect=true";	
		}catch(Exception e){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Los datos ingresados no son validos",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "register?";
		}
	}
	
	public boolean isLogged(){
		Boolean b= this.user.getUserName() != null;
		return b;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
