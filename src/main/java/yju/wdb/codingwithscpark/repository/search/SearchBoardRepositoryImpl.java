package yju.wdb.codingwithscpark.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import yju.wdb.codingwithscpark.entity.Board;
import yju.wdb.codingwithscpark.entity.*;


public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport 
											implements SearchBoardRepository {
	
	private static final Logger log = LogManager.getLogger(SearchBoardRepository.class);
	
	public SearchBoardRepositoryImpl() {
		super(Board.class);
		
	}
	
	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		log.info("searchPage .............");
		
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		/*
		 * SELECT b, w, count(r) from Board b
		 * LEFT JOIN Member m on b.writer w = m LEFT JOIN Reply r ON r.board = b
		 */
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (type != null) {
			String[] typeArr = type.split("");
			
			for (String t : typeArr) {
				switch(t) {
					case "t":
						booleanBuilder.or(board.title.contains(keyword));
						break;
					case "w":
						booleanBuilder.or(member.email.contains(keyword));
						break;
					case "c":
						booleanBuilder.or(board.content.contains(keyword));
						break;
				}
			}
		}
		tuple.where(booleanBuilder);
		Sort sort = pageable.getSort();  // org.springframework.data.domain.Sort
//		tuple.orderBy(board.bno.desc());
		sort.stream().forEach(order -> {
			Order direction = order.isAscending()? Order.ASC : Order.DESC;
			String prop = order.getProperty();
			
			PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
			tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
		});
		tuple.groupBy(board);
		//page 처리
		tuple.offset(pageable.getOffset());
		tuple.limit(pageable.getPageSize());
		List<Tuple> result = tuple.fetch();
		
		log.info(result);
		
		long count = tuple.fetchCount();
		
		log.info("COUNT:" + count);
		
		return new PageImpl<Object[]>(
					result.stream().map(t -> t.toArray())
								.collect(Collectors.toList()), 
								pageable, count
				);
	} 

	@Override
	public Board search1() {
		log.info("search1 called ...........");
		
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count()).groupBy(board); // Tuple
//		jpqlQuery.select(board).where(board.bno.eq(1L));
		
		log.info("-----------------------------");
		
		log.info(jpqlQuery);
		
		log.info("-----------------------------");
		
		List<Tuple> result = tuple.fetch();		
		log.info(result);
		
		return null;
	}
}
