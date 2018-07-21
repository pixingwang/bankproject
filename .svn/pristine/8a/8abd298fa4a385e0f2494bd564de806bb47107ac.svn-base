package bank.spring.data.neo4j.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import bank.spring.data.neo4j.domain.*;


public class UtilService {
	
	Person person;
	Company company;
	DB db;
	DK dk;
	JY jy;
	ZDB zdb;
	GL gl;
	

	public Map<String, List<Map<String, Object>>> map(String key1, List<Map<String, Object>> value1, String key2, List<Map<String, Object>> value2) {
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

	public Map<String, Object> map(Acount c){          
		Map<String, Object> result = new HashMap<String, Object>();
		
			result.put("id",c.getId());
			result.put("label",c.getLabel());
			result.put("creditcode",c.getCreditcode());
			result.put("yxjgdm",c.getYxjgdm());
			result.put("yxjgmc",c.getYxjgmc());
			result.put("ckzh",c.getCkzh());
			result.put("bzjzhbz",c.getBzjzhbz());
			result.put("ckye",c.getCkye());
			result.put("zhmc",c.getZhmc());
			result.put("khtybh",c.getKhtybh());
			result.put("bz",c.getBz());
			
		return result;
	}
	public Map<String, Object> map(Node c){
		Map<String, Object> result = new HashMap<String, Object>();
			if(c.getLabel()=="P") {
				person=(Person)c;
				result.put("khxm",person.getKhxm());
				result.put("csrq",person.getCsrq());
				result.put("gh",person.getGh());
				result.put("yxjgdm",person.getYxjgdm());
				result.put("gj",person.getGj());
				result.put("yxjgmc",person.getYxjgmc());
				result.put("xb",person.getXb());
				result.put("sfygbz",person.getSfygbz());
				result.put("mz",person.getMz());
				result.put("zhmc",person.getZhmc());
				result.put("zy",person.getZy());
			}else {				
				company=(Company)c;
				result.put("khmc",company.getKhmc());
				result.put("zzc",company.getZzc());
				result.put("jzc",company.getJzc());
				result.put("fxyjxh",company.getFxyjxh());
				result.put("xzqhdm",company.getXzqhdm());
				result.put("zcdz",company.getZcdz());
				result.put("ssxy",company.getSsxy());
				result.put("zczb",company.getZczb());
				result.put("frdb",company.getFrdb());
				result.put("sszb",company.getSszb());
				result.put("frdbzjhm",company.getFrdbzjhm());
			}
			if(c.getFlag()!=null) {
				result.put("flag", "危险");
			}
			result.put("creditcode",c.getCreditcode());
			result.put("id",c.getId());
			result.put("label",c.getLabel());
		return result;
	}
	public Map<String, Object> map(Bank c){
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("yxjgdm",c.getYxjgdm());
		result.put("jglb",c.getJglb());
		result.put("yyzt",c.getYyzt());
		result.put("jgdz",c.getJgdz());
		result.put("jggzkssj",c.getJggzkssj());
		result.put("fzrxm",c.getFzrxm());
		result.put("cjrq",c.getCjrq());
		result.put("jrxkzh",c.getJrxkzh());
		result.put("fzrlxdh",c.getFzrlxdh());
		result.put("nbjgh",c.getNbjgh());
		result.put("fzrzw",c.getFzrzw());
		result.put("clsj",c.getClsj());
		result.put("yxjgmc",c.getYxjgmc());
		result.put("yzbm",c.getYzbm());
		result.put("jggzzzsj",c.getJggzzzsj());
		result.put("wdh",c.getWdh());
		
		result.put("id",c.getId());
		result.put("label",c.getLabel());
		return result;
	}
	
	public Map<String, Object> map(String key1, Object value1, String key2, Object value2,Relationship r) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		if(r.getLabel()=="DB") {
			db=(DB)r;
			result.put("yxjgmc_zh",db.getYxjgmc_zh());
			result.put("yxjgdm",db.getYxjgdm());
			result.put("dbrzjhm",db.getDbrzjhm());
			result.put("dbqsrq", db.getDbqsrq());
			result.put("dbqsrq_sj",db.getDbqsrq_sj());
			result.put("dbdqrq",db.getDbdqrq());
			result.put("dbdqrq_sj",db.getDbdqrq_sj());
			result.put("dkrzjhm", db.getDbrzjhm());
			result.put("yxjgmc",db.getYxjgmc());
			result.put("dbzje",db.getDbzje());
			result.put("dbbz", db.getDbbz());
			result.put("db",db.getDb());
			result.put("dbhtzt",db.getDbhtzt());
			
		}
		if(r.getLabel()=="DK") {
			dk=(DK)r;
			result.put("bz",dk.getBz());
			result.put("yxjgdm", dk.getYxjgdm());
			result.put("khmc",dk.getKhmc());
			result.put("dkqx",dk.getDkqx());
			result.put("hkzh", dk.getHkzh());
			result.put("dkrzzh",dk.getDkrzzh());
			result.put("xdywzl",dk.getXdywzl());
			result.put("xdyxm", dk.getXdyxm());
			result.put("zjhm",dk.getZjhm());
			result.put("dksjdqrq_sj",dk.getDksjdqrq_sj());
			result.put("zydbfs", dk.getZydbfs());
			result.put("dkyt",dk.getDkyt());
			result.put("bzjje",dk.getBzjje());
			result.put("dkgx", dk.getDkgx());
			result.put("dksjdqrq",dk.getDksjdqrq());
			result.put("zqs",dk.getZqs());
			result.put("zqcs", dk.getZqcs());
			result.put("dkwjfl",dk.getDkwjfl());
			result.put("hkfs",dk.getHkfs());
			result.put("xdygh", dk.getXdygh());
			result.put("khtybh",dk.getKhtybh());
			result.put("dksjffrq",dk.getDksjffrq());
			result.put("yxjgmc_zh", dk.getYxjgmc_zh());
			result.put("dktxxy",dk.getDktxxy());
			result.put("xdjjh",dk.getXdjjh());
			result.put("xdhth", dk.getXdhth());
			result.put("je",dk.getJe());
			result.put("jkye",dk.getJkye());
			result.put("dklx", dk.getDklx());
			result.put("fkfs",dk.getFkfs());
			result.put("dksjffrq_sj",dk.getDksjffrq_sj());
			result.put("yxjgmc",dk.getYxjgmc());
			
		}
		if(r.getLabel()=="JY") {
			jy=(JY)r;
			result.put("bz",jy.getBz());
			result.put("yxjgdm",jy.getYxjgdm());
			result.put("zhye",jy.getZhye());
			result.put("cjrq",jy.getCjrq());
			
			result.put("jyzh",jy.getJyzh());
			result.put("jyqd",jy.getJyqd());
			result.put("ywlx",jy.getYwlx());
			result.put("jysj",jy.getJysj());
			
			result.put("hxjylsh",jy.getHxjylsh());
			result.put("dfxh",jy.getDfxh());
			result.put("jyjdbz",jy.getJyjdbz());
			result.put("mxkmbh",jy.getMxkmbh());
			
			result.put("jyjgmc",jy.getJyjgmc());
			result.put("xzbz",jy.getXzbz());
			result.put("jyhm",jy.getJyhm());
			result.put("dfjgmc",jy.getDfjgmc());
			
			result.put("jyjzh",jy.getJyjzh());
			result.put("dfhm",jy.getDfhm());
			result.put("kxhbz",jy.getKxhbz());
			result.put("jylx",jy.getJylx());
			
			result.put("jygx",jy.getJygx());
			result.put("czgyh",jy.getCzgyh());
			result.put("jyje",jy.getJyje());
			result.put("jyrq",jy.getJyrq());
			
			result.put("zy",jy.getZy());
			result.put("zhbz",jy.getZhbz());
			
			result.put("bcxh",jy.getBcxh());
			result.put("jyrq_sj",jy.getJyrq_sj());
			result.put("jyjgmc_zh",jy.getJyjgmc_zh());
			result.put("dfzh",jy.getDfzh());
			
		}
		if(r.getLabel()=="ZDB") {
			zdb=(ZDB)r;
			result.put("zdbje",Double.parseDouble(zdb.getZdbje()));
			result.put("zdbcs",zdb.getZdbcs());
		}
		if(r.getLabel()=="GL") {
			gl=(GL)r;
			result.put("gxlx",gl.getGxlx());
		}
		result.put("label",r.getLabel());
		result.put("id",r.getId());
		
		return result;
	}
	

	
	public Map<String,List<Map<String, Object>>> filterForTotal(Map<String,List<Map<String, Object>>> result,String min,String max){
		
		double mymin=Double.parseDouble(min);
		double mymax=Double.parseDouble(max);
		List<Map<String,Object>> rels=result.get("links");
		
		
		//筛选不符合条件的边
		for(int i=0;i<rels.size();i++) {
			double total=(double)rels.get(i).get("total");
			if(total<mymin||total>mymax) {
				result.get("links").remove(i);
				i--;
			}
		}
		rels=result.get("links");
		
		List<Map<String,Object>> nodes=new ArrayList<>();
		Map<String,Object> startNode,endNode;
		//筛选不符合条件的节点
		Iterator<Map<String, Object>> r=rels.iterator();
		int j=0;
		while(r.hasNext()) {
			Map<String,Object> myr=r.next();
			startNode=result.get("nodes").get((int)myr.get("source"));
			endNode=result.get("nodes").get((int)myr.get("target"));
			
			int source,target;
			
			if(nodes.indexOf(startNode)==-1) {
				nodes.add(startNode);
				source=nodes.size()-1;
			}else {
				source=nodes.indexOf(startNode);
			}
			
			if(nodes.indexOf(endNode)==-1) {
				nodes.add(endNode);
				target=nodes.size()-1;
			}else {
				target=nodes.indexOf(endNode);
			}
			rels.get(j).replace("source", source);
			rels.get(j).replace("target", target);
			j++;
		}
		
		result.replace("nodes", nodes);
		result.replace("links", rels);
		
		return result;
		
	}
	
