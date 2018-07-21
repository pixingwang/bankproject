package bank.embedded.data.neo4j.centrality;

import java.util.Comparator;
import java.util.Map;


public class ListComparatorByDegree implements Comparator<Map<String,Object>>{

		@Override
		public int compare(Map<String,Object> o1, Map<String,Object> o2) {
			
			Integer ob1=(int)o1.get("相关节点数目");
			Integer ob2=(int)o2.get("相关节点数目");
					
			return ob2.compareTo(ob1);
		
		}

}
