package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class M_Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;
	
	private String email;
	private String pw;
	private String nickname;
	
	public M_Member() {
		super();
	}
	
	public M_Member(Long mid) {
		this.mid = mid;
	}
	
	public M_Member(String email, String pw, String nickname) {
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
	}
	
	public M_Member(Long mid, String email, String pw, String nickname) {
		super();
		this.mid = mid;
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "M_Member [mid=" + mid + ", email=" + email + ", pw=" + pw + ", nickname=" + nickname + "]";
	}
	
	public Long getMid() {
		return mid;
	}
	public String getEmail() {
		return email;
	}
	public String getPw() {
		return pw;
	}
	public String getNickname() {
		return nickname;
	}
	
}
