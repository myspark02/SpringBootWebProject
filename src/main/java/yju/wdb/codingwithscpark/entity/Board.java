package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String content;
	
	@ManyToOne (fetch = FetchType.LAZY) // Lazy loading
	private Member writer; 
	
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
	
	public Long getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Member getWriter() {
		return writer; 
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + 
				", content=" + content + ", getRegDate()=" + getRegDate()
				+ ", getModDate()=" + getModDate() + "]";
	}
	public Board(String title, String content, Member writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public Board() {
		super();
	}
	
	public Board(Long bno) {
		this.bno = bno;
	}
	
}

