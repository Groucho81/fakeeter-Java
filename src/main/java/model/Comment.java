package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@NotNull(message = "El campo usuario no puede estar vacio") 
	@ManyToOne
	private User user;
	
	@NotNull(message = "El campo post no puede estar vacio")
	@ManyToOne
	private Post post;
	
	@NotNull(message = "El campo content no puede estar vacio")
	@Size(min=2,max=255)
	private String content;
	
	@NotNull(message = "El campo date no puede estar vacio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
