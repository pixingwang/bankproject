package bank.spring.data.neo4j.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bank.spring.data.neo4j.domain.*;

@Repository
public interface RelationshipRepository  extends PagingAndSortingRepository<Relationship, Long>{

	@Query("match c1=(n)-[r1]-(p)-[r2]-(q)-[r3]-(k)-[r4]-(s)\r\n" + 
			"			where n<>p and n<>q and n<>k and n<>s \r\n" + 
			"			and p<>q and p<>k and p<>s \r\n" + 
			"			and q<>k and q<>s\r\n" + 
			"			and k<>s\r\n" + 
			"			return c1 limit {limit}")
	
	Collection<Relationship> showAll_rel1(@Param("limit") int limit);
	@Query("match (n)-[r1:JY]-(p)\r\n" + 
			"return n,r1,p limit {limit}")
	Collection<Relationship> showAll_rel2(@Param("limit") int limit);
	@Query("match (n1)-[r1:JY]->(p1)\r\n" + 
			"with n1,r1,p1 limit 50\r\n" + 
			"match (n1)-[r2]-(p2)-[r3]-(p3)\r\n" + 
			"return n1,r2,p2,r3,p3,count(p2) AS D order by D ASC limit {limit}")
	Collection<Relationship> showAll_rel3(@Param("limit") int limit);
	
	
	@Query("match circle=(n:N)-[:DB]->(n) return circle  ")//自担保
	Collection<Relationship> findCircle_1DB(@Param("skip") int skip,@Param("limit") int limit);
	
	@Query("match circle=(n:N)-[:DB]->(m:N)-[:DB]->(q:N) where n=q and n<>m return circle  ")//两个节点间担保303node 652 rel
	Collection<Relationship> findCircle_2DB(@Param("skip") int skip,@Param("limit") int limit);
	
	@Query("MATCH circle=(m:N)-[:DB]->(n:N)-[:DB]->(p:N)-[:DB]->(q:N) WHERE m<>n AND m<>p AND n<>p AND m=q RETURN circle  ")//三节点的担保
	Collection<Relationship> findCircle_3DB(@Param("skip") int skip,@Param("limit") int limit);
	
	@Query("match circle=(n:N)-[:DB]->(m:N)-[:DB]->(p:N)-[:DB]->(q:N)-[:DB]->(s:N) where n=s and n<>m and n<>p and n<>q and m<>p and m<>q and p<>q return circle  ")//4节点间的担保 105node,211rel
	Collection<Relationship> findCircle_4DB(@Param("skip") int skip,@Param("limit") int limit);
	
	@Query("match circle=(n)-[:DB]->(m)-[:DB]->(p)-[:DB]->(q)-[:DB]->(s)-[:DB]->(z)"
	 		+ " where n=z and n<>m and n<>p and n<>q and n<>s "
	 		+ "and m<>p and m<>q and m<>s "
	 		+ "and p<>q and p<>s and q<>s "
	 		+ "return circle  ")//5个节点
	Collection<Relationship> findCircle_5DB(@Param("skip") int skip,@Param("limit") int limit);
	

	
	
	
	@Query("MATCH (n)-[r]-(p) WHERE id(n)={id} RETURN n,r,p")//通过id扩展节点
	Collection<Relationship> extendById(@Param("id") int id);
	

	//贷款用于银承保证金
	@Query("match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy:JY]->(jyds:CK)<-[:DY]-(n:N)-[:DY]->(zh:CK)<-[dk_new:DK]-(:B)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 \r\n" + 
			"and jyds.bzjzhbz=\"是\"\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P skip {skip} limit {limit}\r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(n:N)-[:DY]->(zh:CK)<-[dk_new:DK]-(:B)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and jyds.bzjzhbz=\"是\"\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(n:N)-[:DY]->(zh:CK)<-[dk_new:DK]-(:B)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and jyds.bzjzhbz=\"是\"\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(dk_new.dksjffrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P skip {skip} limit {limit} ")
	Collection<Relationship> searchDkyyycbz(@Param("skip") int skip,@Param("limit") int limit);
	
	//贷款转入房地产
	@Query("match P=(:B)-[dk:DK]->(ckzh:CK)-[jy:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 \r\n" + 
			"and n.khmc contains \"房地产\"\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and n.khmc contains \"房地产\"\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and n.khmc contains \"房地产\"\r\n" + 
			"return P skip {skip} limit {limit} ")
	Collection<Relationship> searchDkzrfdc(@Param("skip") int skip,@Param("limit") int limit);
	
