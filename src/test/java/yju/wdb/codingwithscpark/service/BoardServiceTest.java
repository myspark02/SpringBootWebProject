package yju.wdb.codingwithscpark.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.dto.*;

@SpringBootTest
public class BoardServiceTest {
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testModify() {
		BoardDTO boardDTO = new BoardDTO(2L, "제목 변경합니다", "내용 변경합니다");
		boardService.modify(boardDTO);
	}
	
//	@Test
	public void testREmove() {
		Long bno = 16L;
		boardService.removeWithReplies(bno);
	}
	
	
//	@Test
	public void testGet() {
		Long bno = 100L;
		BoardDTO boardDTO = boardService.get(bno);
		System.out.println(boardDTO);
	}
	
//	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
		
		for (BoardDTO boardDTO : result.getDtoList()) System.out.println(boardDTO);
	}
	
//	@Test
	public void testRegister() {
		BoardDTO dto = new BoardDTO("Test.", "Test...", "user55@yju.ac.kr");
		Long bno = boardService.register(dto);
		System.out.println("Registered ...: " + bno);
	}
}
