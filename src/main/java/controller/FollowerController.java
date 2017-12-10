package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Follower;
import model.User;

@Stateless
public class FollowerController {
	@PersistenceContext
	private EntityManager entityManager;
	
	private Follower follower;
	
	public void follow (User user, User userFollowed) {
		follower=new Follower(user,userFollowed);
		entityManager.persist(follower);
	}
	
	public boolean canFollow (User user, User userToFollow) {
		String hql = "select f from Follower f where f.user.id = :userId and f.userFollowed.id = :userToFollowId";
		TypedQuery<Follower> q = entityManager.createQuery(hql,Follower.class);
		q.setParameter("userId", user.getId());
		q.setParameter("userToFollowId", userToFollow.getId());
		if (q.getResultList().size()>=1 || user.getId()==userToFollow.getId()) {
			return false;
		}else {
			return true;
		}
	}
}
