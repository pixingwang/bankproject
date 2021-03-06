package bank.embedded.data.neo4j.centrality;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.csvreader.CsvWriter;

import bank.embedded.data.neo4j.util.MapUtil;
import bank.embedded.data.neo4j.util.Taversal;
import bank.embedded.data.neo4j.util.ToD3FormatFromGraphDb;
import bank.embedded.data.neo4j.util.UpdateRdbms;





/**
 * 度量中心性
 * @author dell
 *
 */
public class FindAllNodeAboutDegreeCentrality {
		private static final File DB_PATH = new File( "D:\\\\neo4jdatabase-923 -test" );
		//存储担保环记录路径
		public static String path="D:\\";
		public static GraphDatabaseService graphDb;
		
		ListComparatorByDegree listComparatorByDegree=new ListComparatorByDegree();
		ListComparatorByJE listComparatorByJE=new ListComparatorByJE();
		
		
		
		MapUtil mapUtil=new MapUtil();
		Taversal traversal=new Taversal();
		ToD3FormatFromGraphDb td=new ToD3FormatFromGraphDb();
		UpdateRdbms updateRdbms=new UpdateRdbms();
		
	   
	    
	    public static enum LabelTypes implements Label

	    {

	        P,C,N

	    }
	   
	    public static enum RelTypes implements RelationshipType

	    {

	        DB,ZDB

	    }
	    @SuppressWarnings("static-access")
		public FindAllNodeAboutDegreeCentrality(GraphDatabaseService graphDb) {
	    	this.graphDb=graphDb;
	    }
	    
	    public static void main(String[] args) {
	    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	    	FindAllNodeAboutDegreeCentrality db = new FindAllNodeAboutDegreeCentrality(graphDb);
		    try {
				
				db.printAllNodeDegreeCentrality();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	 	@SuppressWarnings("unused")
		public void printAllNodeDegreeCentrality() throws IOException {
	 		
	 		List<Map<String,Object>> result=new ArrayList<>();
	 		
	 		int depth=3;
	 		
	 		Set<Long> degree1=new HashSet<>();
	 		Set<Long> degree2=new HashSet<>();
	 		Set<Long> degree3=new HashSet<>();
	 		
	 		Traverse traverse=new Traverse(graphDb);
	 		
			try(Transaction tx=graphDb.beginTx()){
				
				//遍历对公节点
				Iterator<Node> nodeIterator=graphDb.findNodes(LabelTypes.C);
				
				int i=0;
				
				while(nodeIterator.hasNext()) {
					degree1.clear();
					degree2.clear();
					degree3.clear();
				
					i++;
					Node startNode=nodeIterator.next();
					
					Stream<Path> spStream=traverse.search(startNode, RelTypes.ZDB, Direction.OUTGOING, depth);
					//排除单个记录
					if(traverse.getNodeIteratrosize()<2)continue;
		            Iterator<Path> paths=spStream.iterator();
		            int maxLength=traverse.getmaxlength();
		            
		            Iterator<Relationship> relationships=traverse.getRelationships();
		            int nodesize=traverse.getNodeIteratrosize();
		            
		            Map<String,Object> map=new HashMap<>();
		            
		            double totalDBJE=0;
		            int totalDBCS=0;
		            while (relationships.hasNext()) {
		            	Relationship r=relationships.next();
						totalDBJE+=Double.parseDouble((String)r.getProperty("zdbje"));
						totalDBCS+=Integer.parseInt((String)r.getProperty("zdbcs"));
		            	//totalDBJE+=(Double)r.getProperty("money");
					}
		            map.put("节点id",startNode.getId());
		            map.put("ZZJGDM", startNode.getProperty("creditcode"));
		            map.put("相关节点数目", nodesize-1);
		            map.put("总担保金额", totalDBJE);
		            map.put("总担保次数",totalDBCS);
		            map.put("最长路径长度",maxLength);
		            
		            map.put("第一层节点数", traverse.getdepth1());
		            map.put("第二层节点数", traverse.getdepth2());
		            map.put("第三层节点数", traverse.getdepth3());

		            
					//System.out.println(i+"---"+map);
		            System.out.println(i);
					
		            result.add(map);
		            
				
				}
				
			}
			//对结果按度节点数目排序
			Collections.sort(result,listComparatorByJE);
			
			
			try(Transaction t=graphDb.beginTx()){
			//将前50的结果保存到关系数据库用于前端展示
			for(int i=0;i<result.size();i++) {
				Node startNode=graphDb.getNodeById((Long) result.get(i).get("节点id"));
				Stream<Path> spStream=traverse.search(startNode, RelTypes.ZDB, Direction.OUTGOING, depth);
		        Iterator<Path> paths=spStream.iterator();

		        Iterator<Relationship> pathRelationship=traverse.getRelationships();
		        Set<Relationship> relationships=new HashSet<Relationship>();
		        
		        while(pathRelationship.hasNext()){
            		Relationship pRelationship=pathRelationship.next();
            		relationships.add(pRelationship);
		        }
		        Map<String, List<Map<String, Object>>> records=new HashMap<String, List<Map<String,Object>>>();
				records=td.toD3FormatByrel(relationships);
				updateRdbms.updateDegreeCentrality(records,(String)startNode.getProperty("khmc"),startNode.getId());
		        
				relationships.clear();
			}
			
			
			
			//写入前100的信息
			CsvWriter csvWriter=new CsvWriter(path+"NodeCentrality.csv", ',',Charset.forName("UTF-8"));
			for(int i=0;i<=1000;i++) {
				Node mNode=graphDb.getNodeById((Long)result.get(i).get("节点id"));
				result.get(i).put("节点名称",mNode.getProperty("khmc"));
				String [] rStrings= {result.get(i).toString()};
				csvWriter.writeRecord(rStrings);
			}
			csvWriter.close();
			
			}
			
			for(int i=0;i<=10;i++) {
				System.out.println(result.get(i));
			}
		
			
		}
			
		

}
