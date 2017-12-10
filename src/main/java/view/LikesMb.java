package view;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import auth.AuthMb;
import controller.LikeController;
import model.Post;

@Named
@Stateless
public class LikesMb {
	
	@Inject
	private AuthMb authMb;
	
	@Inject 
	private LikeController likeCont;
	
	public String isLikable(Post post) {
		
		if (likeCont.alreadyLiked(authMb.getUser(), post)) {
			return "disabled";
		}else {
			return "";
		}
	}
	public void like (Post post) {
		likeCont.likeIt(authMb.getUser(), post);
	}
	
	public int likeCount (Post post) {
		return likeCont.likeCount(post);
	}
}
