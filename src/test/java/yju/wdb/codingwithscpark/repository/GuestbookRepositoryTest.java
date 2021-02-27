package yju.wdb.codingwithscpark.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.entity.GuestBook;

@SpringBootTest
public class GuestbookRepositoryTest {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//@Test
	public void insertDummies() {
		
		IntStream.rangeClosed(1, 300).forEach(i -> {
			GuestBook guestbook = new GuestBook("Title..." + i, "Content..." + i, "user" + (i%10));
			GuestBook insertedGuestBook = guestbookRepository.save(guestbook);
			System.out.println(insertedGuestBook);
		});
	}
	
	@Test
	public void updateTest() {
		Optional<GuestBook> result = guestbookRepository.findById(300L);
		
		if(result.isPresent()) {
			GuestBook guestbook = result.get();
			guestbook.changeTitle("Changed Title...");
			guestbook.changeContent("Changed Content....");
			
			guestbookRepository.save(guestbook);
		}
		
	}
}


