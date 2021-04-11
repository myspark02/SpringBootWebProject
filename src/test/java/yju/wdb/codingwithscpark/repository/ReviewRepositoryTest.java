package yju.wdb.codingwithscpark.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yju.wdb.codingwithscpark.entity.*;

import java.util.stream.IntStream;

@SpringBootTest

public class ReviewRepositoryTest {
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void insertMovieReviews() {
		// 200 개의 리뷰를 등록
		IntStream.rangeClosed(1,  200).forEach(i -> {
			// 영화 번호 
			Long mno = (long)(Math.random()*100) + 1;
			Movie movie = new Movie(mno);
			
			// 리뷰어 번호
			Long mid = (long)(Math.random()*100) + 1;
			M_Member member = new M_Member(mid);
			
			Review movieReview = new Review(member, movie, (int)(Math.random()*5)+1, "이 영화에 대한 느낌..."+i);
			reviewRepository.save(movieReview);
		});
	}

}
