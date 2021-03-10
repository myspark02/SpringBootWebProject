package yju.wdb.codingwithscpark.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;

import yju.wdb.codingwithscpark.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertMembers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = new Member("user" + i + "@yju.ac.kr", "1111", "USER"+i);
			memberRepository.save(member);
		});
	}
}


