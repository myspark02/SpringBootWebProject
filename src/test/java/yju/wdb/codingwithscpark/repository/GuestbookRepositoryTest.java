package yju.wdb.codingwithscpark.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import yju.wdb.codingwithscpark.entity.GuestBook;
import yju.wdb.codingwithscpark.entity.QGuestBook;

@SpringBootTest
public class GuestbookRepositoryTest {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
//	@Test
	public void insertDummies() {
		
		IntStream.rangeClosed(1, 300).forEach(i -> {
			GuestBook guestbook = new GuestBook("Title..." + i, "Content..." + i, "user" + (i%10));
			GuestBook insertedGuestBook = guestbookRepository.save(guestbook);
			System.out.println(insertedGuestBook);
		});
	}
	
	//@Test
	public void updateTest() {
		Optional<GuestBook> result = guestbookRepository.findById(300L);
		
		if(result.isPresent()) {
			GuestBook guestbook = result.get();
			guestbook.changeTitle("Changed Title...");
			guestbook.changeContent("Changed Content....");
			
			guestbookRepository.save(guestbook);
		}
	}
	
	//@Test
	public void testQuery() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		QGuestBook qGuestBook = QGuestBook.guestBook; //1
		String keyword = "1";
		
		BooleanBuilder builder = new BooleanBuilder(); //2
		
		BooleanExpression expression = qGuestBook.title.contains(keyword); //3
		
		builder.and(expression); //4
		
		Page<GuestBook> result = guestbookRepository.findAll(builder, pageable); // 5
		
		result.stream().forEach(guestBook -> {
			System.out.println(guestBook);
		});
		
	}

	//@Test
	public void testQuery2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		QGuestBook qGuestBook = QGuestBook.guestBook;
		
		String keyword = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		BooleanExpression exTitle =qGuestBook.title.contains(keyword);
		
		BooleanExpression exContent = qGuestBook.content.contains(keyword);
		
		BooleanExpression exAll = exTitle.or(exContent); // 1------
		
		builder.and(exAll); // 2------
		
		builder.and(qGuestBook.gno.gt(0L)); // 3---------
		
		Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);
		
		result.stream().forEach(guestbook -> {
			System.out.println(guestbook);
		});
		
	}
}