	//贷款转入融资担保
	@Query("match P=(:B)-[dk:DK]->(ckzh:CK)-[jy:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 \r\n" + 
			"and n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\"\r\n" + 
			"return P  \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\"\r\n" + 
			"return P  \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\"\r\n" + 
			"return P  ")
	Collection<Relationship> searchDkzrrzdb(@Param("skip") int skip,@Param("limit") int limit);
	
	
	//贷款回流至担保人
	@Query("match P=(:B)-[dk:DK]->(ckzh:CK)-[jy:JY]->(jyds:CK)<-[:DY]-(dbr:N)-[:DB]->(dkr:N)-[:DY]->(ckzh:CK)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(dbr:N)-[:DB]->(dkr:N)-[:DY]->(ckzh:CK)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(dbr:N)-[:DB]->(dkr:N)-[:DY]->(ckzh:CK)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"return P  ")
	Collection<Relationship> searchDkhlzdbr(@Param("skip") int skip,@Param("limit") int limit);
	
	//贷款以旧还新
	@Query("match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy.jyje>=dk_new.je*0.8 and jy.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2  \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy1.jyje >=dk_new.je*0.8 and jy1.jyje<=dk_new.je*1.2 and jy2.jyje>=dk_new.je*0.8 and jy2.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2  \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy1.jyje>=dk_new.je*0.8 and jy1.jyje<=dk_new.je*1.2 \r\n" + 
			"and jy2.jyje>=dk_new.je*0.8 and jy2.jyje<=dk_new.je*1.2\r\n" + 
			"and jy3.jyje>=dk_new.je*0.8 and jy3.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2 skip {skip} limit {limit} ")
	Collection<Relationship> searchDkyjhx(@Param("skip") int skip,@Param("limit") int limit);
	
	//贷款回流至法人代表
	@Query("match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy:JY]->(jydszh:CK)<-[:DY]-(jyds:N),(ckzh)<-[:DY]-(com:C)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2  \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and com.frdbzjhm=jyds.zjhm\r\n" + 
			"return P skip {skip} limit {limit} \r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy1:JY]->(:CK)-[jy2:JY]->(jydszh:CK)<-[:DY]-(jyds:N),(ckzh)<-[:DY]-(com:C)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and com.frdbzjhm=jyds.zjhm \r\n" + 
			"return P  skip {skip} limit {limit}\r\n" + 
			"union\r\n" + 
			"match P=(:B)-[dk:DK]->(ckzh:CK:Com)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jydszh:CK)<-[:DY]-(jyds:N),(ckzh)<-[:DY]-(com:C)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and com.frdbzjhm=jyds.zjhm \r\n" + 
			"return P skip {skip} limit {limit} ")
	Collection<Relationship> searchDkhlzfrdb(@Param("skip") int skip,@Param("limit") int limit);
	
	//贷款发放至员工
	@Query("match P=(b:B)-[dk:DK]->(ckzh:CK:Per)<-[:DY]-(n:P:N)where n.zhmc<>"+"\"\\\\N\""+" and n.yxjgdm=b.yxjgdm return P skip {skip} limit {limit} ")
	Collection<Relationship> searchDkffzyg(@Param("skip") int skip,@Param("limit") int limit);
	
	
	//消费贷转入房地产
	@Query("match P=(:B)-[dk:DK{dklx:\"消费贷款\"}]->(ckzh:CK)-[jy:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
				"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
				"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
				"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 \r\n" + 
				"and n.khmc contains \"房地产\"\r\n" + 
				"return P  skip {skip} limit {limit}\r\n" + 
				"union\r\n" + 
				"match P=(:B)-[dk:DK{dklx:\"消费贷款\"}]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
				"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
				"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
				"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
				"and n.khmc contains \"房地产\"\r\n" + 
				"return P  skip {skip} limit {limit}\r\n" + 
				"union\r\n" + 
				"match P=(:B)-[dk:DK{dklx:\"消费贷款\"}]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
				"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
				"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
				"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
				"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
				"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
				"and n.khmc contains \"房地产\"\r\n" + 
				"return P skip {skip} limit {limit} ")
	Collection<Relationship> searchXfdkzrfdc(@Param("skip") int skip,@Param("limit") int limit);
		
	//通过输入条件扩展节点
	@Query("match (n:CK)-[r:JY]->(p:CK) where id(n)={id} and r.jyje>toInteger({minJYJE}) and r.jyje<toInteger({maxJYJE}) and toInteger(r.jyrq)>toInteger({minJYRQ}) and toInteger(r.jyrq)<toInteger({maxJYRQ}) return n,r,p")
	Collection<Relationship> searchJydsByInput(@Param("id")int id, @Param("minJYJE")String minJYJE, @Param("maxJYJE")String maxJYJE, @Param("minJYRQ")String minJYRQ,@Param("maxJYRQ")String maxJYRQ);
	//通过公司名称查找节点和相关关联节点
	@Query("match (n:C)-[r:GL]-(p) where n.khmc contains {companyName} RETURN n,r,p")
	Collection<Relationship> findByCompanyName(@Param("companyName") String companyName);
	//通过公司机构代码查找节点和相关关联节点
	@Query("match (n:C)-[r:GL]-(p) where n.creditcode contains {companyJgdm} RETURN n,r,p")
	Collection<Relationship> findByCompanyJgdm(@Param("companyJgdm") String companyJgdm);
	
}
