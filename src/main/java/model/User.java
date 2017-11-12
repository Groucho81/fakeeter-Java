package model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull(message = "El campo nombre no puede estar vacio") 
	@Size(min = 4, max = 12, message = "Tu nombre debe tener un minimo de 4 caracteres y un maximo de 12")
	private String userName;
	
	@NotNull(message = "El campo nombre no puede estar vacio")
	@Size(min = 4, max = 15, message = "Tu password debe tener un minimo de 8 caracteres y un maximo de 15")
	private String password;
	
	@NotNull(message = "El campo email no puede estar vacio")
	@Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$", message="Campo de email no valido")
	private String email;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Post> posts;
	
	public User () {
		super();
	}

	public User(String userName, String password,String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
