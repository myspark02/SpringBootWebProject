package yju.wdb.codingwithscpark.service;

import yju.wdb.codingwithscpark.dto.GuestBookDTO;
import yju.wdb.codingwithscpark.entity.GuestBook;

public interface GuestBookService {
	Long register(GuestBookDTO dto);
	
	default GuestBook convertDTO2Entity(GuestBookDTO dto) {
		GuestBook entity = new GuestBook(dto.getTitle(), dto.getContent(), dto.getWriter());
		return entity;
	}

}

