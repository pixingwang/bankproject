package bank.embedded.data.neo4j.centrality;

import java.util.Comparator;
import java.util.Map;


public class ListComparatorByJE implements Comparator<Map<String,Object>>{

		@Override
		public int compare(Map<String,Object> o1, Map<String,Object> o2) {
			
			Double ob1=(double)o1.get("总担保金额");
			Double ob2=(double)o2.get("总担保金额");
					
			return ob2.compareTo(ob1);
			
		
		}

}
