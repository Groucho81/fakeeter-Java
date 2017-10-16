package model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
@Singleton
public class DataBase {
	private List<User> usersList = new ArrayList<>();
	private List<Post> postsList = new ArrayList<>();
	
	public DataBase() {
		long uid=nextUserId();
		usersList.add(new User(uid,"pepe","pepe","pepe@pepe.com"));
		long pid=nextPostId();
		User u=usersList.get(0);
		Date date = new Date();
		postsList.add(new Post(pid,u,date,"Hola Mundo"));
		System.out.println("###### ACA ESTOY: "+postsList.get(0).toString());
		date = new Date();
		pid=nextPostId();
		postsList.add(new Post(pid,u,date,"Otro post relevante"));
		System.out.println("###### ACA ESTOY: "+postsList.get(1).toString());
	}
	
	public List<User> getUsersList() {
		return usersList;
	}
	
	public List<Post> getPostsList() {
		return postsList;
	}
	public int nextUserId(){
		return usersList.size();
	}
	public void addUsr(User u) {
		usersList.add(u);
	}
	public int nextPostId(){
		return postsList.size();
	}
	public void addPost(Post p) {
		postsList.add(p);
	}
}
