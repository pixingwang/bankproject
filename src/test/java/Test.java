

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;





public class Test {
	
/*
	public static void main(String[] args) throws IOException {
		/// TODO Auto-generated method stub
		//System.out.println(Runtime.getRuntime().maxMemory());
		long start = System.currentTimeMillis();
		try (Csvimport importdata = new Csvimport("bolt://127.0.0.1:7687", "neo4j", "123456")) {
			System.out.println(11);
			///逐条读取的方式
			importdata.createIndex();
			//importdata.importByCSV("C:\\Users\\王瑶\\Desktop\\C2C.csv");
			//importdata.importByCSV2("C:\\Users\\王瑶\\Desktop\\C2C.csv");
			importdata.importByCSV2("C:\\Users\\王瑶\\Desktop\\target.csv");
			//importdata.test();
			//System.out.println(importdata.createCypherforDBCircle(3));
			//importdata.createCypher();
			///数据分离，LoadCSV的方式
			importdata.importByLoadCSV();
			importdata.close();
			
			///UNWIND的方式
			Map<String,Object> list=importdata.toMapByCSV("C:\\Users\\王瑶\\Desktop\\target.csv");
			importdata.importByUNWIND(list);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - start) / 1000 + "秒");
*/

	public  void test() {
		System.out.println(11);
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("E:\\Neo4jGraphDatabase\\Import_new - 副本"));
		List<Node> nodes=new ArrayList<Node>();
		try(Transaction tx=graphDb.beginTx()){
			
			Result result=graphDb.execute("match (n) where id(n)=393 set n.flag='危险' return n");
			System.out.println(result.resultAsString());
		/*	Node node=graphDb.getNodeById(393);
			node.setProperty("flag","危险");
			
			System.out.println(graphDb.execute("match (n) where id(n)=393 return n").resultAsString());*/
			
			
			/*ResourceIterator<Path> path = result.columnAs("p");
			Iterator<Node> nIterator = path.next().nodes().iterator();
			while(nIterator.hasNext()) {
				Node node=nIterator.next();
				nodes.add(node);
				
			}
			System.out.println(nodes);
			
			Result result2=graphDb.execute("match p=(n:C)-[:DB]-() where id(n)=389 return p");
			ResourceIterator<Path> path2 = result.columnAs("p");
			Iterator<Node> nIterator2 = path.next().nodes().iterator();
			while(nIterator2.hasNext()) {
				Node node=nIterator2.next();
				nodes.add(node);
				
			}
			System.out.println(nodes);
			Set<Node> nodesSet=new HashSet<Node>();
			nodesSet.addAll(nodes);
			System.out.println(nodesSet);*/
			tx.success();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*UtilService us=new UtilService();
		System.out.println(us.createCypherforJYChain(101, 3, "1000", "20000", "124512", "20154521"));*/
		Test t=new Test();
		t.test();
	
	}

	

}
