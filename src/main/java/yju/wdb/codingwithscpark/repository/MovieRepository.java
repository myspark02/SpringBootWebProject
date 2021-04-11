package yju.wdb.codingwithscpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yju.wdb.codingwithscpark.entity.*;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
