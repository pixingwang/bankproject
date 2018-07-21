package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="C")

public class Company extends Node {
	
	@Property(name="khmc")
	public String khmc;    //对公客户
	
	@Property(name="zzc")
	public double zzc;     //总资产
	
	@Property(name="jzc")
	public double jzc;    //净资产
	
	@Property(name="fxyjxh")
	public String fxyjxh;  //风险预警信号
	
	@Property(name="xzqhdm")
	public String xzqhdm;  //行政区划代码
	
	@Property(name="zcdz")
	public String zcdz;   //注册地址
	
	@Property(name="ssxy")
	public String ssxy;   //所属行业
	
	@Property(name="zczb")
	public String zczb;   //注册资本
	
	@Property(name="frdb")
	public String frdb;   //法人代表
	
	@Property(name="sszb")
	public String sszb;   //实收资本
	
	@Property(name="frdbzjhm")
	public String frdbzjhm;   //
	
	public Company() {
		
	}
	public Company(String creditcode) {
		this.creditcode=creditcode;
		
	}
	public String getKhmc() {
		return khmc;
	}
	public double getZzc() {
		return zzc;
	}
	public double getJzc() {
		return jzc;
	}
	public String getFxyjxh() {
		return fxyjxh;
	}
	public String getXzqhdm() {
		return xzqhdm;
	}
	public String getZcdz() {
		return zcdz;
	}
	public String getSsxy() {
		return ssxy;
	}
	public String getZczb() {
		return zczb;
	}
	public String getFrdb() {
		return frdb;
	}
	public String getSszb() {
		return sszb;
	}
	public String getFrdbzjhm() {
		return frdbzjhm;
	}
	
	public static String label="C";
	
	public String getLabel() {
		return label;
	}
	
	

	

}
