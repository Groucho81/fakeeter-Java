package model;

import java.util.Date;

public class Post {
	private long id;
	private User usr;
	private Date date;
	private String post;
	
	public Post(long id, User usr, Date date,String post) {
		super();
		this.id = id;
		this.usr = usr;
		this.date = date;
		this.post = post;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
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
		String s="id:"+id+" usr:"+usr+" date:"+date+" post:"+post;
		return s;
	}
	
}
