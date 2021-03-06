package bank.embedded.data.neo4j.circleoftraversal;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

import bank.embedded.data.neo4j.util.DbCircleInfo;
import bank.embedded.data.neo4j.util.MapUtil;
import bank.embedded.data.neo4j.util.Taversal;
import bank.embedded.data.neo4j.util.ToD3FormatFromGraphDb;
import bank.embedded.data.neo4j.util.UpdateRdbms;





public class FindAllNodeDbCircleBybothwayOfstream {
		
		//存储担保环记录路径
		public static String path="E:\\CircleData";
		
		public static  GraphDatabaseService graphDb;
		ToD3FormatFromGraphDb td=new ToD3FormatFromGraphDb();
		
	 	MapUtil mapUtil=new MapUtil();
	 	Taversal taversal=new Taversal();
	 	DbCircleInfo dbCircleInfo=new DbCircleInfo();
	 	UpdateRdbms updateRDBMS=new UpdateRdbms();
	 	
	    ListComparatorBylength comparatorBylength=new ListComparatorBylength();
	    ListComparatorByTotalDBJE comparatorByTotalDBJE=new ListComparatorByTotalDBJE();
	    List<Long> passNodes=new ArrayList<>();
	    
	    public static enum LabelTypes implements Label

	    {

	        P,C,N

	    }
	   
	    public static enum RelTypes implements RelationshipType

	    {

	        DB,ZDB

	    }
	    @SuppressWarnings("static-access")
		public FindAllNodeDbCircleBybothwayOfstream(GraphDatabaseService graphDb) {
	    	this.graphDb=graphDb;
	    }
	    


	 	public void printAllNodeDbCircle() throws IOException {
	 		updateRDBMS.deleteTableData();
	 		
	 		List<DbCircleInfo> top_tenOflength=new ArrayList<>(10);
	 		List<DbCircleInfo> top_tenOftotalDBJE=new ArrayList<>(10);
	 		
			try(Transaction tx=graphDb.beginTx()){
				//记录各类担保环记录数
				int[] skips=new int[501];
				for(int i=1;i<501;i++) {
					skips[i]=0;
				}
				
				//遍历所有节点
				//Iterator<Node> nodes=graphDb.getAllNodes().iterator();
				//只遍历对公节点
				
				
				Iterator<Node> nodes=graphDb.findNodes(LabelTypes.C);
				
				
				//控制台输出结果
				//StringBuilder output=new StringBuilder();
				
				int sumOfNode=0;
				int maxCircleLength=0;
				//所有担保环记录数
				int records=0;
				
				while(nodes.hasNext()) {
					
					sumOfNode++;
					Node startNode=nodes.next();
					System.out.println(startNode.getId()+"---"+sumOfNode+"---"+records);
					
					if(startNode.getDegree(RelTypes.ZDB, Direction.INCOMING)==0||startNode.getDegree(RelTypes.ZDB, Direction.OUTGOING)==0) {
						continue;
					}

					    Stream<Path> spStream=taversal.dbcircle(graphDb, startNode, RelTypes.ZDB);
		                Iterator<Path> paths=spStream.iterator();
		                
		                while(paths.hasNext())
		                {
		                	Path circlePath=paths.next();
		                	DbCircleInfo circleInfo=new DbCircleInfo();
		                	
		                	circleInfo=dbCircleInfo.getDbInfo(circlePath);
		                	//担保环长度同时也是担保还上节点数
		                	int length=circleInfo.getLength();
		                	//担保还上所有的关系
		                	List<Relationship> relationships=circleInfo.getRelationships();
		                	
		                	Map<String, List<Map<String, Object>>> result=new HashMap<String, List<Map<String, Object>>>();
		                	
		                	result=td.toD3FormatByrel(relationships);
		                	
		                	updateRDBMS.update_qm(circleInfo);
		                	updateRDBMS.updateRdb(result,skips[length],length,startNode.getProperty("zcdz").toString()+"圈");
		                	skips[length]++;
		              
		                	if((circlePath.length()+1)>maxCircleLength) {
		                		maxCircleLength=circlePath.length()+1;
		                	}
		                	
		                	if(top_tenOflength.size()<10) {
		                		top_tenOflength.add(circleInfo);
		                	}
		                	
		                	if(top_tenOftotalDBJE.size()<10) {
		                		top_tenOftotalDBJE.add(circleInfo);
		                	}
		                	
		                	Collections.sort(top_tenOflength, comparatorBylength);
		                	Collections.sort(top_tenOftotalDBJE,comparatorByTotalDBJE);
		                	
		                	if(circleInfo.getLength()>top_tenOflength.get(0).getLength()) {
		                		top_tenOflength.set(0,circleInfo);
		                	}
		                	if(circleInfo.getTotalDBJE()>top_tenOftotalDBJE.get(0).getTotalDBJE()) {
		                		top_tenOftotalDBJE.set(0,circleInfo);
		                	}
		                    records++;
		                }
					}
					
				updateRDBMS.updateTop10Circle(top_tenOflength, top_tenOftotalDBJE);
			}
				
				
				
		}
			
		

}
