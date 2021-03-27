package yju.wdb.codingwithscpark.repository;

import java.util.*;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import yju.wdb.codingwithscpark.entity.*;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void testSearchPage() {
		Pageable pageable = PageRequest.of(0,  10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.searchPage("t",  "1",  pageable);
	}
	
//	@Test
	public void testSearch1() {
		boardRepository.search1();
	}
	
//	@Test
	public void testRead3() {	
		Object result = boardRepository.getBoardByBno(100L);
		Object[] arr = (Object[])result;
		System.out.println(Arrays.toString(arr));
	}	
	
//	@Test
	public void testWithReplyCount() {
		/*
		 * org.springframework.data.domain.Pageable;
		 * org.springframework.data.domain.Sort;
		 * org.springframework.data.domain.Page
		 */
		Pageable pageable = PageRequest.of(0,  10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		result.get().forEach( row -> {
			Object[] arr = (row);
			System.out.println(Arrays.toString(arr));
		});
	}
	
//	@Test
	public void testGetBoardWithReply() {
		List<Object[]> result = boardRepository.getBoardWithReply(100L);
		for (Object[] arr : result)
			System.out.println(Arrays.toString(arr));
	}
	
//	@Test
	public void testReadWithWriter() {
		Object result = boardRepository.getBoardWithWriter(100L);
		Object[] arr = (Object[])result;
		System.out.println("------------------------------------");
		System.out.println(Arrays.toString(arr));
	}
	
	@Transactional
//	@Test
	public void testRead1() {
		// java.util.Optional
		// 100 must exist in board table as a bno column of a record
		Optional<Board> result = boardRepository.findById(100L); 
		
		Board board = result.get();
		
		System.out.println(board);
		System.out.println("---------------------------------------");
		System.out.println(board.getWriter());
	}
	
//	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1,  100).forEach(i -> {
			Member member = new Member("user"+i+"@yju.ac.kr", "", "");
			Board board = new Board("Tittle..."+i, "Content......." + i, member);
			boardRepository.save(board);
		});
	}
}
