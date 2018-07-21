package bank.spring.data.neo4j.domain;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="N")
public class Node extends Entity {
	
	@Property(name="creditcode")
	public String creditcode;    //证件号码
	@Property(name="flag")
	public String flag;

	public String getCreditcode() {
		return creditcode;
	}

	public String getFlag() {
		return flag;
	}

	
	public String getLabel() {
		return null;
	}
	
	
}
