package yju.wdb.codingwithscpark.repository.search;

import org.springframework.data.domain.*;

import yju.wdb.codingwithscpark.entity.Board;

public interface SearchBoardRepository {
	Board search1();
	
	Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
