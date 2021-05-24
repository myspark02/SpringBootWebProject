package yju.wdb.codingwithscpark.service;

import java.util.*;
import java.util.stream.Collectors;

import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.entity.*;

public interface MovieService {
	Long register(MovieDTO movieDTO);
	
	default Map<String, Object> convertDto2Entity(MovieDTO movieDTO) {
		Map<String, Object> entityMap = new HashMap<>();
		
		Movie movie = new Movie(movieDTO.getMno(), movieDTO.getTitle());
		
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
		
		// MovieImageDTO 처리
		if(imageDTOList != null && imageDTOList.size() > 0) {
			List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {
				MovieImage movieImage = new MovieImage(movieImageDTO.getPath(), 
												movieImageDTO.getImageName(), movieImageDTO.getUuid(), movie);
				return movieImage;
				
			}).collect(Collectors.toList());
			
			entityMap.put("imgList", movieImageList);
		}
		return entityMap;
		
	}
}
