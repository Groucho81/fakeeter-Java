package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date date;
	private String post;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	public Post () {
		super();
	}
	
	public Post(User user, Date date, String post) {
		super();
		this.date = date;
		this.post = post;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPost() {
		return post;
	}
	@Override
	public String toString() {
		String s="id:"+id+" usr:"+user.getId()+" date:"+date+" post:"+post;
		return s;
	}
	
}
