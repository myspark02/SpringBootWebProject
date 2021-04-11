package yju.wdb.codingwithscpark.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.dto.ReplyDTO;

@SpringBootTest
public class ReplyServiceTest {
	
	@Autowired
	private ReplyService service;
	
	@Test
	public void testGetList() {
		Long bno = 100L; // 존재하는 게시글 번호이어야 함
		List<ReplyDTO> replyDTOList = service.getList(bno);
		replyDTOList.forEach(replyDTO->System.out.println(replyDTO));
	}

}
