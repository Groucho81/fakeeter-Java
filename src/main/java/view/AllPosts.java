package view;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


import controller.PostController;
import model.Post;



@Named("allPostsMb")

public class AllPosts {
	
	
	@Inject
	private PostController postCont;

	private String usr;
	private Date date;
	private String post;


	public String getUsr() {
		return usr;
	}

	public Date getDate() {
		return date;
	}

	public String getPost() {
		return post;
	}
	
	public List<Post> getPosts() {
		return postCont.getAll();
	}
			
}
