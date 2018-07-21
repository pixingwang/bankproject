package bank.spring.data.neo4j.domain;



import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="CK")
public class Acount extends Entity {
	
	@Property(name="creditcode")
	protected String creditcode;   //证件号码
	
	@Property(name="yxjgdm")
	protected String yxjgdm;       //银行机构代码
	
	@Property(name="yxjgmc")
	protected String yxjgmc;       //银行机构名称
	
	@Property(name="ckzh")
	protected String ckzh;         //存款账户
	
	@Property(name="bzjzhbz")
	protected String bzjzhbz;      //保证金账户标志
	
	@Property(name="ckye")
	protected double ckye;         //存款余额
	
	@Property(name="zhmc")
	protected String zhmc;         //账户名称
	
	@Property(name="khtybh")
	protected String khtybh;       //客户统一编号
	
	@Property(name="bz")
	protected String bz;           //币种
	
	public String getCreditcode() {
		return creditcode;
	}
	public String getYxjgdm() {
		return yxjgdm;
	}
	public String getYxjgmc() {
		return yxjgmc;
	}
	public String getCkzh() {
		return ckzh;
	}
	public String getBzjzhbz() {
		return bzjzhbz;
	}
	public double getCkye() {
		return ckye;
	}
	public String getZhmc() {
		return zhmc;
	}
	public String getKhtybh() {
		return khtybh;
	}
	public String getBz() {
		return bz;
	}
	
	public String getLabel() {
		return null;
	}
	
	
}
