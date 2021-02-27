package yju.wdb.codingwithscpark.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor  
@NoArgsConstructor 
@ToString 

// Lombok 작동 안하는 듯.

public class GuestBook extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 1500, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	
	public GuestBook(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public GuestBook() {
		
	}
	
	public Long getGno() {
		return gno;
	}
	
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "gno:" + gno + ", title:"+title+", content:"+ content + ", writer:" + writer;
	}
}

