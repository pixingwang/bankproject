package bank.spring.data.neo4j.repositories;



import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bank.spring.data.neo4j.domain.Acount;



@Repository
public interface DBfileRepository extends CrudRepository<Acount, Long>{
	
	@Query("USING PERIODIC COMMIT 10000 "+
			"LOAD CSV WITH HEADERS FROM 'file:///P2P.csv' AS csvLine "
	        +"merge (p:P { code:csvLine.FROM_CUST_CODE}) "
			+"set p.no=csvLine.FROM_CUST_NO "
	        +"set p.name=csvLine.FROM_CUST_NAME "
	        +"merge(q:P {code:csvLine.TO_CUST_CODE}) "
			+"set q.no=csvLine.TO_CUST_NO "
	        +"set q.name=csvLine.TO_CUST_NAME "
	        +"with csvLine,p,q "
			+"foreach(n in case when csvLine.REL_TYPE='DB'then [1] else [] end |"
	        +"create (p)-[r:DB{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE='JY'then [1] else [] end |"
	        +"create (p)-[r:JY{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['FR','GD','CW','DS','JS','ZJL','KZRGL'] then [1] else [] end |"
	        +"create (p)-[r:KZ{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['GL','QT']then [1] else [] end |"
	        +"create (p)-[r:QT{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) ") 
	@Transactional
	void LoadP2P();
	
	@Query("USING PERIODIC COMMIT 10000 "+
			"LOAD CSV WITH HEADERS FROM 'file:///P2C.csv' AS csvLine "
	        +"merge (p:P { code:csvLine.FROM_CUST_CODE}) "
			+"set p.no=csvLine.FROM_CUST_NO "
	        +"set p.name=csvLine.FROM_CUST_NAME "
	        +"merge(q:C {code:csvLine.TO_CUST_CODE}) "
			+"set q.no=csvLine.TO_CUST_NO "
	        +"set q.name=csvLine.TO_CUST_NAME "
	        +"with csvLine,p,q "
			+"foreach(n in case when csvLine.REL_TYPE='DB'then [1] else [] end |"
	        +"create (p)-[r:DB{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE='JY'then [1] else [] end |"
	        +"create (p)-[r:JY{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['FR','GD','CW','DS','JS','ZJL','KZRGL'] then [1] else [] end |"
	        +"create (p)-[r:KZ{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['GL','QT']then [1] else [] end |"
	        +"create (p)-[r:QT{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) ") 
	@Transactional
	void LoadP2C();
	
	@Query("USING PERIODIC COMMIT 10000 "+
			"LOAD CSV WITH HEADERS FROM 'file:///C2P.csv' AS csvLine "
	        +"merge (p:C { code:csvLine.FROM_CUST_CODE}) "
			+"set p.no=csvLine.FROM_CUST_NO "
	        +"set p.name=csvLine.FROM_CUST_NAME "
	        +"merge(q:P {code:csvLine.TO_CUST_CODE}) "
			+"set q.no=csvLine.TO_CUST_NO "
	        +"set q.name=csvLine.TO_CUST_NAME "
	        +"with csvLine,p,q "
			+"foreach(n in case when csvLine.REL_TYPE='DB'then [1] else [] end |"
	        +"create (p)-[r:DB{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE='JY'then [1] else [] end |"
	        +"create (p)-[r:JY{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['FR','GD','CW','DS','JS','ZJL','KZRGL'] then [1] else [] end |"
	        +"create (p)-[r:KZ{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['GL','QT']then [1] else [] end |"
	        +"create (p)-[r:QT{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) ")
	@Transactional
	void LoadC2P();
	
	@Query("USING PERIODIC COMMIT 10000 "+
			"LOAD CSV WITH HEADERS FROM 'file:///C2C.csv' AS csvLine "
	        +"merge (p:C { code:csvLine.FROM_CUST_CODE}) "
			+"set p.no=csvLine.FROM_CUST_NO "
	        +"set p.name=csvLine.FROM_CUST_NAME "
	        +"merge(q:C {code:csvLine.TO_CUST_CODE}) "
			+"set q.no=csvLine.TO_CUST_NO "
	        +"set q.name=csvLine.TO_CUST_NAME "
	        +"with csvLine,p,q "
			+"foreach(n in case when csvLine.REL_TYPE='DB'then [1] else [] end |"
	        +"create (p)-[r:DB{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE='JY'then [1] else [] end |"
	        +"create (p)-[r:JY{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['FR','GD','CW','DS','JS','ZJL','KZRGL'] then [1] else [] end |"
	        +"create (p)-[r:KZ{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) "
			+"foreach(n in case when csvLine.REL_TYPE in ['GL','QT']then [1] else [] end |"
	        +"create (p)-[r:QT{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(q)) ") 
	@Transactional
	void LoadC2C();
	
	@Query("CREATE INDEX ON :P(code)") 
	void CreatePindex();
	
	@Query("CREATE INDEX ON :C(code)") 
	void CreateCindex();
	
	@Query("MATCH (n) DETACH DELETE n") 
	void DeleteDB();
	
	
}
