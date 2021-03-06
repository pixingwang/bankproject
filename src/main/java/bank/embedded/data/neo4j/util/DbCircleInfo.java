package bank.embedded.data.neo4j.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;


public class DbCircleInfo {
	
	private int length;
	private double totalDBJE;
	private List<Relationship> relationships=new ArrayList<>();
	private List<Node> nodes=new ArrayList<>();
	String path;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getTotalDBJE() {
		return totalDBJE;
	}
	public void setTotalDBJE(double totalDBJE) {
		this.totalDBJE = totalDBJE;
	}
	public List<Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	
	 @Override
	    public String toString() {
	        return "DbCircle [length=" + length + ", totalDBJE=" + totalDBJE +"]"+"\n"+path;
	    }

	
	 
	 public DbCircleInfo getDbInfo(Node startNode,Relationship firstrel,Path circlePath,int flag) {
			DbCircleInfo dbInfo=new DbCircleInfo();
			List<Relationship> relationships=new ArrayList<>();
			List<Node> nodes=new ArrayList<>();
			int length=0;
			double totalDBJE=0;
			String path;
			
			//System.out.println(startNode+"--"+firstrel+"--"+circlePath);
			
			nodes.add(startNode);
			Iterator<Node> nodesI=circlePath.nodes().iterator();
			while(nodesI.hasNext()) {
				Node node=nodesI.next();
				nodes.add(node);
			}
		
			relationships.add(firstrel);
			totalDBJE+=Double.parseDouble((String)firstrel.getProperty("zdbje"));
			Iterator<Relationship> relationshipsI=circlePath.relationships().iterator();
			while(relationshipsI.hasNext()) {
				Relationship relationship=relationshipsI.next();
				totalDBJE+=Double.parseDouble((String)relationship.getProperty("zdbje"));
				relationships.add(relationship);
			}
			
			
			length=circlePath.length()+1;
			
			if(flag==0) {
				path="At depth " + (circlePath.length()+1) + " => "
		                +"("+startNode.getId()+")"+"--[DB,"+firstrel.getId()+"]-->"+circlePath.toString()+"\n";
			}else {
				path="At depth " + (circlePath.length()+1) + " => "
		                +"("+startNode.getId()+")"+"<--[DB,"+firstrel.getId()+"]--"+circlePath.toString()+"\n";
			}
			
			
			dbInfo.setLength(length);
			dbInfo.setTotalDBJE(totalDBJE);
			dbInfo.setRelationships(relationships);
			dbInfo.setNodes(nodes);
			dbInfo.setPath(path);
			
			return dbInfo;
			
		}
	 
	 public DbCircleInfo getDbInfo(Path circlePath) {
			DbCircleInfo dbInfo=new DbCircleInfo();
			List<Relationship> relationships=new ArrayList<>();
			List<Node> nodes=new ArrayList<>();
			int length=0;
			double totalDBJE=0;
			String path;
			
			Iterator<Node> nodesI=circlePath.nodes().iterator();
			while(nodesI.hasNext()) {
				Node node=nodesI.next();
				nodes.add(node);
			}
			nodes.remove(nodes.size()-1);

			
			Iterator<Relationship> relationshipsI=circlePath.relationships().iterator();
			while(relationshipsI.hasNext()) {
				Relationship relationship=relationshipsI.next();
				totalDBJE+=Double.parseDouble((String)relationship.getProperty("zdbje"));
				relationships.add(relationship);
			}
			
			
			length=circlePath.length();
			
			
				path="At depth " + (circlePath.length()) + " => "
		                +circlePath.toString()+"\n";
			
			
			dbInfo.setLength(length);
			dbInfo.setTotalDBJE(totalDBJE);
			dbInfo.setRelationships(relationships);
			dbInfo.setNodes(nodes);
			dbInfo.setPath(path);
			
			return dbInfo;
			
		}
}
