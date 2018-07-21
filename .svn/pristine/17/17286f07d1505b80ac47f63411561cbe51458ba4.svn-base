package bank.spring.data.neo4j.repositories;



import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bank.spring.data.neo4j.domain.Acount;



@Repository
public interface AcountRepository extends CrudRepository<Acount, Long>{
	
	/*@Query("MATCH (n:P:N) WHERE n.creditcode=\"420104070951fccf8f0eebfda4d9e1ee38803f\" RETURN n") 
	Collection<Acount> findByNameLike(@Param("name") String name);*/
	
	@Query("MATCH (n:CK) WHERE n.zhmc contains {countName} RETURN n") 
	Collection<Acount> findByCountName(@Param("countName") String countName);
	
	@Query("MATCH (n:CK) WHERE n.ckzh contains {creditcode} RETURN n") 
	Collection<Acount> findByCreditcode(@Param("creditcode") String creditcode);
	
	
}
