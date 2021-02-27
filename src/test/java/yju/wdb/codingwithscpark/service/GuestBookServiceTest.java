package yju.wdb.codingwithscpark.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.dto.GuestBookDTO;

@SpringBootTest
public class GuestBookServiceTest {
	
	@Autowired
	private GuestBookService service;
	
	@Test
	public void testRegister() {
		GuestBookDTO guestbookDTO = new GuestBookDTO("Sample Title...!", "Sample Content...!", "user1");
		
		System.out.println(service.register(guestbookDTO));
	}

}
