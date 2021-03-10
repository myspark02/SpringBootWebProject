package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity {
	@Id
	@Column(length = 32)
	private String email;
	private String password;
	private String name;
	
	
	public Member() {
		super();
	}
	public Member(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Member [email=" + email + ", password=" + password + 
				", name=" + name + ", getRegDate()=" + getRegDate()
				+ ", getModDate()=" + getModDate() + "]";
	}
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
}
