package view;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import auth.AuthMb;
import controller.CommentController;
import model.Comment;
import model.Post;
import model.User;

@Named
@Stateless
public class CommentMb {

	@Inject
	private CommentController commentCntrl;
	
	@Inject
	private AuthMb authMb;
	
	@NotNull
	@Size(min=2,max=255)
	private String comment;
	
	public void create(Post post){
		try {
			User user = authMb.getUser();
			commentCntrl.create(user, post, this.comment);
			comment="";
		}catch(Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar el comentario", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public List<Comment> listByPost(Post post){
		return commentCntrl.byPost(post);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
