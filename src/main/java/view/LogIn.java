package view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import auth.AuthMb;
import controller.ImageController;
import controller.UserController;
import model.Image;
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
	
	@Inject 
	private ImageController imgCntl;
	
	private User user=new User();
	private Part file;
	private Image image;

	public String checkLogin() {
		try {
			this.user = usrCont.login(user.getUserName(), user.getPassword());
		}catch(Exception e){
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
				if(this.file != null && this.file.getSize() > 0 && this.file.getContentType().startsWith("image")){
					this.image = imgCntl.upload(this.file);
				}
				user.setAvatar(image);
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
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
}
