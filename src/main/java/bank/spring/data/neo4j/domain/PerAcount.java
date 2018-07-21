package bank.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label="Per")
public class PerAcount extends Acount{
	public PerAcount(){}

	public PerAcount(String creditcode,String yxjgdm,String yxjgmc,String ckzh,String bzjzhbz,double ckye,String zhmc,String khtybh,String bz){
		this.creditcode=creditcode;
		this.yxjgdm=yxjgdm;
		this.yxjgmc=yxjgmc;
		this.ckzh=ckzh;
		this.bzjzhbz=bzjzhbz;
		this.ckye=ckye;
		this.zhmc=zhmc;
		this.khtybh=khtybh;
		this.bz=bz;
	}
	
	public static String label="Per";
	
	public String getLabel() {
		return label;
	}
}
