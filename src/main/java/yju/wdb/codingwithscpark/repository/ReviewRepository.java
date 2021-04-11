package yju.wdb.codingwithscpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yju.wdb.codingwithscpark.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
