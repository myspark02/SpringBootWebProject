package yju.wdb.codingwithscpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.*;

import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.*;

import lombok.extern.log4j.Log4j2;
import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.entity.GuestBook;
import yju.wdb.codingwithscpark.entity.QGuestBook;
import yju.wdb.codingwithscpark.repository.GuestbookRepository;

@Service
@Log4j2

public class GuestBookServiceImpl implements GuestBookService {
	private static final Logger log = LogManager.getLogger(GuestBookServiceImpl.class);
	
	@Autowired
	private GuestbookRepository repository; // You should declare it as final
	
	// Construct Querydsl query
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		BooleanBuilder booleanBuilder = new BooleanBuilder(); // org.querydsl.core.BooleanBuilder
		
		String type = requestDTO.getType();
		String keyword = requestDTO.getKeyword();
		
		QGuestBook qGuestBook = QGuestBook.guestBook;
		
		BooleanExpression expression = qGuestBook.gno.gt(0L);  // org.querydsl.core.types.dsl.BooleanExpression
		booleanBuilder.and(expression);
		if (type == null || type.trim().length() == 0)  // no search condition is given
			return booleanBuilder;
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		/*
		 * type can be : "t", "c", "w", "tc", "tw", "cw", "tcw", ...
		 */
		if (type.contains("t")) conditionBuilder.or(qGuestBook.title.contains(keyword));
		if (type.contains("c")) conditionBuilder.or(qGuestBook.content.contains(keyword));
		if (type.contains("w")) conditionBuilder.or(qGuestBook.writer.contains(keyword));
		
		booleanBuilder.and(conditionBuilder);
		
		
		return booleanBuilder;
	}
	
	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
	}


	@Override
	public void modify(GuestBookDTO dto) {
		// updatable fields are title and content
		Optional<GuestBook> result = repository.findById(dto.getGno());
		if (result.isPresent()) {
			GuestBook entity = result.get();
			entity.changeTitle(dto.getTitle());
			entity.changeContent(dto.getContent());
			repository.save(entity);
		}
	}

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
		
		BooleanBuilder booleanBuilder = getSearch(requestDTO); // construct Querydsl search condition 
		Page<GuestBook> result = repository.findAll(booleanBuilder, pageable); // use Querydsl
		
		Function<GuestBook, GuestBookDTO> fn = (entity -> convertEntity2DTO(entity));
		
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public GuestBookDTO read(Long gno) {
		Optional<GuestBook> result = repository.findById(gno);
		return result.isPresent()? convertEntity2DTO(result.get()) : null;
	}
}	

