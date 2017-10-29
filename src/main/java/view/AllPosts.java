package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.Post;


@Named("allPostsMb")
@SessionScoped
public class AllPosts implements Serializable{
	private static final long serialVersionUID = 991515424619865689L;
	@Inject
	private PostController postCont;

	private String usr;
	private Date date;
	private String post;
	private List<Post> allThePosts;

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
