package controller;


import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Post;
import model.User;

@Stateless
public class UserController {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(User user){
		entityManager.persist(user);
	}
	public User byId(int id){
        return entityManager.find(User.class, id);
	}
	public User login(String userName, String password) {
		String hql = "select u from User u where u.userName=:userName and u.password=:password";
		TypedQuery<User> q = entityManager.createQuery(hql,User.class);
		q.setParameter("userName",userName);
		q.setParameter("password",password);
		return q.getSingleResult();
	}
	
	public boolean isValidUser(String u,String e){
		String hql = "Select u from User u where u.userName='"+u+"' or u.email='"+e+"'";
		TypedQuery<User> q = entityManager.createQuery(hql,User.class);
		List<User> results = q.getResultList();
		if (results.size()>=1) {
			return false;
		}else {
			return true;
		}
	}
	public void update (User user) {
		entityManager.merge(user);
	}
	public List<User> searchUsers (String search){
		String hql = "Select u from User u where u.userName like %:userName% or u.email like %:email%";
		TypedQuery<User> q = entityManager.createQuery(hql,User.class);
		q.setParameter("userName",search);
		q.setParameter("email",search);
		return q.getResultList();
	}
}
