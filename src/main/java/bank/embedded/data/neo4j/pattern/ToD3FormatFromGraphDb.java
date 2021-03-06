package bank.embedded.data.neo4j.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Relationship;

import bank.embedded.data.neo4j.util.MapUtil;





public class ToD3FormatFromGraphDb {
	
	
	
	public Map<String, List<Map<String, Object>>> toD3FormatByrel(Collection<Relationship> relationship) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();	
		
		return toD3Format(relationship, nodes, rels);
		
			
	}
	
	
	private Map<String, List<Map<String, Object>>> toD3Format(Collection<Relationship> relationship,List<Map<String, Object>> nodes,List<Map<String, Object>> rels){
		MapUtil us=new MapUtil();
		
		
		Iterator<Relationship> result = relationship.iterator();
		
		while (result.hasNext()) {
			
			Relationship C=result.next();
			int source,target,index;
			Map<String, Object>  startNode=new HashMap<>();
			Map<String, Object> endNode=new HashMap<>();
			Map<String, Object> a=new HashMap<>();
			int sum=1;int no=1;
			boolean flagsum=true,flagno=true;
			int flag=0;
			
			startNode=us.map(C.getStartNode());
			endNode=us.map(C.getEndNode());
			
			
			index=us.getIndex(startNode, nodes);
				if(index==-1) {
					nodes.add(startNode);
					source=nodes.size()-1;
				}else {
					source=index;
				}
			index=us.getIndex(endNode, nodes);
				if(index==-1) {
					nodes.add(endNode);
					target=nodes.size()-1;
				}else {
					target=index;
				}
				
			a=us.map("source", source, "target", target,C);

			for(int j=rels.size()-1;j>=0;j--){
				boolean hasa=(int)rels.get(j).get("source")==source&&(int)rels.get(j).get("target")==target;
				boolean hasb=(int)rels.get(j).get("source")==target&&(int)rels.get(j).get("target")==source;
				
				
				if(flag==0) {
					if(hasa||hasb){
						if(flagsum){
						sum=(int)rels.get(j).get("sum")+1;
						flagsum=false;
						}
						
						if(flagno){
						 no=(int)rels.get(j).get("no")+1;
						 flagno=false;
						}	
						rels.get(j).replace("sum", sum);	
					}
				}
			}
			a.put("sum",sum);
			a.put("no",no);
			rels.add(a);
		}
		return us.map("nodes", nodes, "links", rels);	

	}

	

}
