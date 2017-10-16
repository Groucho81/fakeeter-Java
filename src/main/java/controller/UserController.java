package controller;


import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import model.DataBase;
import model.User;

@Stateful
public class UserController {
	@Inject
	DataBase db;
	public User verify(String username,String password){
		//Si existe un usr con ese password devuelve el usr, sino devuelve null.
		User usr=null;
		for (User u : db.getUsersList()) {
			if (u.getUserName().equals(username) && u.getPassword().equals(password)){
				usr=u;
			}
		}
		return usr;
	}
	public User verifyNewUser(String username,String email){
		//Devuelve null si no encontró ningún usuario con el mismo usr o email,
		// indicando que son datos válidos para generarlo. Sino devuelve el usr.
		User usr=null;
		for (User u : db.getUsersList()) {
			if (u.getUserName().equals(username) || u.getEmail().equals(email)){
				usr=u;
			}
		}
		return usr;
	}
	public void createUser(String username, String password, String email){
		long uid=db.nextUserId();
		User usr = new User(uid,username,password,email);
		db.addUsr(usr);
	}
	
}
