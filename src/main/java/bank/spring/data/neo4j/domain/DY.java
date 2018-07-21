package bank.spring.data.neo4j.domain;



import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * 对应账户关系
 * @author dell
 *
 */
@RelationshipEntity(type = "DY")
public class DY extends Relationship{
	@StartNode
	protected Node StartNode;
	@EndNode
	protected Acount EndNode;
	
	public static String label="DY";

	public String getLabel() {
		return label;
	}
	
	public Node getStartNode() {
		return StartNode;
	}
	public Acount getEndNode() {
		return EndNode;
	}
	public DY(){
		
	}
	
}
