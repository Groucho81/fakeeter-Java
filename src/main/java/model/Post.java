package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
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
	public void setPost(String post) {
		this.post=post;
	}
	public String getUserName() {
		return user.getUserName();
	}
	@Override
	public String toString() {
		String s="id:"+id+" usr:"+user.getId()+" date:"+date+" post:"+post;
		return s;
	}
	
}
