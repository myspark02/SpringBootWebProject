package yju.wdb.codingwithscpark.entity;
import javax.persistence.*;

@Entity
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewnum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private M_Member member;
	
	private int grade;
	
	private String text;

	@Override
	public String toString() {
		return "Review [reviewnum=" + reviewnum +  ", grade=" + grade + ", text=" + text + "]";
	}

	public Review(Long reviewnum, Movie movie, M_Member member, int grade, String text) {
		super();
		this.reviewnum = reviewnum;
		this.movie = movie;
		this.member = member;
		this.grade = grade;
		this.text = text;
	}
	
	public Review(M_Member member, Movie movie, int grade, String text) {
		
		this.movie = movie;
		this.member = member;
		this.grade = grade;
		this.text = text;
	}

	public Review() {
		super();
	}

	public Long getReviewnum() {
		return reviewnum;
	}

	public Movie getMovie() {
		return movie;
	}

	public M_Member getMember() {
		return member;
	}

	public int getGrade() {
		return grade;
	}

	public String getText() {
		return text;
	}
	
}
