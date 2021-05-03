package yju.wdb.codingwithscpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import yju.wdb.codingwithscpark.entity.M_Member;
import yju.wdb.codingwithscpark.entity.Movie;
import yju.wdb.codingwithscpark.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	void deleteByMember(M_Member member);
	
	@EntityGraph(attributePaths = {"member"}, type=EntityGraph.EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);
}