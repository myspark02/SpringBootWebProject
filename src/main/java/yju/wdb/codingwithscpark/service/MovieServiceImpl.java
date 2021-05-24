package yju.wdb.codingwithscpark.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yju.wdb.codingwithscpark.dto.MovieDTO;
import yju.wdb.codingwithscpark.entity.*;
import yju.wdb.codingwithscpark.repository.*;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository imageRepository;
	
	@Override
	public Long register(MovieDTO movieDTO) {
		Map<String, Object> entityMap = convertDto2Entity(movieDTO);
		
		Movie movie = (Movie)entityMap.get("movie");
		List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");
		
		movieRepository.save(movie);
		
		movieImageList.forEach(movieImage -> {
			imageRepository.save(movieImage);
		});
		
		return movie.getMno();
	}

}
