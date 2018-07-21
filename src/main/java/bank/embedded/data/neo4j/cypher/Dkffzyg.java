package bank.embedded.data.neo4j.cypher;

/**
 * 向本行员工发放经营性贷款
 * @author dell
 *
 */
public class Dkffzyg extends Cypher {
	private String cypher1="match P=(b:B)-[dk:DK]->(ckzh:CK:Per)<-[:DY]-(n:P:N)where n.zhmc<>"+"\"\\\\N\""+" and n.yxjgdm=b.yxjgdm return P ";
	private String cypher2="match P=(b:B)-[dk:DK]->(ckzh:CK:Per)<-[:DY]-(n:P:N)where n.zhmc<>"+"\"\\\\N\""+" and n.yxjgdm=b.yxjgdm return P " ;
	private String cypher3="match P=(b:B)-[dk:DK]->(ckzh:CK:Per)<-[:DY]-(n:P:N)where n.zhmc<>"+"\"\\\\N\""+" and n.yxjgdm=b.yxjgdm return P ";
	
	private String patternName="dkffzyg";
	public String getCypher1() {
		return cypher1;
	}
	public String getCypher2() {
		return cypher2;
	}
	public String getCypher3() {
		return cypher3;
	}
	
	public String getPatternName() {
		return patternName;
	}
}
