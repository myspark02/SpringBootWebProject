package yju.wdb.codingwithscpark.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yju.wdb.codingwithscpark.entity.Board;
import yju.wdb.codingwithscpark.repository.search.SearchBoardRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
	@Query("select b, w, count(r)" + 
			" from Board b left join b.writer w"  +   // 공백 주의
			" left outer join Reply r on r.board = b" +
			" where b.bno = :bno"
			)
	Object getBoardByBno(@Param("bno") Long bno);  // 상세내용 조회용, 댓글 목록은 Ajax로 처리.  
	
	@Query(value="select b, w, count(r)" +
				" from Board b "  +    // 공백 주의
				" left join b.writer w " +
				" left join Reply r on r.board = b " +
				" group by b", 
			countQuery = "select count(b) from Board b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
		
		
	@Query("select b, r from Board b left join Reply r on r.board = b where b.bno = :bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
	
	// @Param : Annotation to bind method parameters to a query via a named parameter.
	@Query("select b, w from Board b left join b.writer w where b.bno = :bno")
	Object getBoardWithWriter(@Param("bno") Long bno); 

}
