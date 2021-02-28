package yju.wdb.codingwithscpark.dto;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResultDTO <DTO, Entity>{
	
	private List<DTO> dtoList;
	
	public PageResultDTO(Page<Entity> result, Function<Entity, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
	}
	
	public List<DTO> getDtoList() {
		return dtoList;
	}
	 
}


