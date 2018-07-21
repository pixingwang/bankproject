package bank.embedded.data.neo4j.cypher;
/**
 * 贷款以新还旧
 * @author dell
 *
 */
public class Dkyjhx extends Cypher{
	private String cypher1="match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy.jyje>=dk_new.je*0.8 and jy.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 \r\n" + 
			"and toInteger(jy.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2  \r\n";
	private String cypher2="match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy1.jyje >=dk_new.je*0.8 and jy1.jyje<=dk_new.je*1.2 and jy2.jyje>=dk_new.je*0.8 and jy2.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2  \r\n" ;
	private String cypher3="match P=(:B)-[dk_new:DK]->(ckzh1:CK)-[jy1:JY]->(:CK)-[jy2:JY]->(:CK)-[jy3:JY]->(ckzh2:CK)<-[dk_old:DK]-(:B),(ckzh1)<-[r1:DY]-(m)-[r2:DY]->(ckzh2)\r\n" + 
			"where jy1.jyje>=dk_new.je*0.8 and jy1.jyje<=dk_new.je*1.2 \r\n" + 
			"and jy2.jyje>=dk_new.je*0.8 and jy2.jyje<=dk_new.je*1.2\r\n" + 
			"and jy3.jyje>=dk_new.je*0.8 and jy3.jyje<=dk_new.je*1.2\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7 and toInteger(jy3.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)<7\r\n" + 
			"and toInteger(jy1.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy2.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0 and toInteger(jy3.jyrq_sj)-toInteger(dk_new.dksjffrq_sj)>0\r\n" + 
			"and ckzh2.zjhm=ckzh1.zjhm\r\n" + 
			"and dk_old.dksjdqrq_sj-dk_new.dksjffrq_sj<7\r\n" + 
			"and m.zjhm=ckzh1.zjhm\r\n" + 
			"return P,m,r1,r2  ";
	
	private String patternName="dkyjhx";
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
