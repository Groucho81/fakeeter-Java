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
	
	@Inject UserController userCont;
	
	@Inject
	private AuthMb authMb;
	private String post;
	private Part file;
	private Image image;
	private User user;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
	public void send() {
		try{
			if(this.file != null && this.file.getSize() > 0 && this.file.getContentType().startsWith("image")){
				this.image = imgCntl.upload(this.file);
			}
			if (this.post==null && this.image==null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Texto e imagen vacios. Nada que publicar", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else {
				Date date=new Date();
				Post p=new Post(authMb.getUser(),date,post,image);
				postCont.create(p);
			}
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void changeAvatar() {
		try{
			if(file != null && file.getSize() > 0 && file.getContentType().startsWith("image")){
				image = imgCntl.upload(file);
			}
			user=authMb.getUser();
			user.setAvatar(image);
			userCont.update(user);		
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	public List<Post> getPosts(){
		return postCont.getUserPosts(authMb.getUser());
	}

}