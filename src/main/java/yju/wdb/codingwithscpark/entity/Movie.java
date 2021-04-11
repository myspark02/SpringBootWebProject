package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class Movie extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	
	private String title;
	

	public Movie() {
		super();
	}
	
	public Movie(Long mno) {
		this.mno = mno;
	}
	
	public Movie(String title) {
		this.title = title;
	}

	public Movie(Long mno, String title) {
		super();
		this.mno = mno;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Movie [mno=" + mno + ", title=" + title + ", getMno()=" + getMno() + ", getTitle()=" + getTitle()
				+ ", getRegDate()=" + getRegDate() + ", getModDate()=" + getModDate() + "]";
	}

	public Long getMno() {
		return mno;
	}

	public String getTitle() {
		return title;
	}
	
}
