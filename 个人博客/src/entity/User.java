package entity;

public class User {
	private int id;
	private String name;
	private String tel;
	private String password;
	private String sex;
	
	public User(String name, String tel, String password) {
		this.name=name;
		this.tel=tel;
		this.password=password;
	}
	public User(String name, String tel, String password, String sex) {
		this.name=name;
		this.tel=tel;
		this.password=password;
		this.sex=sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
