package model;

public class User {
	private long id;
	private String email;
	private String userName;
	private String password;
	
	public User(long id,String usrName, String pwd,String mail) {
		super();
		this.id = id;
		this.email = mail;
		this.userName = usrName;
		this.password = pwd;
	}
	public long getId() {
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
}
