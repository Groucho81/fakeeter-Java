package view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.UserController;
import model.User;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



@Named("loginMb")
@SessionScoped
public class LogIn implements Serializable{
	private static final long serialVersionUID = 791515424619865689L;
	
	@Inject
	private UserController usrCont;
	
	private String userName;
	private String password;
	private String email;
	private User user=new User();

	public String checkLogin() {
		try {
			System.out.println("User = "+user.toString());
			userName=user.getUserName();
			password=user.getPassword();
			this.user = usrCont.login(userName, password);
		}catch(Exception e){
			System.out.println("Error de logueo");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Nombre de Usuario o Password incorrecto",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index?";
		}
		
		this.userName = null;
		this.password = null;
		this.email=null;
		if (this.user != null){
			return "home?";
		}else{
			return "index?";
		}
	}
	
	public String register(){
		if (usrCont.isValidUser(userName, email)) {
			User u = new User(userName,password,email);
			usrCont.create(u);
			return "index?faces-redirect=true";
		}
		return "register?faces-redirect=true";
		
	}
	
	public boolean isLogged(){
		Boolean b= this.user.getUserName() != null;
		//System.out.println("Se ejecuta el chequeo del login "+String.valueOf(b)+" User="+this.user.getUserName());
		return b;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getEmail() {
		return userName;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	
}
