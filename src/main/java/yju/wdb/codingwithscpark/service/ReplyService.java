package yju.wdb.codingwithscpark.service;

import yju.wdb.codingwithscpark.dto.ReplyDTO;
import yju.wdb.codingwithscpark.entity.*;

import java.util.*;

public interface ReplyService {
	Long register(ReplyDTO replyDTO); // 댓글 등록
	List<ReplyDTO> getList(Long bno); // 특정 게시물의 댓글 목록
	void modify(ReplyDTO replyDTO); // 댓글 수정
	void remove(Long rno); // 댓글 삭제
	
	// ReplyDTO 객체를 Reply 객체로 변환. Board 객체의 처리가 수반됨
	default Reply convertDTO2Entity(ReplyDTO replyDTO) {
		Board board = new Board(replyDTO.getBno());
		
		Reply reply = new Reply(replyDTO.getRno(), replyDTO.getText(), replyDTO.getReplyer(), board);
		
		return reply;
	}

	//Reply 객체를 ReplyDTO 객체로 변환, Board 객체가 필요하지 않으므로 게시물 번호만 
	default ReplyDTO convertEntity2DTO(Reply reply) {
		ReplyDTO dto = new ReplyDTO(reply.getRno(), reply.getText(), 
									reply.getReplyer(), reply.getRegDate(), reply.getModDate());
		return dto;
	}
}
