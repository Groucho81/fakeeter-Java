package controller;

import java.util.List;

import javax.ejb.Stateless;
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
	public List<Post> getUserPosts(User user){
		String hql = "Select p from Post p where p.user.id = :userId";
		TypedQuery<Post> q = entityManager.createQuery(hql,Post.class);
		q.setParameter("userId", user.getId());
        return q.getResultList();
	}

}
