package bank.spring.data.neo4j.repositories;



import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import bank.spring.data.neo4j.domain.Company;



@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
	
	
	@Query("MATCH (n:C) WHERE n.khmc contains {companyName} RETURN n") 
	Collection<Company> findByCompanyName(@Param("companyName") String companyName);
	
	@Query("MATCH (n:C) WHERE n.creditcode contains {companyJGDM} RETURN n") 
	Collection<Company> findByCompanyCreditcode(@Param("companyJGDM") String companyJGDM);
	
	
}
