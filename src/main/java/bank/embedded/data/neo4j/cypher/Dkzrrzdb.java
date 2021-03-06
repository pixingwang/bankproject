package bank.embedded.data.neo4j.cypher;
/**
 * 贷款转入融资担保类公司
 * @author dell
 *
 */
public class Dkzrrzdb extends Cypher{
	private String cypher1="match P=(:B)-[dk:DK]->(ckzh:CK)-[jy:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy.jyje>=dk.je*0.8 and jy.jyje<=dk.je*1.2 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 \r\n" + 
			"and (n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\")\r\n" + 
			"return P  \r\n" ;
	private String cypher2="match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and (n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\")\r\n" + 
			"return P  \r\n" ;
	private String cypher3="match P=(:B)-[dk:DK]->(ckzh:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(jyds:CK)<-[:DY]-(n:C:N)\r\n" + 
			"where jy1.jyje>=dk.je*0.8 and jy1.jyje<=dk.je*1.2 \r\n" + 
			"and jy2.jyje>=dk.je*0.8 and jy2.jyje<=dk.je*1.2\r\n" + 
			"and jy3.jyje>=dk.je*0.8 and jy3.jyje<=dk.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk.dksjffrq_sj)>0\r\n" + 
			"and (n.khmc contains \"典当行\" or n.khmc contains \"小额贷款\" or n.khmc contains \"担保\")\r\n" + 
			"return P  ";
	
	private String patternName="dkzrrzdb";
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
