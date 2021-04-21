package yju.wdb.codingwithscpark.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import yju.wdb.codingwithscpark.entity.*;

public interface MovieRepository extends JpaRepository<Movie, Long> {
//	@Query("select m, avg(coalesce(r.grade, 0)), count(distinct r) " + 
//				"from Movie m left outer join Review r on r.movie = m group by m")
//	@Query("select m, max(mi), avg(coalesce(r.grade, 0)), count(distinct r) " + 
//			"from Movie m left outer join MovieImage mi on mi.movie =  m " + 
//			"left outer join Review r on r.movie = m group by m")              // <- N+1 problem. why?
	@Query("select m, max(mi.inum), avg(coalesce(r.grade, 0)), count(distinct r) " + 
			"from Movie m left outer join MovieImage mi on mi.movie =  m " + 
			"left outer join Review r on r.movie = m group by m")
	Page<Object[]> getListPage(Pageable pageable);
}