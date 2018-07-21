package bank.embedded.data.neo4j.circleoftraversal;

import java.util.Comparator;

import bank.embedded.data.neo4j.util.DbCircleInfo;



public class ListComparatorByTotalDBJE implements Comparator<Object>{
	@Override
	public int compare(Object o1, Object o2) {
		DbCircleInfo ob1=(DbCircleInfo)o1;
		DbCircleInfo ob2=(DbCircleInfo)o2;
		int flag;
		if(ob1.getTotalDBJE()-ob2.getTotalDBJE()<0) {
			flag=-1;
		}else if(ob1.getTotalDBJE()-ob2.getTotalDBJE()==0) {
			flag=0;
		}else{
			flag=1;
		}
		
		if(flag==0) {
			return ob1.getLength()-ob2.getLength()>0?1:-1;
		}else {
			return flag;
		}
	
	}
}
