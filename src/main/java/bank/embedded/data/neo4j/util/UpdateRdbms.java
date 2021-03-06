package bank.embedded.data.neo4j.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import bank.spring.data.neo4j.util.MysqlConnection;
import net.sf.json.JSONObject;

public class UpdateRdbms {
	
	public  MysqlConnection mysqlConn= new MysqlConnection();
	ToD3FormatFromGraphDb td=new ToD3FormatFromGraphDb();
	//更新担保环数据
	public void updateRdb(Map<String, List<Map<String, Object>>> result,int skip,int length,String itemName) throws IOException{
		//System.out.println("更新担保环数据库");
		Connection conn = mysqlConn.GetSqlConnection();
		try {
		String sql = "INSERT INTO circledata VALUES (?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		JSONObject json = JSONObject.fromObject(result);
		pst.setInt(1, length);
		pst.setInt(2, skip);
		pst.setString(3, json.toString());
		pst.setString(4,itemName);
		
		pst.executeUpdate();

		pst.close();
		//System.out.println("circleData已更新完成！");
		conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//更新度中心性相关节点
	public void updateDegreeCentrality(Map<String, List<Map<String, Object>>> result,String itemName,Long startNodeId) throws IOException{
		
		Connection conn = mysqlConn.GetSqlConnection();
		try {
		String sql = "INSERT INTO degreeCentralityData VALUES (?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		JSONObject json = JSONObject.fromObject(result);
		pst.setLong(1, startNodeId);
		pst.setString(2, itemName);
		pst.setString(3, json.toString());
		
		pst.executeUpdate();
		pst.close();
		conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 清空担保环数据库
	 * @throws IOException
	 */
	public void deleteTableData() throws IOException {
		System.out.println("清空担保环数据库");
		Connection conn=mysqlConn.GetSqlConnection();
		try {
			
				Statement st=conn.createStatement();
				String sql1="truncate table qm_graph";
				
				String sql2="truncate table qm_graph_node";
				
				String sql3="truncate table qm_graph_link";
				
				String sql4="truncate table circledata";
				
				String sql5="truncate table circledataoftop";
				
				st.addBatch(sql1);
				st.addBatch(sql2);
				st.addBatch(sql3);
				st.addBatch(sql4);
				st.addBatch(sql5);
				st.executeBatch();
				System.out.println("清空表完成");
				st.close();
				conn.close();	
			}catch (SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	public void updateTop10Circle(List<DbCircleInfo> top_tenOflength,List<DbCircleInfo> top_tenOftotalDBJE) throws IOException {
		
		System.out.println("更新前十大担保环数据库");
		Connection conn = mysqlConn.GetSqlConnection();
		
		
		
		Set<Relationship> relsOflength=new HashSet<>();
		Set<Relationship> relsofdbje=new HashSet<>();
		DbCircleInfo ci;
		List<Relationship> relationships;
		Map<String, List<Map<String, Object>>> resultOflength=new HashMap<String, List<Map<String, Object>>>();
		Map<String, List<Map<String, Object>>> resultOfdbje=new HashMap<String, List<Map<String, Object>>>();
		
	
		
		Iterator<DbCircleInfo> top_tenOflengthDCinfo=top_tenOflength.iterator();
		while(top_tenOflengthDCinfo.hasNext()) {
			ci=top_tenOflengthDCinfo.next();
			relationships=ci.getRelationships();
			relsOflength.addAll(relationships);
			relationships.clear();
		}
		
		resultOflength=td.toD3FormatByrel(relsOflength);
		try {
		String sql = "INSERT INTO circledataoftop VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		JSONObject json = JSONObject.fromObject(resultOflength);
		pst.setString(1, "TopOflength");
		pst.setString(2, json.toString());
		pst.executeUpdate();
		pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		Iterator<DbCircleInfo> top_tenOftotalDBJEDCinfo=top_tenOftotalDBJE.iterator();
		while(top_tenOftotalDBJEDCinfo.hasNext()) {
			ci=top_tenOftotalDBJEDCinfo.next();
			relationships=ci.getRelationships();
			relsofdbje.addAll(relationships);
			relationships.clear();
		} 
		
		resultOfdbje=td.toD3FormatByrel(relsofdbje);
		try {
		String sql = "INSERT INTO circledataoftop VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		JSONObject json = JSONObject.fromObject(resultOfdbje);
		pst.setString(1, "TopOfdbje");
		pst.setString(2, json.toString());
		pst.executeUpdate();
		pst.close();
		conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("circleDataOfTop已更新完成！");
		
	}
	
	public  void update_qm(DbCircleInfo circleInfo) throws IOException {
		
		List<Relationship> relationships=circleInfo.getRelationships();
    	List<Node> nodes=circleInfo.getNodes();
		
		System.out.println("更新担保环数据");
		Connection conn = mysqlConn.GetSqlConnection();
		
		//更新qm_graph
    	Map<String, List<Map<String, Object>>> result=new HashMap<String, List<Map<String, Object>>>();
    	result=td.toD3FormatByrel(relationships);
    	JSONObject json = JSONObject.fromObject(result);
    	try {
    		int size=circleInfo.getLength();
    		String sql = "INSERT INTO qm_graph(KIND,NAME,SIZE,TOPOLOGY) VALUES (\"001\",\"担保圈\","+size+",'"+json.toString()+"')";
    		Statement st = conn.createStatement();
    		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);    
            ResultSet rs = st.getGeneratedKeys();    
            if (rs.next()) {    
                 int id = rs.getInt(1);    
                 //System.out.println ("生成记录的key为 ：" + id);    
                 updateNodes(nodes,relationships,id);
                 
            }  
            rs.close();	
            st.close();    
            conn.close();    
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
		
		
		
	}
	private void updateNodes(List<Node> nodes, List<Relationship> relationships, int id) throws IOException {
		Connection connection=mysqlConn.GetSqlConnection();
		
		//更新节点
		try {
			Statement st=connection.createStatement();
			List<Integer> nodeId=new ArrayList<>(); 
			int order_no_node=0;
			Iterator<Node> nodeI=nodes.iterator();
			while(nodeI.hasNext()) {
				Node node=nodeI.next();
				Map<String, Object> map=sKind(node);
				String sql="INSERT INTO qm_graph_node(GRAPH_ID,KIND,NAME,ORDER_NO,ENTITY_ID) VALUES ("+id+",'"+map.get("KIND")+"','"+map.get("NAME")+"',"+order_no_node+",'"+map.get("ENTITY_ID")+"')";
				System.out.println(sql);
				st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);    
	            ResultSet rs = st.getGeneratedKeys();
	            if(rs.next()) {
	            	int node_id=rs.getInt(1);
	            	nodeId.add(node_id);
	            }
	            order_no_node++;
	          
			}
			
			int order_no_rel=0;
			Iterator<Relationship> relationshipI=relationships.iterator();
			while(relationshipI.hasNext()) {
				Relationship rel=relationshipI.next();
				Map<String,Object> map=sKind(rel);
				int FROM_NODE_ID=nodeId.get(order_no_rel);
				int TO_NODE_ID;
				if(order_no_rel<relationships.size()-1) {
					TO_NODE_ID=nodeId.get(order_no_rel+1);
				}else {
					TO_NODE_ID=nodeId.get(0);
				}
				
				String sql="INSERT INTO qm_graph_link(GRAPH_ID,KIND,NAME,ORDER_NO,FROM_NODE_ID,TO_NODE_ID) VALUES ("+id+",'"+map.get("KIND")+"','"+map.get("NAME")+"',"+order_no_rel+","+FROM_NODE_ID+","+TO_NODE_ID+")";
				st.executeUpdate(sql);
				order_no_rel++;
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	 public static enum LabelTypes implements Label
		{

		  P,C,Com,Per,B,N,CK

		}
		public static enum RelTypes implements RelationshipType

		{

		 JY,DK,DY,DB,ZDB,GL
		}
	private Map<String,Object> sKind(Node node) {
		Map<String , Object> map=new HashMap<>();
		if(node.hasLabel(LabelTypes.P)) {
			map.put("KIND", "01");
			map.put("NAME", node.getProperty("khxm"));
			map.put("ENTITY_ID",node.getProperty("creditcode"));
			return map;
		}
		if(node.hasLabel(LabelTypes.C)) {
			map.put("KIND", "02");
			map.put("NAME", node.getProperty("khmc"));
			map.put("ENTITY_ID",node.getProperty("creditcode"));
			return map;
		}
		if(node.hasLabel(LabelTypes.Com)) {
			map.put("KIND", "03");
			map.put("NAME", node.getProperty("zhmc"));
			map.put("ENTITY_ID",node.getProperty("creditcode"));
			return map;
		}
		if(node.hasLabel(LabelTypes.Per)) {
			map.put("KIND", "04");
			map.put("NAME", node.getProperty("zhmc"));
			map.put("ENTITY_ID",node.getProperty("creditcode"));
			return map;
		}
		if(node.hasLabel(LabelTypes.B)) {
			map.put("KIND", "05");
			map.put("NAME", node.getProperty("yxjgmc"));
			map.put("ENTITY_ID",node.getProperty("yxjgdm"));
			return map;
		}
		return map;
	}
	
	private Map<String,Object> sKind(Relationship rel) {
		Map<String,Object> map=new HashMap<>();
		if(rel.isType(RelTypes.DB)) {
			map.put("KIND", "01");
			map.put("NAME", rel.getProperty("担保"));
			return map;
		}
		if(rel.isType(RelTypes.JY)) {
			map.put("KIND", "02");
			map.put("NAME", rel.getProperty("交易"));
			return map;
		}
		if(rel.isType(RelTypes.DK)) {
			map.put("KIND", "03");
			map.put("NAME", rel.getProperty("贷款"));
			return map;
		}
		if(rel.isType(RelTypes.DY)) {
			map.put("KIND", "04");
			map.put("NAME", rel.getProperty("对应"));
			return map;
		}
		if(rel.isType(RelTypes.GL)) {
			map.put("KIND", "05");
			map.put("NAME", rel.getProperty("关联"));
			return map;
		}
		if(rel.isType(RelTypes.ZDB)) {
			map.put("KIND", "06");
			map.put("NAME", rel.getProperty("总担保"));
			return map;
		}
		return map;
	}

	
}
