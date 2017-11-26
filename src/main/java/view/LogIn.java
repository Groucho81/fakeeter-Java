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
	
	private User user=new User();

	public String checkLogin() {
		try {
			System.out.println("********** User = "+user.getUserName()+" "+user.getPassword());
			this.user = usrCont.login(user.getUserName(), user.getPassword());
		}catch(Exception e){
			System.out.println("Error de logueo");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Nombre de Usuario o Password incorrecto",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index?";
		}
		
		if (this.user != null){
			return "home?faces-redirect=true";
		}else{
			return "index?";
		}
	}
	
	public String register(){
		System.out.println("email:"+user.getEmail()+" user:"+user.getUserName()+" password:"+user.getPassword());
		try{
			if (usrCont.isValidUser(user.getUserName(), user.getEmail())) {
				usrCont.create(this.user);
				return "index?faces-redirect=true";
			}
			return "register?faces-redirect=true";	
		}catch(Exception e){
			System.out.println("Error de registracion");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Los datos ingresados no son validos",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "register?";
		}
	}
	public String logout() {
		user = new User();
		return "index?";
	}
	public boolean isLogged(){
		Boolean b= this.user.getUserName() != null;
		//System.out.println("Se ejecuta el chequeo del login "+String.valueOf(b)+" User="+this.user.getUserName());
		return b;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
