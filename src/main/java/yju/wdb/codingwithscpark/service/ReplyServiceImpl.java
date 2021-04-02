package yju.wdb.codingwithscpark.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yju.wdb.codingwithscpark.dto.ReplyDTO;
import yju.wdb.codingwithscpark.entity.*;
import yju.wdb.codingwithscpark.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyRepository replyRepository;
	

	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = convertDTO2Entity(replyDTO);
		replyRepository.save(reply);
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		List<Reply> result = 
				replyRepository.getRepliesByBoardOrderByRno(new Board(bno));
		
		return result.stream().map(reply -> 
						convertEntity2DTO(reply)).collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		Reply reply = convertDTO2Entity(replyDTO);	
		replyRepository.save(reply);

	}

	@Override
	public void remove(Long rno) {
		replyRepository.deleteById(rno);

	}
}

