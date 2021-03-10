package yju.wdb.codingwithscpark.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import yju.wdb.codingwithscpark.entity.*;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	@Test
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
