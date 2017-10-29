package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.Post;


@Named("homeMb")
@SessionScoped
public class Home implements Serializable{
	private static final long serialVersionUID = 891515424619865689L;
	@Inject
	private PostController postCont;
	private String post;
	private String usr;
	
	public PostController getPostCont() {
		return postCont;
	}
	public void setPostCont(PostController postCont) {
		this.postCont = postCont;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public void send(int uid) {
		Date d=new Date();
		Post p=new Post(uid,d,post);
		postCont.create(p);
	}
	public List<Post> getPosts(int uid){
		return postCont.getUserPosts(uid);
	}

}
