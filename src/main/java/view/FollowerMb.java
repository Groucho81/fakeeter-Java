package view;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import auth.AuthMb;
import controller.FollowerController;
import controller.PostController;
import model.Post;
import model.User;

@Named
@Stateless
public class FollowerMb {
	@Inject
	private AuthMb authMb;
	@Inject 
	private FollowerController followerCont;
	
	@Inject 
	private PostController postCont;
	
	public String canFollow(User userToFollow) {
		
		if (followerCont.canFollow(authMb.getUser(), userToFollow)) {
			return "";
		}else {
			return "disabled";
		}
	}
	public void follow (User userToFollow) {
		followerCont.follow(authMb.getUser(), userToFollow);
		System.out.println("##### FollowerMb->follow->userToFollow:"+userToFollow.getUserName());
		System.out.println("##### FollowerMb->follow->usuario logueado:"+authMb.getUser().getUserName());
	}
	
	public List<Post> getPosts() {
		return postCont.getFolowPosts(authMb.getUser());
	}

}
