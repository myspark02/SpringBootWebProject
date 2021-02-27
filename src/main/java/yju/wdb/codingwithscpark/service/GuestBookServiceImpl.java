package yju.wdb.codingwithscpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.apache.logging.log4j.*;

import lombok.extern.log4j.Log4j2;
import yju.wdb.codingwithscpark.dto.GuestBookDTO;
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

}

