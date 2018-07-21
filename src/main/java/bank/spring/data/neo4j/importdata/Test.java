package bank.spring.data.neo4j.importdata;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Test {

	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) throws IllegalStateException,
			IOException, InterruptedException {

		Batch batch = new Batch();
		long starttime = System.currentTimeMillis();

//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_grjcxx",
//				"GR");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_dgkh",
//				"DG");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_jgxxb",
//				"YH");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_grckzh",
//				"GRCKZH");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_dgckzh",
//				"DGCKZH");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_jyls",
//				"JY");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_dkgx",
//				"DB");
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_xdht",
//				"DK");
//		
//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_dgzh_gx",
//				"DY");

//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_kzlgx",
//				"GL");

//		batch.UpdateDB("D:/neo4jdatabase-923", "D:/neo4jimport/0918/e_dkgx_hz",
//				"ZDB");
		
		
//		启动服务
//		Runtime.getRuntime().exec("cmd /c "+"neo4j start",null,new File("F:\\neo4j-community-3.3.0\\bin"));
//        try {
//			proc.waitFor(10,TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
		
		File f=new File("F:/neo4j-community-3.3.0/bin");
		String[] s= f.list();
		
		
		for(String a : s){
		System.out.println(a+a.length());
		}
		
		
		
//        Thread.sleep(10*1000);
//        proc0.destroy();
//        
//		Runtime.getRuntime().exec("cmd /c "+"F:\\neo4j-community-3.3.0\\bin\\stop.bat",null,new File("F:\\neo4j-community-3.3.0\\bin"));        
//		Thread.sleep(10*1000);
//        proc1.destroy();
        
        
        
		long end = (System.currentTimeMillis() - starttime) / 1000;
		System.out.println(new Date().toLocaleString() + " >>> 总共用时  " + end / 3600 + "时" + (end / 60) % 60 + "分"
				+ end % 60 + "秒");

	}

}