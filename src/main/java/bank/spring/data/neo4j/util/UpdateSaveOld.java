package bank.spring.data.neo4j.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class UpdateSaveOld {
	UtilService us=new UtilService();
	
	public Map<String, List<Map<String, Object>>> update(String nodes,String links){
		
		
		
		List<Map<String,Object>> new_nodes=new ArrayList<>();
		
		JSONArray json_node=JSONArray.fromObject(nodes);
		
		
		
		if(json_node.size()>0){
				for(int i=0;i<json_node.size();i++){
					JSONObject job = json_node.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					Map<String, Object> result = new HashMap<String, Object>();
				
					
					
					switch((String)job.get("label")) {
					case "B":
						result.put("yxjgdm",job.get("yxjgdm"));
						result.put("jglb",job.get("jglb"));
						result.put("yyzt",job.get("yyzt"));
						result.put("jgdz",job.get("jgdz"));
						result.put("jggzkssj",job.get("jggzkssj"));
						result.put("fzrxm",job.get("fzrxm"));
						result.put("cjrq",job.get("cjrq"));
						result.put("jrxkzh",job.get("jrxkzh"));
						result.put("fzrlxdh",job.get("fzrlxdh"));
						result.put("nbjgh",job.get("nbjgh"));
						result.put("fzrzw",job.get("fzrzw"));
						result.put("clsj",job.get("clsj"));
						result.put("yxjgmc",job.get("yxjgmc"));
						result.put("yzbm",job.get("yzbm"));
						result.put("jggzzzsj",job.get("jggzzzsj"));
						result.put("wdh",job.get("wdh"));
						break;
						
					case "Per":
					case "Com":
						result.put("creditcode",job.get("creditcode"));
						result.put("yxjgdm",job.get("yxjgdm"));
						result.put("yxjgmc",job.get("yxjgmc"));
						result.put("ckzh",job.get("ckzh"));
						result.put("bzjzhbz",job.get("bzjzhbz"));
						result.put("ckye",job.get("ckye"));
						result.put("zhmc",job.get("zhmc"));
						result.put("khtybh",job.get("khtybh"));
						result.put("bz",job.get("bz"));
						break;
					case "P":
						result.put("khxm",job.get("khxm"));
						result.put("csrq",job.get("csrq"));
						result.put("gh",job.get("gh"));
						result.put("yxjgdm",job.get("yxjgdm"));
						result.put("gj",job.get("gj"));
						result.put("yxjgmc",job.get("yxjgmc"));
						result.put("xb",job.get("xb"));
						result.put("sfygbz",job.get("sfygbz"));
						result.put("mz",job.get("mz"));
						result.put("zhmc",job.get("zhmc"));
						result.put("zy",job.get("zy"));
						result.put("creditcode",job.get("creditcode"));
						if(job.has("flag")) {
							result.put("flag", job.get("flag"));
						}
						break;
					case "C":
						result.put("khmc",job.get("khmc"));
						result.put("zzc",job.get("zzc"));
						result.put("jzc",job.get("jzc"));
						result.put("fxyjxh",job.get("fxyjxh"));
						result.put("xzqhdm",job.get("xzqhdm"));
						result.put("zcdz",job.get("zcdz"));
						result.put("ssxy",job.get("ssxy"));
						result.put("zczb",job.get("zczb"));
						result.put("frdb",job.get("frdb"));
						result.put("sszb",job.get("sszb"));
						result.put("frdbzjhm",job.get("frdbzjhm"));
						result.put("creditcode",job.get("creditcode"));
						if(job.has("flag")) {
							result.put("flag", job.get("flag"));
						}
						break;
					}
					
					result.put("id", job.get("id"));
					result.put("label", job.get("label"));
					new_nodes.add(result);
				}
				
		}

		List<Map<String,Object>> new_links=new ArrayList<>();
		
		JSONArray json_link=JSONArray.fromObject(links);
		
		if(json_link.size()>0){
				for(int i=0;i<json_link.size();i++){
					JSONObject job = json_link.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					Map<String, Object> result = new HashMap<String, Object>();
					int source=0,target=0;
					
					result.put("no", job.get("no"));
					result.put("sum", job.get("sum"));
					result.put("id", job.get("id"));
					result.put("label", job.get("label"));


					
					JSONObject sourceJs=(JSONObject) job.get("source");
					int sourceId=sourceJs.getInt("id");
					for(int j=0;j<new_nodes.size();j++) {
						if(sourceId==(int)new_nodes.get(j).get("id")) {
							source=j;
						}
					}
					result.put("source",source);
					JSONObject targetJs=(JSONObject) job.get("target");
					int targetId=targetJs.getInt("id");
					for(int j=0;j<new_nodes.size();j++) {
						if(targetId==(int)new_nodes.get(j).get("id")) {
							target=j;
						}
					}
					result.put("target", target);
					switch((String)job.get("label")) {
					case "DB":
						result.put("yxjgmc_zh",job.get("yxjgmc_zh"));
						result.put("yxjgdm",job.get("yxjgdm"));
						result.put("dbrzjhm",job.get("dbrzjhm"));
						result.put("dbqsrq", job.get("dbqsrq"));
						result.put("dbqsrq_sj",job.get("dbqsrq_sj"));
						result.put("dbdqrq",job.get("dbdqrq"));
						result.put("dbdqrq_sj",job.get("dbdqrq_sj"));
						result.put("dkrzjhm", job.get("dkrzjhm"));
						result.put("yxjgmc",job.get("yxjgmc"));
						result.put("dbzje",job.get("dbzje"));
						result.put("dbbz", job.get("dbbz"));
						result.put("db",job.get("db"));
						result.put("dbhtzt",job.get("dbhtzt"));
						
						break;
					case "JY":
						result.put("bz",job.get("bz"));
						result.put("yxjgdm",job.get("yxjgdm"));
						result.put("zhye",job.get("zhye"));
						result.put("cjrq",job.get("cjrq"));
						
						result.put("jyzh",job.get("jyzh"));
						result.put("jyqd",job.get("jyqd"));
						result.put("ywlx",job.get("ywlx"));
						result.put("jysj",job.get("jysj"));
						
						result.put("hxjylsh",job.get("hxjylsh"));
						result.put("dfxh",job.get("dfxh"));
						result.put("jyjdbz",job.get("jyjdbz"));
						result.put("mxkmbh",job.get("mxkmbh"));
						
						result.put("jyjgmc",job.get("jyjgmc"));
						result.put("xzbz",job.get("xzbz"));
						result.put("jyhm",job.get("jyhm"));
						result.put("dfjgmc",job.get("dfjgmc"));
						
						result.put("jyjzh",job.get("jyjzh"));
						result.put("dfhm",job.get("dfhm"));
						result.put("kxhbz",job.get("kxhbz"));
						result.put("jylx",job.get("jylx"));
						
						result.put("jygx",job.get("jygx"));
						result.put("czgyh",job.get("czgyh"));
						result.put("jyje",job.get("jyje"));
						result.put("jyrq",job.get("jyrq"));
						
						result.put("zy",job.get("zy"));
						result.put("zhbz",job.get("zhbz"));
						result.put("bcxh",job.get("bcxh"));
						result.put("jyrq_sj",job.get("jyrq_sj"));
						result.put("jyjgmc_zh",job.get("jyjgmc_zh"));
						result.put("dfzh",job.get("dfzh"));
		
						break;
					case "DK":
						result.put("bz",job.get("bz"));
						result.put("yxjgdm", job.get("yxjgdm"));
						result.put("khmc",job.get("khmc"));
						result.put("dkqx",job.get("dkqx"));
						result.put("hkzh", job.get("hkzh"));
						result.put("dkrzzh",job.get("dkrzzh"));
						result.put("xdywzl",job.get("xdywzl"));
						result.put("xdyxm", job.get("xdyxm"));
						result.put("zjhm",job.get("zjhm"));
						result.put("dksjdqrq_sj",job.get("dksjdqrq_sj"));
						result.put("zydbfs", job.get("zydbfs"));
						result.put("dkyt",job.get("dkyt"));
						result.put("bzjje",job.get("bzjje"));
						result.put("dkgx", job.get("dkgx"));
						result.put("dksjdqrq",job.get("dksjdqrq"));
						result.put("zqs",job.get("zqs"));
						result.put("zqcs",job.get("zqcs"));
						result.put("dkwjfl",job.get("dkwjfl"));
						result.put("hkfs",job.get("hkfs"));
						result.put("xdygh", job.get("xdygh"));
						result.put("khtybh",job.get("khtybh"));
						result.put("dksjffrq",job.get("dksjffrq"));
						result.put("yxjgmc_zh",job.get("yxjgmc_zh"));
						result.put("dktxxy",job.get("dktxxy"));
						result.put("xdjjh",job.get("xdjjh"));
						result.put("xdhth", job.get("xdhth"));
						result.put("je",job.get("je"));
						result.put("jkye",job.get("jkye"));
						result.put("dklx", job.get("dklx"));
						result.put("fkfs",job.get("fkfs"));
						result.put("dksjffrq_sj",job.get("dksjffrq_sj"));
						result.put("yxjgmc",job.get("yxjgmc"));
						break;
					case "DY":
						break;
					case "ZDB":
						result.put("zdbje",job.get("zdbje"));
						result.put("zdbcs",job.get("zdbcs"));
						break;
					case "GL":
						result.put("gxlx",job.get("gxlx"));
						break;
					}
					new_links.add(result);
				}
				
		}

		
		return us.map("nodes",new_nodes,"links",new_links);
	}

}
