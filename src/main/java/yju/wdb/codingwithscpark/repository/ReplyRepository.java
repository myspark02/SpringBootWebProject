package yju.wdb.codingwithscpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import yju.wdb.codingwithscpark.entity.Board;
import yju.wdb.codingwithscpark.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	@Modifying
	@Query("delete from Reply r where r.board.bno=:bno")
	void deleteByBno(Long bno);
	
	// 게시글의 댓글 목록 가져오기
	List<Reply> getRepliesByBoardOrderByRno(Board board);
	
}
