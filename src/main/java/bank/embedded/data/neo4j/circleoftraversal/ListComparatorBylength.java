package bank.embedded.data.neo4j.circleoftraversal;

import java.util.Comparator;

import bank.embedded.data.neo4j.util.DbCircleInfo;





public class ListComparatorBylength implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		DbCircleInfo ob1=(DbCircleInfo)o1;
		DbCircleInfo ob2=(DbCircleInfo)o2;
		int flag=ob1.getLength()-ob2.getLength();
		
		if(flag==0) {
			return ob1.getTotalDBJE()-ob2.getTotalDBJE()>0?1:-1;
		}else {
			return flag;
		}
	
	}
   
}