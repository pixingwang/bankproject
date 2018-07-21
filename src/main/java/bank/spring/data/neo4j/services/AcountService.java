package bank.spring.data.neo4j.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import bank.spring.data.neo4j.domain.Acount;

import bank.spring.data.neo4j.repositories.AcountRepository;
import bank.spring.data.neo4j.util.UtilService;


@Service
public class AcountService {
	@Autowired AcountRepository  acountRepository;
	
private Map<String, List<Map<String, Object>>> toD3FormatByrel(Collection<Acount> Node) {
		
		UtilService us=new UtilService();
		
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();	
		
		Iterator<Acount> result = Node.iterator();
		
		while (result.hasNext()) {
			
			Acount acount=result.next();
			nodes.add(us.map(acount));
			
			
		}	
			
			return us.map("nodes", nodes, "links", rels);
			
	}



	public Map<String, List<Map<String, Object>>> findByCountName(String CountName) {
		Collection<Acount> result=acountRepository.findByCountName(CountName);
		return toD3FormatByrel(result);
	}

	public Map<String, List<Map<String, Object>>> findByCountNumber(String creditcode) {
		Collection<Acount> result=acountRepository.findByCreditcode(creditcode);
		return toD3FormatByrel(result);
	}


	

}
