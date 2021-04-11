package yju.wdb.codingwithscpark.repository;

import java.util.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import yju.wdb.codingwithscpark.entity.*;

@SpringBootTest
public class ReplyRepositoryTest {
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Test
	public void testListByBoard() {
		List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(new Board(52L));
		
		replyList.forEach(reply -> System.out.println(reply));
	}
	
//	@Test
	public void testRead1() {
		// java.util.Optional
		// 100 must exist in board table as a bno column of a record
		Optional<Reply> result = replyRepository.findById(1L); 
		
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println("---------------------------------------");
		System.out.println(reply.getBoard());
	}
	
//	@Test
	public void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			long bno = (long)(Math.random() * 100) + 1;
			
			Board  board = new Board(bno);
			
			Reply reply = new Reply("Reply......."+i, board, "guest");
			
			replyRepository.save(reply);
			
		});
	}
}
