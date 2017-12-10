package auth;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.User;

@Named
@SessionScoped
public class AuthMb implements Serializable{

	private static final long serialVersionUID = -4803205115493366016L;
	
	private User user;

	public boolean isLogged() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String logout() {
		user = new User();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/index?faces-redirect=true";
	}
	
}
