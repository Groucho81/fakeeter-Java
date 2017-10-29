package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int usrId;
	private Date date;
	private String post;
	

	
	public Post(int usrId, Date date, String post) {
		super();
		this.usrId = usrId;
		this.date = date;
		this.post = post;
	}
	public int getId() {
		return id;
	}
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int u) {
		this.usrId = u;
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
		String s="id:"+id+" usr:"+usrId+" date:"+date+" post:"+post;
		return s;
	}
	
}
