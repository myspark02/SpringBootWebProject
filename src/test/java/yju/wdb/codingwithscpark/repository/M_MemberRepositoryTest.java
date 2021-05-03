package yju.wdb.codingwithscpark.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import yju.wdb.codingwithscpark.entity.*;

import java.util.stream.IntStream;

@SpringBootTest
public class M_MemberRepositoryTest {
	@Autowired
	private M_MemberRepository memberRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Commit
	@Transactional
	@Test
	public void testDeleteMember() {
		Long mid = 2L; // Member의 mid
		M_Member member = new M_Member(mid);
		
		// 기존 
//		memberRepository.deleteById(mid);
//		reviewRepository.deleteByMember(member);
		
		// 순서 주의 
		reviewRepository.deleteByMember(member);
		memberRepository.deleteById(mid);
	}
	
//	@Test
	public void insertMembers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			M_Member member = new M_Member("r"+i+"@yju.ac.kr", "1111", "reviewer"+i);
			memberRepository.save(member);
		});
	}
}
