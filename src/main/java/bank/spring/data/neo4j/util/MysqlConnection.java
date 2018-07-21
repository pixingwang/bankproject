package bank.spring.data.neo4j.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnection {
	
	//这里是SqlConnection 类  

//		private static String Url="jdbc:mysql://localhost:3306/neo4j";//数据库连接字符串，这里的deom为数据库名
//		private static String Name="root";//登录名  
//		private static String Password="123456";//密码
		
//		@Value("${MysqlUrl}")
		String Url;
//		@Value("${Mysqlname}")
		String Name;
//		@Value("${Mysqlpassword}")
		String Password;
	
		public Connection GetSqlConnection() throws IOException {
			//加载驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序");
				e.printStackTrace();
			}
			
			Connection conn=null;
			try {
				Properties pps=new Properties();
				pps.load(pps.getClass().getResourceAsStream("/application.properties"));
				Url=pps.getProperty("MysqlUrl");
				Name=pps.getProperty("Mysqlname");
				Password=pps.getProperty("Mysqlpassword");
				conn=DriverManager.getConnection(Url,Name,Password);
				//System.out.println("获取数据库连接成功！");
			} catch (SQLException e) {
				System.out.println("获取数据库连接失败！");
				e.printStackTrace();
			}
			
			return conn;
		}
		

}
