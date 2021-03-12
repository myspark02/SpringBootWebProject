package yju.wdb.codingwithscpark.service;


import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.entity.*;

public interface BoardService {
	
	void modify(BoardDTO boardDTO);
	
	void removeWithReplies(Long bno); 
	
	BoardDTO get(Long bno);
	
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	Long register(BoardDTO dto);
	
	default Board convertDTO2Entity(BoardDTO dto) {
		Member member = new Member(dto.getWriterEmail());
		Board entity = new Board(dto.getTitle(), dto.getContent(), member);
		return entity;
	}
	
	default BoardDTO convertEntity2DTO(Board board, Member member, Long replyCount) {
		BoardDTO dto = new BoardDTO(board.getBno(), board.getTitle(), board.getContent(), 
							member.getEmail(), member.getName(), board.getRegDate(),
							board.getModDate(), replyCount.intValue());
		return dto;
	}
}
