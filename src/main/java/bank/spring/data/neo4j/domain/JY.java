package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;



/**
 * 交易关系
 * @author dell
 *
 */
@RelationshipEntity(type = "JY")   
public class JY extends Relationship{
	
	@Property(name="bz")
	public String bz;      //币种
	
	@Property(name="yxjgdm")
	public String yxjgdm;   //银行机构代码
	
	@Property(name="zhye")
	protected double zhye;  //账户余额
	
	@Property(name="cjrq")
	protected String cjrq;   //采集日期
	
	@Property(name="jyzh")
	protected String jyzh;   //对方账号
	
	@Property(name="jyqd")
	protected String jyqd;   //交易渠道
	
	@Property(name="ywlx")
	protected String ywlx;   //业务类型
	
	@Property(name="jysj")
	protected String jysj;   //交易时间
	
	@Property(name="hxjylsh")
	protected String hxjylsh;  //核心交易流水号
	
	@Property(name="dfxh")
	protected String dfxh;    //对方行号
	
	@Property(name="jyjdbz")
	protected String jyjdbz;   //交易借贷标志
	
	@Property(name="mxkmbh")
	protected String mxkmbh;   //明细科目编号
	
	@Property(name="jyjgmc")
	protected String jyjgmc;   //交易机构名称
	
	@Property(name="jyjgmc_zh")
	protected String jyjgmc_zh;  //交易机构总行
	
	@Property(name="xzbz")
	protected String xzbz;    //现转标志
	
	@Property(name="jyhm")
	protected String jyhm;    //交易户名
	
	@Property(name="dfjgmc")
	protected String dfjgmc;   //对方机构名称
	
	@Property(name="jyjzh")
	protected String jyjzh;    //交易介质号
	
	@Property(name="dfhm")
	protected String dfhm;     //对方户名
	
	@Property(name="kxhbz")
	protected String kxhbz;    //开销户标志
	
	@Property(name="jylx")
	protected String jylx;     //交易类型
	
	@Property(name="jygx")
	protected String jygx;     //
	
	@Property(name="czgyh")
	protected String czgyh;    //操作柜员号
	
	@Property(name="jyje")
	protected double jyje;     //交易金额
	
	@Property(name="jyrq")
	protected String jyrq;     //交易日期
	
	@Property(name="jyrq_sj")
	protected String jyrq_sj;   //
	
	@Property(name="zy")
	protected String zy;        //
	
	@Property(name="zhbz")
	protected String zhbz;      //
	
	@Property(name="bcxh")
	protected String bcxh;       //笔次序号
	
	@Property(name="dfzh")
	protected String dfzh;       //对方账号
	
	@StartNode
	protected Acount StartNode;
	@EndNode
	protected Acount EndNode;
	
	public static String label="JY";

	public String getLabel() {
		return label;
	}
	
	public Acount getStartNode() {
		return StartNode;
	}
	public Acount getEndNode() {
		return EndNode;
	}

	public String getBz() {
		return bz;
	}

	public String getYxjgdm() {
		return yxjgdm;
	}

	public double getZhye() {
		return zhye;
	}

	public String getCjrq() {
		return cjrq;
	}

	public String getJyzh() {
		return jyzh;
	}

	public String getJyqd() {
		return jyqd;
	}

	public String getYwlx() {
		return ywlx;
	}

	public String getJysj() {
		return jysj;
	}

	public String getHxjylsh() {
		return hxjylsh;
	}

	public String getDfxh() {
		return dfxh;
	}

	public String getJyjdbz() {
		return jyjdbz;
	}

	public String getMxkmbh() {
		return mxkmbh;
	}

	public String getJyjgmc() {
		return jyjgmc;
	}

	public String getXzbz() {
		return xzbz;
	}

	public String getJyhm() {
		return jyhm;
	}

	public String getDfjgmc() {
		return dfjgmc;
	}

	public String getJyjzh() {
		return jyjzh;
	}

	public String getDfhm() {
		return dfhm;
	}

	public String getKxhbz() {
		return kxhbz;
	}

	public String getJylx() {
		return jylx;
	}

	public String getJygx() {
		return jygx;
	}

	public String getCzgyh() {
		return czgyh;
	}

	public double getJyje() {
		return jyje;
	}

	public String getJyrq() {
		return jyrq;
	}

	public String getZy() {
		return zy;
	}

	public String getZhbz() {
		return zhbz;
	}
	public String getJyjgmc_zh() {
		return jyjgmc_zh;
	}

	public String getJyrq_sj() {
		return jyrq_sj;
	}

	public String getBcxh() {
		return bcxh;
	}

	public String getDfzh() {
		return dfzh;
	}
	public JY(){
		
	}
	
	
}
