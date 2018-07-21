import java.io.IOException;


public class aaa {

		public static void main(String[] args) throws IOException {
			/// TODO Auto-generated method stub
			//System.out.println(Runtime.getRuntime().maxMemory());
			long start = System.currentTimeMillis();
			try (Csvimport importdata = new Csvimport("bolt://127.0.0.1:7687", "neo4j", "123456")) {
				//System.out.println(11);
				///逐条读取的方式
				//importdata.createIndex();
				//importdata.importByCSV("C:\\Users\\王瑶\\Desktop\\C2C.csv");
				//importdata.importByCSV2("C:\\Users\\王瑶\\Desktop\\C2C.csv");
				//importdata.importByCSV2("C:\\Users\\王瑶\\Desktop\\target.csv");
				importdata.test();
				//System.out.println(importdata.createCypherforDBCircle(3));
				//importdata.createCypher();
				///数据分离，LoadCSV的方式
				/*importdata.importByLoadCSV();
				importdata.close();
				
				///UNWIND的方式
				Map<String,Object> list=importdata.toMapByCSV("C:\\Users\\王瑶\\Desktop\\target.csv");
				importdata.importByUNWIND(list);*/
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println((System.currentTimeMillis() - start) / 1000 + "秒");
	
	}

}