	public  String createCypherforDBCircle(int n) {//通过输入框的数据创建不同的cypher语句
		String []node=new String[n+1];
		for(int i=1;i<=n;i++) {
			String name="n";
			name=name+i;
			node[i]=name;
		}
	
		StringBuilder cypher=new StringBuilder("match circle=(n0)");
		for(int i=1;i<=n;i++) {
			cypher.append("-[:DB]->("+node[i]+")");
		}
		int length=node.length;
		cypher.append(" where n0="+node[length-1]);
		
		for(int i=0;i<n-1;i++) {
			String name="n";
			name=name+i;
			for(int j=i+1;j<n;j++) {
				cypher.append(" and "+name+"<>"+node[j]);
			}
		}
		
		cypher.append(" return circle  ");
		return cypher.toString();
	}
	
	public  String createCypherforJYChain(int id,int n,String minJYJE, String maxJYJE, String minJYRQ,String maxJYRQ) {//通过输入框的数据创建不同的cypher语句
		String []rel=new String[n+1];
		for(int i=1;i<=n;i++) {
			String name="n";
			name=name+i;
			rel[i]=name;
		}
	
		StringBuilder cypher=new StringBuilder("match JYChain=(n0:CK) ");
		for(int i=1;i<=n;i++) {
			cypher.append("-["+rel[i]+":JY]->(:CK)");
		}
		
		cypher.append(" where id(n0)="+id);
		
		for(int i=1;i<=n;i++){
			cypher.append(" and "+rel[i]+".jyje>toInteger("+minJYJE+") and "+rel[i]+".jyje<toInteger("+maxJYJE+") and toInteger("+rel[i]+".jyrq)>toInteger("+minJYRQ+") and toInteger("+rel[i]+".jyrq)<toInteger("+maxJYRQ+") ");
		}
		
		cypher.append(" return JYChain ");
		return cypher.toString();
	}
	
	
	public int getIndex(Map<String, Object> node,List<Map<String, Object>> nodes) {
		int index=-1;
		int length=nodes.size();
		for(int i=0;i<length;i++) {
			
			if(node.get("id").toString().equals(nodes.get(i).get("id").toString())){
				index=i;
				break;
			}
			
			
		}
		return index;
	}
	
	

}
