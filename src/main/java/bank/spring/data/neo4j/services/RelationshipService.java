package bank.spring.data.neo4j.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.neo4j.ogm.session.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.spring.data.neo4j.domain.*;
import bank.spring.data.neo4j.repositories.RelationshipRepository;
import bank.spring.data.neo4j.util.ToD3Format;
import bank.spring.data.neo4j.util.UtilService;

@Service
public class RelationshipService {
	@Autowired RelationshipRepository relationshipRepository;
	UtilService us=new UtilService();
	ToD3Format td=new ToD3Format();

	@Autowired
	Session session;

	@Transactional(readOnly=true)//摘要图的显示
	public Map<String,List<Map<String, Object>>> showAll_Rel(int limit) { 
		Collection<Relationship> result=relationshipRepository.showAll_rel1(limit-200);//DB和KZ
		Collection<Relationship> otherresult1=relationshipRepository.showAll_rel2(limit-950);
		Collection<Relationship> otherresult2=relationshipRepository.showAll_rel3(limit-700);//JY
		result.addAll(otherresult1);
		result.addAll(otherresult2);
		return td.toD3FormatByrel(result);
	}
	
	@Transactional(readOnly=true)//1节点担保圈的查询
	public Map<String,List<Map<String, Object>>> findCircle_1DB(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.findCircle_1DB(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)//2节点担保圈的查询
	public Map<String,List<Map<String, Object>>> findCircle_2DB(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.findCircle_2DB(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)//3节点担保圈的查询
	public Map<String,List<Map<String, Object>>> findCircle_3DB(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.findCircle_3DB(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)//4节点担保圈的查询
	public Map<String,List<Map<String, Object>>> findCircle_4DB(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.findCircle_4DB(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)//5节点担保圈的查询
	public Map<String,List<Map<String, Object>>> findCircle_5DB(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.findCircle_5DB(skip,limit);
		return td.toD3FormatByrel(result);
	}
	

	
	@Transactional(readOnly=true)//通过节点Id扩展节点
	public Map<String,List<Map<String, Object>>> extendById(int id,Map<String,List<Map<String, Object>>> old) {
		Collection<Relationship> result=relationshipRepository.extendById(id);
		
		return td.toD3FormatByExtend(result,old,id);
	}
	
	@Transactional(readOnly=true)//通过输入搜索担保圈
	public Map<String, List<Map<String, Object>>> searchDbCircleByInput(int num,int skip,int limit) {
		List<Relationship> result=new ArrayList<Relationship>();
		Map<String,Object> map=new HashMap<>();
		map.put("skip",skip);
		map.put("limit",limit);
		
		
		String cypher=us.createCypherforDBCircle(num);
		
		
		Iterator<Relationship> r=session.query(Relationship.class, cypher,map).iterator();
	
		while(r.hasNext()) {
			Relationship m=r.next();
			result.add(m);
		}
		
		return td.toD3FormatByrel(result);
		
	}
	

	//通过公司名称查询公司节点，并展示相关关联关系
	public Map<String, List<Map<String, Object>>> findByCompanyName(String CompanyName) {
		Collection<Relationship> result=relationshipRepository.findByCompanyName(CompanyName);
		return td.toD3FormatByrel(result);
	}


	//通过公司机构代码查询公司节点，并展示相关关联关系
	public Map<String, List<Map<String, Object>>> findByCompanyJgdm(String CompanyJgdm) {
		Collection<Relationship> result=relationshipRepository.findByCompanyJgdm(CompanyJgdm);
		return td.toD3FormatByrel(result);
	}
	
	
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkyyycbz(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkyyycbz(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkzrfdc(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkzrfdc(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkzrrzdb(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkzrrzdb(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkhlzdbr(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkhlzdbr(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkyjhx(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkyjhx(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkhlzfrdb(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkhlzfrdb(skip,limit);
		return td.toD3FormatByrel(result);
	}
	@Transactional(readOnly=true)
	public Map<String, List<Map<String, Object>>> searchDkffzyg(int skip,int limit) {
		Collection<Relationship> result=relationshipRepository.searchDkffzyg(skip,limit);
		return td.toD3FormatByrel(result);
	}
	public Map<String, List<Map<String, Object>>> searchXfdkzrfdc(int skip, int limit) {
		Collection<Relationship> result=relationshipRepository.searchXfdkzrfdc(skip,limit);
		return td.toD3FormatByrel(result);
	}
	public Map<String, List<Map<String, Object>>> extendByInput(int id,int n,Map<String, List<Map<String, Object>>> old, String minJYJE, String maxJYJE, String minJYRQ,String maxJYRQ) {
		List<Relationship> result=new ArrayList<Relationship>();
		Map<String,Object> map=new HashMap<>();
		map.put("skip",0);
		map.put("limit",0);
		
		
		String cypher=us.createCypherforJYChain(id, n, minJYJE, maxJYJE, minJYRQ, maxJYRQ);
		
		
		Iterator<Relationship> r=session.query(Relationship.class, cypher,map).iterator();
	
		while(r.hasNext()) {
			Relationship m=r.next();
			result.add(m);
		}
		System.out.println(result);
		return td.toD3FormatByExtendInput(result, old, id);
	}
	
	
	

}
