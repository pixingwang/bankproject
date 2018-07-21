package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

 /**
  * 总担保关系  
  * @author dell
  *
  */
@RelationshipEntity(type = "ZDB")     
public class ZDB extends Relationship{
	@StartNode
	protected Node StartNode;
	@EndNode
	protected Node EndNode;
	
	public static String label="ZDB";

	public String getLabel() {
		return label;
	}
	
	@Property(name="zdbje")
	public String zdbje;     //总担保金额
	
	@Property(name="zdbcs")
	protected String zdbcs;   //总担保次数
	
	
	public String getZdbje() {
		return zdbje;
	}
	public void setZdbje(String zdbje) {
		this.zdbje = zdbje;
	}
	public String getZdbcs() {
		return zdbcs;
	}
	public void setZdbcs(String zdbcs) {
		this.zdbcs = zdbcs;
	}
	public void setStartNode(Node startNode) {
		StartNode = startNode;
	}
	public void setEndNode(Node endNode) {
		EndNode = endNode;
	}
	public static void setLabel(String label) {
		ZDB.label = label;
	}
	public Node getStartNode() {
		return StartNode;
	}
	public Node getEndNode() {
		return EndNode;
	}
	
	
	public ZDB(){
		
	}
	
}
