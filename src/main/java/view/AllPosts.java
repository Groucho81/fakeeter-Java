package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import controller.PostController;
import controller.UserController;
import model.Post;
import model.User;


@Named("allPostsMb")

public class AllPosts {
	
	
	@Inject
	private PostController postCont;
	@Inject
	private UserController usrCont;

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
