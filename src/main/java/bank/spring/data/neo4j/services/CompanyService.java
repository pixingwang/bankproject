package bank.spring.data.neo4j.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import bank.spring.data.neo4j.domain.Company;

import bank.spring.data.neo4j.repositories.CompanyRepository;
import bank.spring.data.neo4j.util.UtilService;


@Service
public class CompanyService {
	@Autowired CompanyRepository  companyRepository;
	
private Map<String, List<Map<String, Object>>> toD3FormatByrel(Collection<Company> Node) {
		
		UtilService us=new UtilService();
		
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();	
		
		Iterator<Company> result = Node.iterator();
		
		while (result.hasNext()) {
			
			Company Company=result.next();
			nodes.add(us.map(Company));
			
			
		}	
		return us.map("nodes", nodes, "links", rels);
			
	}



	public Map<String, List<Map<String, Object>>> findByCompanyName(String CompanyName) {
		Collection<Company> result=companyRepository.findByCompanyName(CompanyName);
		return toD3FormatByrel(result);
	}

	public Map<String, List<Map<String, Object>>> findByCompanyJGDM(String creditcode) {
		Collection<Company> result=companyRepository.findByCompanyCreditcode(creditcode);
		return toD3FormatByrel(result);
	}


	

}
