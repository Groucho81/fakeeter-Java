package controller;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import model.Post;
import model.User;

@Stateless
public class PostController {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(Post post){
		entityManager.persist(post);
	}

	public List<Post> getAll(){
		String hql = "Select p from Post p";
		TypedQuery<Post> q = entityManager.createQuery(hql,Post.class);
        return q.getResultList();
	}
	public List<Post> getUserPosts(int id){
		String hql = "Select p from Post p where usrId = :usrId";
		TypedQuery<Post> q = entityManager.createQuery(hql,Post.class);
		q.setParameter("usrId", id);
        return q.getResultList();
	}

}
