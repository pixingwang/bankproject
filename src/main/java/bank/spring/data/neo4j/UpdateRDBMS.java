package bank.spring.data.neo4j;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import bank.embedded.data.neo4j.circleoftraversal.FindAllNodeDbCircleBybothwayOfstream;
import bank.embedded.data.neo4j.cypher.Dkffzyg;
import bank.embedded.data.neo4j.cypher.Dkhlzdbr;
import bank.embedded.data.neo4j.cypher.Dkhlzfrdb;
import bank.embedded.data.neo4j.cypher.Dkyjhx;
import bank.embedded.data.neo4j.cypher.Dkyyycbz;
import bank.embedded.data.neo4j.cypher.Dkzrfdc;
import bank.embedded.data.neo4j.cypher.Dkzrrzdb;
import bank.embedded.data.neo4j.cypher.Xfdkzrfdc;
import bank.embedded.data.neo4j.pattern.SearchPatternData;
@Service
public class UpdateRDBMS {
	@Value("${Neo4jDatabasePath}")
	String Neo4jDatabasePath;

	private static GraphDatabaseService graphDb;

	public void setUp() throws IOException

	{


		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(Neo4jDatabasePath));

		registerShutdownHook();

	}
	
	public void update() {
		
		try {
			FindAllNodeDbCircleBybothwayOfstream fdc=new FindAllNodeDbCircleBybothwayOfstream(graphDb);
			SearchPatternData spd=new SearchPatternData(graphDb);
			
			//执行担保环数据查询更新
			System.out.println("开始更新担保环数据");
			fdc.printAllNodeDbCircle();
			System.out.println("担保环数据更新完毕");
			
			
			System.out.println("开始更新其他模式数据");
			
			spd.deleteTableData();
			System.out.println("数据库以清空");
			
			System.out.println("更新贷款发放至员工");
			Dkffzyg dkffzyg=new Dkffzyg();
			System.out.println(spd.searchPattern(dkffzyg));
			
			System.out.println("更新贷款回流至担保人");
			Dkhlzdbr dkhlzdbr=new Dkhlzdbr();
			System.out.println(spd.searchPattern(dkhlzdbr));
			
			System.out.println("更新贷款回流至法人代表");
			Dkhlzfrdb dkhlzfrdb=new Dkhlzfrdb();
			System.out.println(spd.searchPattern(dkhlzfrdb));
			
			System.out.println("更新贷款以旧换新");
			Dkyjhx dkyjhx=new Dkyjhx();
			System.out.println(spd.searchPattern(dkyjhx));
			
			System.out.println("更新贷款用于银承保证金");
			Dkyyycbz dkyyycbz=new Dkyyycbz();
			System.out.println(spd.searchPattern(dkyyycbz));
			
			System.out.println("更新贷款转入房地产");
			Dkzrfdc dkzrfdc =new Dkzrfdc();
			System.out.println(spd.searchPattern(dkzrfdc));
			
			System.out.println("更新贷款转入融资担保");
			Dkzrrzdb dkzrrzdb=new Dkzrrzdb();
			System.out.println(spd.searchPattern(dkzrrzdb));
			
			System.out.println("更新消费贷转入房地产");
			Xfdkzrfdc xfdkzrfdc=new Xfdkzrfdc();
			System.out.println(spd.searchPattern(xfdkzrfdc));
			
			
			System.out.println("其他模式数据更新完毕");

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

	public void shutdown()

	{

		graphDb.shutdown();

	}

	private void registerShutdownHook()

	{

		Runtime.getRuntime()

				.addShutdownHook(new Thread()

		{

					@Override

					public void run()

			{

						graphDb.shutdown();

					}

				});

	}

}
