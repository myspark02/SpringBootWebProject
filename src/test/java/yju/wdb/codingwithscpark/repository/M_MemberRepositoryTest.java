package yju.wdb.codingwithscpark.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yju.wdb.codingwithscpark.entity.*;

import java.util.stream.IntStream;

@SpringBootTest
public class M_MemberRepositoryTest {
	@Autowired
	private M_MemberRepository memberRepository;
	
	@Test
	public void insertMembers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			M_Member member = new M_Member("r"+i+"@yju.ac.kr", "1111", "reviewer"+i);
			memberRepository.save(member);
		});
	}
}
