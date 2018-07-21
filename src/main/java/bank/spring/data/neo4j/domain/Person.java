package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="P")
public class Person extends Node {
	
	@Property(name="khxm")
	public String khxm;     //客户姓名
	
	@Property(name="csrq")
	public String csrq;     //出生日期
	
	@Property(name="gh")
	public String gh;      //
	
	@Property(name="yxjgdm")
	public String yxjgdm;   //银行机构代码
	
	@Property(name="gj")
	public String gj;      //国籍
	
	@Property(name="yxjgmc")
	public String yxjgmc;  //银行机构名称
	
	@Property(name="xb")
	public String xb;      //性别
	
	@Property(name="sfygbz")
	public String sfygbz;   //是否银行员工
	
	@Property(name="mz")
	public String mz;      //民族
	
	@Property(name="zhmc")
	public String zhmc;    //账户名称
	
	@Property(name="zy")
	public String zy;     //职业
	
	public Person() {}
	public Person(String creditcode) {
		this.creditcode=creditcode;
		
	}
	public String getKhxm() {
		return khxm;
	}
	public String getCsrq() {
		return csrq;
	}
	public String getGh() {
		return gh;
	}
	public String getYxjgdm() {
		return yxjgdm;
	}
	public String getGj() {
		return gj;
	}
	public String getYxjgmc() {
		return yxjgmc;
	}
	public String getXb() {
		return xb;
	}
	public String getSfygbz() {
		return sfygbz;
	}
	public String getMz() {
		return mz;
	}
	public String getZhmc() {
		return zhmc;
	}
	public String getZy() {
		return zy;
	}
	
	public static String label="P";
	
	public String getLabel() {
		return label;
	}
	

	

}
