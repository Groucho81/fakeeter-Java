package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.Post;
import model.User;

//@Named("allPostsMb")
@Named
@SessionScoped
public class AllPosts implements Serializable{
	private static final long serialVersionUID = 791515424619865689L;
	@Inject
	private PostController postCont;
	private List<Post> myposts=postCont.getAll();
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

	public List<Post> getAll() {
		System.out.println("###### ACA ESTOY: Vengo a flotar");
		for (Post p : myposts) {
			System.out.println("###### ACA ESTOY: "+p.toString());
		}
        return myposts;
    }
}
