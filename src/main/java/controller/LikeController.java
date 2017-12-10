package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.UserLike;
import model.Post;
import model.User;

@Stateless
public class LikeController {
	@PersistenceContext
	private EntityManager entityManager;
	
	private UserLike like;
	
	public void LikeIt (User user, Post post) {
		like= new UserLike(user,post);
		entityManager.persist(like);
	}
	public int LikeCount (Post post) {
		String hql = "select l from UserLike l where l.post.id = :postId";
		TypedQuery<UserLike> q = entityManager.createQuery(hql,UserLike.class);
		q.setParameter("postId", post.getId());
		return q.getResultList().size();
	}
	public boolean alreadyLiked (User user, Post post) {
		String hql = "select l from UserLike l where l.post.id = :postId and l.user.id = :userId";
		TypedQuery<UserLike> q = entityManager.createQuery(hql,UserLike.class);
		q.setParameter("postId", post.getId());
		q.setParameter("userId", user.getId());
		if (q.getResultList().size()>=1) {
			return true;
		}else {
			return false;
		}
	}
}
