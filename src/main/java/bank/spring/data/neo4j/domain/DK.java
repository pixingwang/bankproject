package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * 贷款关系
 * @author dell
 *
 */
@RelationshipEntity(type = "DK")    
public class DK extends Relationship{
	
	@Property(name="bz")
	public String bz;       //币种
	
	@Property(name="yxjgdm")
	public String yxjgdm;   //银行机构代码
	
	@Property(name="khmc")
	public String khmc;     //
	
	@Property(name="dkqx")
	public String dkqx;     //
	
	@Property(name="hkzh")
	public String hkzh;     //
	
	@Property(name="dkrzzh")
	public String dkrzzh;    //
	
	@Property(name="xdywzl")
	public String xdywzl;    //
	
	@Property(name="xdyxm")
	public String xdyxm;      //
	
	@Property(name="zjhm")
	public String zjhm;       //
	
	@Property(name="dksjdqrq_sj")
	public String dksjdqrq_sj;   //
	
	@Property(name="zydbfs")
	public String zydbfs;      //
	
	@Property(name="dkyt")
	public String dkyt;        //
	
	@Property(name="bzjje")
	public String bzjje;        //
	
	@Property(name="dkgx")
	public String dkgx;         //
	
	@Property(name="dksjdqrq")
	public String dksjdqrq;     //
	
	@Property(name="zqs")
	public String zqs;          //
	
	@Property(name="zqcs")
	public String zqcs;         //
	
	@Property(name="dkwjfl")
	public String dkwjfl;       //
	
	@Property(name="hkfs")
	public String hkfs;         //
	
	@Property(name="xdygh")
	public String xdygh;        //
	
	@Property(name="khtybh")
	public String khtybh;       //
	
	@Property(name="dksjffrq")
	public String dksjffrq;     //
	
	@Property(name="yxjgmc_zh")
	public String yxjgmc_zh;    //
	
	@Property(name="dktxxy")
	public String dktxxy;       //
	
	@Property(name="xdjjh")
	public String xdjjh;        //
	
	@Property(name="xdhth")
	public String xdhth;        //
	
	@Property(name="je")
	public double je;           //金额
	
	@Property(name="jkye")
	public double jkye;         //
	
	@Property(name="dklx")
	public String dklx;         //贷款类型
	
	@Property(name="fkfs")
	public String fkfs;         //
	
	@Property(name="dksjffrq_sj")
	public String dksjffrq_sj;   //
	
	@Property(name="yxjgmc")
	public String yxjgmc;       //
	
	@StartNode
	protected Bank StartNode;
	@EndNode
	protected Acount EndNode;
	public String getYxjgmc() {
		return yxjgmc;
	}
	public Bank getStartNode() {
		return StartNode;
	}
	public Acount getEndNode() {
		return EndNode;
	}
	
	public static String label="DK";

	public String getLabel() {
		return label;
	}
	public String getBz() {
		return bz;
	}
	public String getYxjgdm() {
		return yxjgdm;
	}
	public String getKhmc() {
		return khmc;
	}
	public String getDkqx() {
		return dkqx;
	}
	public String getHkzh() {
		return hkzh;
	}
	public String getDkrzzh() {
		return dkrzzh;
	}
	public String getXdywzl() {
		return xdywzl;
	}
	public String getXdyxm() {
		return xdyxm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public String getDksjdqrq_sj() {
		return dksjdqrq_sj;
	}
	public String getZydbfs() {
		return zydbfs;
	}
	public String getDkyt() {
		return dkyt;
	}
	public String getBzjje() {
		return bzjje;
	}
	public String getDkgx() {
		return dkgx;
	}
	public String getDksjdqrq() {
		return dksjdqrq;
	}
	public String getZqs() {
		return zqs;
	}
	public String getZqcs() {
		return zqcs;
	}
	public String getDkwjfl() {
		return dkwjfl;
	}
	public String getHkfs() {
		return hkfs;
	}
	public String getXdygh() {
		return xdygh;
	}
	public String getKhtybh() {
		return khtybh;
	}
	public String getDksjffrq() {
		return dksjffrq;
	}
	public String getYxjgmc_zh() {
		return yxjgmc_zh;
	}
	public String getDktxxy() {
		return dktxxy;
	}
	public String getXdjjh() {
		return xdjjh;
	}
	public String getXdhth() {
		return xdhth;
	}
	public double getJe() {
		return je;
	}
	public double getJkye() {
		return jkye;
	}
	public String getDklx() {
		return dklx;
	}
	public String getFkfs() {
		return fkfs;
	}
	public String getDksjffrq_sj() {
		return dksjffrq_sj;
	}
	public DK(){
		
	}
	
	
}
