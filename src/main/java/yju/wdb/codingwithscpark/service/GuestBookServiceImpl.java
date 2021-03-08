package yju.wdb.codingwithscpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.*;

import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.*;

import lombok.extern.log4j.Log4j2;
import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.entity.GuestBook;
import yju.wdb.codingwithscpark.repository.GuestbookRepository;

@Service
@Log4j2

public class GuestBookServiceImpl implements GuestBookService {
	private static final Logger log = LogManager.getLogger(GuestBookServiceImpl.class);
	
	@Autowired
	private GuestbookRepository repository; // You should declare it as final

	@Override
	public Long register(GuestBookDTO dto) {
		log.info("DTO------------");
		log.info(dto);
		
		GuestBook entity = this.convertDTO2Entity(dto);
		
		log.info(entity);
		
		GuestBook savedEntity = repository.save(entity);
		
	
//		System.out.println(savedEntity);
		return savedEntity.getGno();
	}


	@Override
	public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		Page<GuestBook> result = repository.findAll(pageable);
		
		Function<GuestBook, GuestBookDTO> fn = (entity -> convertEntity2DTO(entity));
		
		return new PageResultDTO<>(result, fn);
	}


	@Override
	public GuestBookDTO read(Long gno) {
		Optional<GuestBook> result = repository.findById(gno);
		return result.isPresent()? convertEntity2DTO(result.get()) : null;
	}
}

