package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follower {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User userFollowed;

	public Follower(User user, User userFollowed) {
		super();
		this.user = user;
		this.userFollowed = userFollowed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserFollowed() {
		return userFollowed;
	}

	public void setFollowingUser(User userFollowed) {
		this.userFollowed = userFollowed;
	}
	
	
}
