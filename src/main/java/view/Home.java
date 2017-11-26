package view;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

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
	private LogIn login;
	private String post;
	private Part file;
	private Image image;
	private User user;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
		System.out.println("Home.setpost: "+this.post.toString());
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
		System.out.println("Home.setFile: "+this.file.toString());
	}
	
	public void send() {
		try{
			if(this.file != null && this.file.getSize() > 0 && this.file.getContentType().startsWith("image")){
				this.image = imgCntl.upload(this.file);
				System.out.println("file :"+image.getPath());
			}
			if (this.post==null && this.image==null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Texto e imagen vacios. Nada que publicar", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else {
				Date date=new Date();
				Post p=new Post(login.getUser(),date,post,image);
				postCont.create(p);
			}
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void changeAvatar() {
		//System.out.println("Avatar->file:"+file.toString()+" post:"+post.toString());
		try{
			if(file != null && file.getSize() > 0 && file.getContentType().startsWith("image")){
				image = imgCntl.upload(file);
				System.out.println("file cumple"+image.getPath());
			}
			System.out.println("Home.changeAvatar: USer->"+login.getUser().getUserName());
			user=login.getUser();
			user.setAvatar(image);
			userCont.update(user);		
			System.out.println("Home.changeAvatar: Avatar->"+login.getUser().getAvatar().getPath());
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	public List<Post> getPosts(){
		return postCont.getUserPosts(login.getUser());
	}

}