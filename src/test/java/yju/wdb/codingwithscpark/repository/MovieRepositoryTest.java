package yju.wdb.codingwithscpark.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import yju.wdb.codingwithscpark.entity.*;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTest {
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository imageRepository;
	
	@Commit
	@Transactional
	@Test
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
