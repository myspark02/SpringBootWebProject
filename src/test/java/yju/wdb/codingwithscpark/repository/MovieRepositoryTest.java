package yju.wdb.codingwithscpark.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import yju.wdb.codingwithscpark.entity.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.*;

@SpringBootTest
public class MovieRepositoryTest {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository imageRepository;
	
	@Test
	public void testGetMovieWithAll() {
		List<Object[]> result = movieRepository.getMovieWithAll(94L);
		
		System.out.println(result);
		
		for (Object[] arr : result) 
			System.out.println(Arrays.toString(arr));
	}
	
//	@Test
	public void testListPage() {
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
		Page<Object[]> result = movieRepository.getListPage(pageRequest);
		
		for (Object[] objects : result.getContent()) {
			System.out.println(Arrays.toString(objects));
		}
		
	}
	
//	@Commit
//	@Transactional
//	@Test
	public void insertMovies() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Movie movie = new Movie("Movie..." + i);
			
			System.out.println("-------------------------------------");
			movieRepository.save(movie);
			
			int count = (int)(Math.random() * 5) + 1; 
			
			for (int j = 0; j < count; j++) {
				MovieImage movieImage = new MovieImage(UUID.randomUUID().toString(), "test"+j+".jpg", movie);
				imageRepository.save(movieImage);
			}
			System.out.println("========================================");
		});
	}

}
