package com.neo4j.GRAMI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

@SuppressWarnings("unchecked")
public class getDataInformation {
	//数据库地址
	private static final File DB_PATH = new File( "E:\\neo4j2" );
	//写入文件地址
	private static final File FILE_PATH = new File( "d:\\movie2.txt" );
	
	private static BufferedWriter bw;
	
	private static List<Object> list = new LinkedList<Object>();
	
	private static GraphDatabaseService graphDb;
	
	@SuppressWarnings("rawtypes")
	private static HashSet h;
	
	private static Map<Integer, String> map = new HashMap<Integer,String>();
	
	static int count;
	
	public enum LabelTypes implements Label {
		Person,Movie;
	}
	
	public enum RelationshipTypes implements RelationshipType{
		DIRECTED,ACTED_IN;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		
		try (Transaction tx = graphDb.beginTx()) {
			// Nodes
			Iterator<Node> nodes1 = graphDb.findNodes(LabelTypes.Person);
			Iterator<Node> nodes2 = graphDb.findNodes(LabelTypes.Movie);
			
			// Relationships
			ResourceIterable<Relationship>  rels =  graphDb.getAllRelationships();
			
			bw = new BufferedWriter(new FileWriter(FILE_PATH));
			//文件开始标志信息写入文件
			bw.write("# t 1");
			bw.newLine();
			
			//node节点信息写入文件
			writeNodeFile(nodes1,"Person");
			writeNodeFile(nodes2,"Movie");
			
			//找出满足条件的relationship
			findCorrectRelationships(rels);
			//将relationship信息写入文件
			writeRelationshipsFile(h);
			
            bw.close();
			tx.success();
		}
		// Stop the database
        graphDb.shutdown();
	}
	
	
	public static void writeNodeFile(Iterator<Node> nodes,String label) throws IOException {
		while(nodes.hasNext()) {
			System.out.println(0);
        	Node startNode = nodes.next();
        	long l_id = startNode.getId();
        	
        	//写入Node节点信息
        	String NodeId = String.valueOf(count);
        	map.put((int)l_id, NodeId);
        	if(label.equals("Person")) {
				label = "1";
			}
			if(label.equals("Movie")) {
				label = "2";
			}
        	bw.write("v"+" "+NodeId+" "+label);
        	bw.newLine();
        	count++;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void findCorrectRelationships(ResourceIterable<Relationship> rel) {
		h = new HashSet();
		Iterator it= rel.iterator();
		while(it.hasNext()) {
			Relationship relationship = (Relationship) it.next();
			RelationshipType type = relationship.getType();
			String label = String.valueOf(type);
			if(label.equals("DIRECTED")||label.equals("ACTED_IN")) {
				h.add(relationship);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void writeRelationshipsFile(HashSet h) throws IOException {
		Iterator it= h.iterator();
		while(it.hasNext()) {
			Relationship relationship = (Relationship) it.next();
			RelationshipType type = relationship.getType();
			//获取关系两端点id
			long id_1 = relationship.getStartNode().getId();
			long id_2 = relationship.getEndNode().getId();
			
			//判断关系是否已经存在
			if(list.contains(id_1)&&list.contains(id_2)) return;
			else{
				list.add(id_1);
				list.add(id_2);
			}
			
			String id1 = map.get((int)id_1);
			String id2 = map.get((int)id_2);
			String label = String.valueOf(type);
			
			if(label.equals("DIRECTED")) {
				label = "3";
			}
			if(label.equals("ACTED_IN")) {
				label = "4";
			}
			bw.append("e"+" "+id1+" "+id2+" "+label);
			bw.newLine();
		}
	}
}
