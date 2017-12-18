package view;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import auth.AuthMb;
import controller.ImageController;
import controller.PostController;
import controller.UserController;
import model.Image;
import model.Post;
import model.User;


@Named("homeMb")
@Stateless
public class Home{
	@Inject
	private PostController postCont;
	
	@Inject 
	private ImageController imgCntl;
	
	@Inject 
	private UserController userCont;
	
	@Inject
	private AuthMb authMb;
	private String post;
	private Part file=null;
	private Image image;
	private String password=null;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
	public void send() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
		try{
			if(this.file != null && this.file.getSize() > 0 && this.file.getContentType().startsWith("image")){
				this.image = imgCntl.upload(this.file);
			}
			if (this.post.length()==0 && this.image==null) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Texto e imagen vacios. Nada que publicar", null);
				throw new Exception("Imagen invalida");
			}else {
				Date date=new Date();
				Post p=new Post(authMb.getUser(),date,post,image);
				postCont.create(p);
				this.image = null;
				this.post = "";
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			}
		} catch (Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void changeProfile() {
		
		if (this.password.length()==0 && file == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"password e imagen vacios. Nada que modificar", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else {
			User loggedUser=authMb.getUser();
			try{
				if(file != null && file.getSize() > 0 && file.getContentType().startsWith("image")){
					image = imgCntl.upload(file);
					loggedUser.setAvatar(image);
				}
			} catch (Exception e){
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error con el archivo seleccionado", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			try {
				if (password != null && password.length()>0) {
					loggedUser.setPassword(password);			
				}			
			} catch (Exception e){
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error con la password", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			userCont.update(loggedUser);
			file=null;
			image=null;
		}
		
	}
	public List<Post> getPosts(){
		return postCont.getUserPosts(authMb.getUser());
	}
	
}