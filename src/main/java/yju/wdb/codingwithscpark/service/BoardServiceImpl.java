package yju.wdb.codingwithscpark.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.hql.internal.ast.tree.BinaryLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.*;

import yju.wdb.codingwithscpark.dto.BoardDTO;
import yju.wdb.codingwithscpark.dto.PageRequestDTO;
import yju.wdb.codingwithscpark.dto.PageResultDTO;
import yju.wdb.codingwithscpark.entity.*;
import yju.wdb.codingwithscpark.repository.BoardRepository;
import yju.wdb.codingwithscpark.repository.ReplyRepository;

@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger log = LogManager.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardRepository repository;
	
	@Autowired
	private ReplyRepository replyRepository;

	@Transactional
	@Override
	public void modify(BoardDTO boardDTO) {
		 // JpaRepository method
		Board board = repository.getOne(boardDTO.getBno());
		
		if (board != null) {
			board.changeTitle(boardDTO.getTitle());
			board.changeContent(boardDTO.getContent());
			repository.save(board);
		}
	}

	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		
		replyRepository.deleteByBno(bno);
	
		repository.deleteById(bno);
		
	}

	@Override
	public BoardDTO get(Long bno) {
		Object result = repository.getBoardByBno(bno);
		Object[] arr = (Object[])result;
		return convertEntity2DTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.debug(pageRequestDTO);
		
		Function<Object[], BoardDTO> fn = (en -> convertEntity2DTO((Board)en[0], (Member)en[1], (Long)en[2]));
//		Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		Page<Object[]> result = repository.searchPage(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), 
													pageRequestDTO.getPageable(Sort.by("bno").descending()));	
		
		return new PageResultDTO<>(result, fn);
	}

	
	@Override
	public Long register(BoardDTO dto) {
		log.debug("register : " + dto);
		
		Board board = this.convertDTO2Entity(dto);
		repository.save(board);
		return board.getBno();
	}
}
