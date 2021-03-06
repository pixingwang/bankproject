package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;




@RelationshipEntity(type = "DB")    //担保关系
public class DB extends Relationship{
	
	@Property(name="yxjgdm")
	public String yxjgdm;       // 银行机构代码
	
	@Property(name="dbrzjhm")
	protected String dbrzjhm;   //代办人证件号码
	
	@Property(name="dbqsrq")
	protected String dbqsrq;   //担保起始日期
	
	@Property(name="dbqsrq_sj")
	protected String dbqsrq_sj;  //
	
	@Property(name="dbdqrq")
	protected String dbdqrq;    //担保到期日期
	
	@Property(name="dbdqrq_sj")
	protected String dbdqrq_sj;
	
	@Property(name="dkrzjhm")
	protected String dkrzjhm;   //
	
	@Property(name="yxjgmc")
	protected String yxjgmc;
	
	@Property(name="dbzje")
	protected double dbzje;     //担保总金额
	
	@Property(name="dbbz")
	protected String dbbz;    //担保币种
	
	@Property(name="db")
	protected String db;      //担保
	
	@Property(name="dbhtzt")
	protected String dbhtzt;  //担保合同状态
	
	@Property(name="yxjgmc_zh")
	protected String yxjgmc_zh;   
	
	
	
	public String getYxjgmc_zh() {
		return yxjgmc_zh;
	}

	@StartNode
	protected Node StartNode;
	@EndNode
	protected Node EndNode;
	public Node getStartNode() {
		return StartNode;
	}
	public Node getEndNode() {
		return EndNode;
	}
	
	public static String label="DB";

	public String getLabel() {
		return label;
	}

	public String getYxjgdm() {
		return yxjgdm;
	}
	public String getDbrzjhm() {
		return dbrzjhm;
	}
	public String getDbqsrq() {
		return dbqsrq;
	}
	public String getDbqsrq_sj() {
		return dbqsrq_sj;
	}
	public String getDbdqrq() {
		return dbdqrq;
	}
	public String getDbdqrq_sj() {
		return dbdqrq_sj;
	}
	public String getDkrzjhm() {
		return dkrzjhm;
	}
	public String getYxjgmc() {
		return yxjgmc;
	}
	public double getDbzje() {
		return dbzje;
	}
	public String getDbbz() {
		return dbbz;
	}
	public String getDb() {
		return db;
	}
	public String getDbhtzt() {
		return dbhtzt;
	}
	
	public DB(){
		
	}
}
