package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class Reply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String text;
	private String replyer;
	
	@ManyToOne
	private Board board; 
	
	public Long getRno() {
		return rno;
	}
	public String getText() {
		return text;
	}
	public String getReplyer() {
		return replyer;
	}
	public Board getBoard() {
		return board;
	}
	public Reply(String text, Board board, String replyer) {
		this.text = text;
		this.board = board;
		this.replyer = replyer;
	}
	public Reply() {
		super();
	}
	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", text=" + text + ", replyer=" + 
					replyer + ", getRegDate()=" + getRegDate()
				+ ", getModDate()=" + getModDate() + "]";
	}
	
}
