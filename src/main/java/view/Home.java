package view;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.Post;
import model.User;


@Named("homeMb")
public class Home{
	@Inject
	private PostController postCont;
	
	@Inject
	private LogIn login;
	
	private String post;
	
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public void send() {
		if (post.equals("")) {
			System.out.println("Mensaje vacío, nada que hacer");
		}else {
			Date date=new Date();
			User user = login.getUser();
			Post p=new Post(user,date,post);
			postCont.create(p);
		}	
	}
	public List<Post> getPosts(){
		return postCont.getUserPosts(login.getUser());
	}

}