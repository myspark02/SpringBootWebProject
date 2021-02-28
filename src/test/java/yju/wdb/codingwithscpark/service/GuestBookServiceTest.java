package yju.wdb.codingwithscpark.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.entity.GuestBook;

@SpringBootTest
public class GuestBookServiceTest {
	
	@Autowired
	private GuestBookService service;
	
	//@Test
	public void testRegister() {
		GuestBookDTO guestbookDTO = new GuestBookDTO("Sample Title...!", "Sample Content...!", "user1");
		
		System.out.println(service.register(guestbookDTO));
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO(1, 10);
		PageResultDTO<GuestBookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println("PREV:" + resultDTO.isPrev());
		System.out.println("PREV:" + resultDTO.isNext());
		System.out.println("TOTAL:" + resultDTO.getTotalPage());
		
		System.out.println("-----------------------------------");
		for (GuestBookDTO guestbookDTO : resultDTO.getDtoList()) {
			System.out.println(guestbookDTO);
		}
		
		System.out.println("===================================");
		resultDTO.getPageList().forEach(i -> System.out.println(i));
	}

}
