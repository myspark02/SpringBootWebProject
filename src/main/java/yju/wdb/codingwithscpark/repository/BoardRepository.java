package yju.wdb.codingwithscpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yju.wdb.codingwithscpark.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
