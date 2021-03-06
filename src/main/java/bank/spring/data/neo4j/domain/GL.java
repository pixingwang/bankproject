package bank.spring.data.neo4j.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * 关联关系
 * @author dell
 *
 */
@RelationshipEntity(type = "GL")
public class GL extends Relationship{
	@StartNode
	protected Node StartNode;
	@EndNode
	protected Node EndNode;
	
	public static String label="GL";

	public String getLabel() {
		return label;
	}
	
	@Property(name="gxlx")
	public String gxlx;   //关系类型
	
	

	public String getGxlx() {
		return gxlx;
	}
	public void setGxlx(String gxlx) {
		this.gxlx = gxlx;
	}
	public void setStartNode(Node startNode) {
		StartNode = startNode;
	}
	public void setEndNode(Node endNode) {
		EndNode = endNode;
	}
	public static void setLabel(String label) {
		GL.label = label;
	}
	public Node getStartNode() {
		return StartNode;
	}
	public Node getEndNode() {
		return EndNode;
	}

	
}
