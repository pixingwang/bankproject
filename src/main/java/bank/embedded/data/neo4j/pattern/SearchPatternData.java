package bank.embedded.data.neo4j.pattern;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;




import bank.embedded.data.neo4j.cypher.Cypher;

import bank.spring.data.neo4j.util.MysqlConnection;
import net.sf.json.JSONObject;



/**
 * 1.执行Cypher语句，从neo4j中获得查询结果
 * 2.给查询到的关系添加sum,no,nodes（节点数）,links（关系数）属性
 * 3.向mysql中插入对应模式的数据
 * @author dell
 *
 */
public class SearchPatternData {
		
		public  MysqlConnection mysqlConn= new MysqlConnection();
		public static int limit=1;
		public  GraphDatabaseService graphDb;
		ToD3FormatFromGraphDb td=new ToD3FormatFromGraphDb();
		
	    public static enum LabelTypes implements Label

	    {

	        P,C,N

	    }
	   
	    public static enum RelTypes implements RelationshipType

	    {

	        DB,ZDB

	    }
	    
	    public  SearchPatternData(GraphDatabaseService graphDb) {
	    	this.graphDb=graphDb;
	    }

	
	    
	 	public int searchPattern(Cypher cy) throws IOException {
	 	int skip = 0;
		List<Relationship> relationships = new ArrayList<>();
		try (Transaction tx = graphDb.beginTx()) {
			String cypher = "";
			
			for (int i = 1; i <= 3; i++) {
				switch (i) {
				case 1:
					cypher = cy.getCypher1();
					break;
				case 2:
					cypher = cy.getCypher2();
					break;
				case 3:
					cypher = cy.getCypher3();
					break;
				}
				System.out.println(cypher);
				Result result = graphDb.execute(cypher);
				while (result.hasNext()) {
					ResourceIterator<Path> path = result.columnAs("P");
					Path p=path.next();
					Iterator<Relationship> rels = p.relationships().iterator();
					while (rels.hasNext()) {
						Relationship r = rels.next();
						relationships.add(r);
					}
					
					//给相关节点添加标记
					Iterator<Node> nodes=p.nodes().iterator();
					while (nodes.hasNext()) {
						Node n=nodes.next();
						
						
						if(n.hasLabel(LabelTypes.N)) {
							n.setProperty("flag", "危险");
						}
						
					}
					
					Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
					map = td.toD3FormatByrel(relationships);
					updateRDBMS(skip, map, cy.getPatternName());
					skip++;
					relationships.clear();
					
				
				}
				
			}
			tx.success();
		}
		return skip;
	 }
	 	/**
	 	 * 删除mysql中八个模式的数据
	 	 * @throws IOException
	 	 */
	 	public void deleteTableData() throws IOException {
			Connection conn=mysqlConn.GetSqlConnection();
			String tabelName="";
			try {
				for(int i=1;i<=8;i++) {
					switch(i) {
					case 1:tabelName="dkffzyg";break;
					case 2:tabelName="dkhlzdbr";break;
					case 3:tabelName="dkhlzfrdb";break;
					case 4:tabelName="dkyjhx";break;
					case 5:tabelName="dkzrfdc";break;
					case 6:tabelName="dkzrrzdb";break;
					case 7:tabelName="xfdjrfdc";break;
					case 8:tabelName="dkyyycbz";break;
					}
					String sql="truncate table "+tabelName;
					Statement st=conn.createStatement();
					st.execute(sql);
					System.out.println("清空"+tabelName+"表成功！");
				}
					
					conn.close();		
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	 	/**
	 	 * 向mysql中插入八个模式对应的数据
	 	 * @param skip
	 	 * @param records
	 	 * @param tablename
	 	 * @throws IOException
	 	 */
		private void updateRDBMS(int skip,Map<String, List<Map<String, Object>>> records,String tablename) throws IOException {
			Connection conn=mysqlConn.GetSqlConnection();
			String sql="INSERT INTO "+tablename+" VALUES (?,?)";
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				JSONObject json=JSONObject.fromObject(records);
				pst.setInt(1,skip);
				pst.setString(2,json.toString());
				pst.executeUpdate();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
           
		}

		
}
