package yju.wdb.codingwithscpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import yju.wdb.codingwithscpark.entity.GuestBook;

public interface GuestbookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {

}
