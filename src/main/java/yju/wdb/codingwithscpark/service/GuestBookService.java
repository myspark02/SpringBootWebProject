package yju.wdb.codingwithscpark.service;

import yju.wdb.codingwithscpark.dto.GuestBookDTO;
import yju.wdb.codingwithscpark.dto.PageRequestDTO;
import yju.wdb.codingwithscpark.dto.PageResultDTO;
import yju.wdb.codingwithscpark.entity.GuestBook;

public interface GuestBookService {
	Long register(GuestBookDTO dto);
	
	default GuestBook convertDTO2Entity(GuestBookDTO dto) {
		GuestBook entity = new GuestBook(dto.getTitle(), dto.getContent(), dto.getWriter());
		return entity;
	}
	
	default GuestBookDTO convertEntity2DTO(GuestBook entity) {
		GuestBookDTO dto = new GuestBookDTO(entity.getGno(), entity.getTitle(), 
				entity.getContent(), entity.getWriter(), entity.getRegDate(), entity.getModDate());
		return dto;
	}
	
	public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);
	
	GuestBookDTO read(Long gno);
}

