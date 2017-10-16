package controller;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import model.DataBase;
import model.Post;
import model.User;

@Stateful
public class PostController {
	@Inject
	DataBase db;
	public void createPost(User user, String message){
		long pid=db.nextPostId();
		Date date = new Date();
		Post post = new Post(pid,user,date,message);
		db.addPost(post);
	}
	public List<Post> getAll(){
		return db.getPostsList();
	}

}
