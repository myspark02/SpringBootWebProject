package yju.wdb.codingwithscpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yju.wdb.codingwithscpark.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

