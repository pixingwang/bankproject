package bank.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="B")
public class Bank extends Entity{
	@Property(name="yxjgdm")
	public String yxjgdm; //银行机构代码
	
	@Property(name="jglb")
	public String jglb;    //机构类别
	
	@Property(name="yyzt")
	public String yyzt;   //营业状态
	
	@Property(name="jgdz")
	public String jgdz;   //机构地址
	
	@Property(name="jggzkssj")
	public String jggzkssj;   //机构工作终止时间
	
	@Property(name="fzrxm")
	public String fzrxm;     //负责人姓名
	
	@Property(name="cjrq")
	public String cjrq;      //采集日期
	
	@Property(name="jrxkzh")
	public String jrxkzh;   //金融许可证号
	
	@Property(name="fzrlxdh")
	public String fzrlxdh;   //负责人联系电话
	
	@Property(name="nbjgh")
	public String nbjgh;     //内部机构号
	
	@Property(name="fzrzw")
	public String fzrzw;     //负责人职务
	
	@Property(name="clsj")
	public String clsj;      //成立时间
	
	@Property(name="yxjgmc")
	public String yxjgmc;    //银行机构名称
	
	@Property(name="yzbm")
	public String yzbm;     //邮政编码
	
	@Property(name="jggzzzsj")
	public String jggzzzsj;   //机构工作终止时间
	
	@Property(name="wdh")
	public String wdh;       //网点号
	
	public String getYxjgdm() {
		return yxjgdm;
	}
	public String getJglb() {
		return jglb;
	}
	public String getYyzt() {
		return yyzt;
	}
	public String getJgdz() {
		return jgdz;
	}
	public String getJggzkssj() {
		return jggzkssj;
	}
	public String getFzrxm() {
		return fzrxm;
	}
	public String getCjrq() {
		return cjrq;
	}
	public String getJrxkzh() {
		return jrxkzh;
	}
	public String getFzrlxdh() {
		return fzrlxdh;
	}
	public String getNbjgh() {
		return nbjgh;
	}
	public String getFzrzw() {
		return fzrzw;
	}
	public String getClsj() {
		return clsj;
	}
	public String getYxjgmc() {
		return yxjgmc;
	}
	public String getYzbm() {
		return yzbm;
	}
	public String getJggzzzsj() {
		return jggzzzsj;
	}
	public String getWdh() {
		return wdh;
	}
	
	public static String label="B";
	
	public String getLabel() {
		return label;
	}

}
