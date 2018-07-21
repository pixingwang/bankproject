import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.io.FileOutputStream;

import java.io.OutputStream;

import org.neo4j.cypher.internal.frontend.v2_3.perty.recipe.Pretty.listAppender;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.springframework.data.mapping.model.CamelCaseSplittingFieldNamingStrategy;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import bank.spring.data.neo4j.domain.Relationship;
import bank.spring.data.neo4j.util.UtilService;



public class Csvimport implements AutoCloseable {
	public final Driver driver;
	UtilService us=new UtilService();
	public Csvimport(String uri, String user, String password) {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		driver.close();
	}

	public void createIndex() {
		try (Session session = driver.session()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("create index on:P(no)");
				tx.run("create index on:C(no)");
				tx.success(); // Mark this write as successful.
				tx.close();
			}
		}

	}
	
	public static String getRel_type(String rel_type) {

		String type = "";
		switch (rel_type) {
		case "DB":
			type = "DB";
			break;
		case "JY":
			type = "JY";
			break;
		case "GD":
		case "FR":
		case "KZRGL":
		case "JS":
		case "CW":
		case "ZJL":
		case "DS":
			type = "KZ";
			break;
		case "GL":
		case "QT":
			type = "QT";
			break;
		default:
			break;
		}

		return type;
	}
	public void importByCSV2(String path) throws IOException {
		StringBuilder cypher=new StringBuilder();
		int sum=0;
		
		CsvReader readersize=new CsvReader(path, ',',Charset.forName("UTF-8"));
		readersize.readHeaders();
		long size=0;
		while (readersize.readRecord()) {
			size++;
		}
		System.out.println(size);
		
		CsvReader reader=new CsvReader(path, ',',Charset.forName("UTF-8"));
		reader.readHeaders();
		
		try (Session session = driver.session()) {
				Map<String, Object> params;
				while(sum<size) {
				try (Transaction tx = session.beginTransaction()) {
				System.out.println(sum);
				while (reader.readRecord()) {
					String [] record=reader.getValues();
						params=map(record);
						if(record[6].equals("P")) {
							if(record[10].equals("P")) {
								cypher.append("merge(FROM:P{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:P{no:{to_no}})set TO.code= {to_code},TO.name= {to_name}");
							}else {
								cypher.append("merge(FROM:P{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:C{no:{to_no}})set TO.code= {to_code},TO.name= {to_name}");
							}
						}else{
							if(record.equals("P")) {
								cypher.append("merge(FROM:C{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:P{no:{to_no}})set TO.code= {to_code},TO.name= {to_name}");
							}else {
								cypher.append("merge(FROM:C{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:C{no:{to_no}})set TO.code= {to_code},TO.name= {to_name}");
							}
						}
						
						
						switch(getRel_type(record[1])) {
						
						case "DB":
							cypher.append(" CREATE (FROM)-[R:DB{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "JY":
							cypher.append(" CREATE (FROM)-[R:JY{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "KZ":
							cypher.append(" CREATE (FROM)-[R:KZ{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "QT":
							cypher.append(" CREATE (FROM)-[R:QT{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						
						}
						tx.run(cypher.toString(),params);
						cypher.delete(0,cypher.length());
						tx.success(); // Mark this write as successful.
						sum++;
						params.clear();
						if(sum%5000==0) {
							tx.close();
							break;
						}
					}
				}
		}
		System.out.println(sum);
	}	
}
	
	public void importByCSV(String path) throws IOException {
		StringBuilder cypher=new StringBuilder();
		int sum=0;
		ArrayList<String[]> csvList=new ArrayList<String[]>();
		CsvReader reader=new CsvReader(path, ',',Charset.forName("UTF-8"));
		reader.readHeaders();
		while(reader.readRecord()){// 逐行读入出表头的数据  
			csvList.add(reader.getValues());
		}
		
		try (Session session = driver.session()) {
			Map<String, Object> params;
			int left = 0;
			int right = 5000;
			while (left < csvList.size()) {
				if (right >= csvList.size()) {
					right = csvList.size();
				}
				try (Transaction tx = session.beginTransaction()) {
					for (int i = left; i < right; i++) {
						
/*					cypher="merge(FROM:"+csvList.get(i)[6]+"{no:'"+csvList.get(i)[3]+"'})set FROM.code='"+csvList.get(i)[4]+"',FROM.name='"+csvList.get(i)[5]+"'"
								+ "merge(TO:"+csvList.get(i)[10]+"{no:'"+csvList.get(i)[7]+"'})set TO.code='"+csvList.get(i)[8]+"',FROM.name='"+csvList.get(i)[9]+"'"
										+ "CREATE (FROM)-[R:"+getRel_type(csvList.get(i)[1])+"{rel_type_cn:'"+csvList.get(i)[2]+"',data_date:'"+csvList.get(i)[0]+"',rela_from:'"+csvList.get(i)[12]+"',proportion:'"+csvList.get(i)[11]+"',money:"+(Double.parseDouble((String)csvList.get(i)[13]))+"}]->(TO) ";
*/					
						params=map(csvList.get(i));
						//System.out.println(csvList.get(i)[6].getClass());
						if(csvList.get(i)[6].equals("P")) {
							if(csvList.get(i)[10].equals("P")) {
								cypher.append("merge(FROM:P{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:P{no:{to_no}})set FROM.code= {to_code},FROM.name= {to_name}");
							}else {
								cypher.append("merge(FROM:P{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:C{no:{to_no}})set FROM.code= {to_code},FROM.name= {to_name}");
							}
						}else{
							if(csvList.get(i).equals("P")) {
								cypher.append("merge(FROM:C{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:P{no:{to_no}})set FROM.code= {to_code},FROM.name= {to_name}");
							}else {
								cypher.append("merge(FROM:C{no:{from_no}})set FROM.code= {from_code},FROM.name= {from_name}"
										+ " merge(TO:C{no:{to_no}})set FROM.code= {to_code},FROM.name= {to_name}");
							}
						}
						switch(getRel_type(csvList.get(i)[1])) {
						
						case "DB":
							cypher.append(" CREATE (FROM)-[R:DB{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "JY":
							cypher.append(" CREATE (FROM)-[R:JY{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "KZ":
							cypher.append(" CREATE (FROM)-[R:KZ{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						case "QT":
							cypher.append(" CREATE (FROM)-[R:QT{rel_type_cn:{type_cn},data_date:{data_date},rela_from:{rela_from},proportion:{proportion},money:{money}}]->(TO)");
							break;
						
						}
						//System.out.println(cypher);
						tx.run(cypher.toString(),params);
						cypher.delete(0,cypher.length());
						tx.success(); // Mark this write as successful.
						sum++;
						params.clear();
					}
					tx.close();
					System.out.println(sum);
				}
				left += 5000;
				right += 5000;
			}
			System.out.println(left + "--" + right);
		}
	}
	
	public void importByLoadCSV() {
		try (Session session = driver.session()) {
			//try (Transaction tx = session.beginTransaction()) {
				session.run("CREATE CONSTRAINT ON (p:P) ASSERT p.no IS UNIQUE");
				session.run("CREATE CONSTRAINT ON (p:C) ASSERT p.no IS UNIQUE");
				System.out.println(1);
				session.run( "LOAD CSV WITH HEADERS FROM \"file:///node.csv\" AS csvLine " + 
						" foreach(n in case when csvLine.TYPE='P'then [1] else [] end | merge (p:P{no:csvLine.NO}) set p.code=csvLine.CODE,p.name=csvLine.NAME) " + 
						" foreach(n in case when csvLine.TYPE='C'then [1] else [] end | merge (p:C{no:csvLine.NO}) set p.code=csvLine.CODE,p.name=csvLine.NAME)");
				System.out.println(2);
				session.run( " USING PERIODIC COMMIT 2000 "+
						" LOAD CSV WITH HEADERS FROM \"file:///rel.csv\" AS csvLine " + 
						" MATCH (from{no:csvLine.FROM_CUST_NO}),(to{no:csvLine.TO_CUST_NO}) " + 
						" foreach(n in case when csvLine.REL_TYPE='DB'then [1] else [] end | " + 
						" create (from)-[r:DB{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(to)) " + 
						" foreach(n in case when csvLine.REL_TYPE='JY'then [1] else [] end | " + 
						" create (from)-[r:JY{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(to))  " + 
						" foreach(n in case when csvLine.REL_TYPE in ['FR','GD','CW','DS','JS','ZJL','KZRGL'] then [1] else [] end | " + 
						" create (from)-[r:KZ{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(to)) " + 
						" foreach(n in case when csvLine.REL_TYPE in ['GL','QT']then [1] else [] end | " + 
						" create (from)-[r:QT{rela_from:csvLine.RELA_FROM,proportion:csvLine.PROPORTION,money:toFloat(csvLine.MONEY),data_date:csvLine.DATA_DATE,rel_type_cn:csvLine.REL_TYPE_CN}]->(to))");
				/*tx.success(); // Mark this write as successful.
				tx.close();
			}*/
				session.close();
		}
		
	}
	
	
	
	public Map<String, Object> map(String no, String code,String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("no", no);
		result.put("code", code);
		result.put("name",name);
		//result.put("type", type);
 		return result;
	}
	public Map<String, Object> map(String data_date,String type_cn,String proportion,String rela_from,String money) {
		Map<String, Object> result = new HashMap<String, Object>(6);
		result.put("data_date", data_date);
		//result.put("type", getRel_type(type));
		result.put("type_cn",type_cn);
		result.put("proportion", proportion);
		result.put("rela_from", rela_from);
		result.put("money", money);
 		return result;
	}
	public Map<String, Object> map(String []r) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("from_no", r[3]);
		result.put("from_code", r[4]);
		result.put("from_name",r[5]);
		//result.put("from_type", r[6]);
		result.put("to_no", r[7]);
		result.put("to_code", r[8]);
		result.put("to_name",r[9]);
		//result.put("to_type", r[10]);
		result.put("data_date", r[0]);
		//result.put("type", getRel_type(r[1]));
		result.put("type_cn",r[2]);
		result.put("proportion",r[11]);
		result.put("rela_from",r[12]);
		result.put("money",(Double.parseDouble((String)r[13])));
		//(Double.parseDouble((String)csvList.get(i)[13]))
 		return result;
	}
	public int getIndex(Map<String, Object> node,List<Map<String, Object>> nodes) {
		int index=-1;
		int length=nodes.size();
		for(int i=0;i<length;i++) {
			if(node.get("no").equals(nodes.get(i).get("no"))) {
				index=i;
				break;
			}
		}
		return index;
	}
	public List<Map<String, Object>> toMapByCSV(String path) throws IOException{
		CsvReader reader=new CsvReader(path, ',',Charset.forName("UTF-8"));
		reader.readHeaders();
		Map<String,Object> record=new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();
		while(reader.readRecord()){
			String a[]=reader.getValues();
			record=map(a);
			list.add(record);
		}
		
		
		return list;
	}
	
	
	
	/*public Map<String,List<Map<String, Object>>> toMapByCSV(String path) throws Exception{
		//ArrayList<String[]> csvList=new ArrayList<String[]>();
		CsvReader reader=new CsvReader(path, ',',Charset.forName("UTF-8"));
		reader.readHeaders();
		while(reader.readRecord()){// 逐行读入出表头的数据  
			csvList.add(reader.getValues());
		}
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();	
		
		Map<String,Object> startNode,endNode;
		while(reader.readRecord()){
			String a[]=reader.getValues();
			startNode=map(a[3], a[4], a[5], a[6]);
			int index=getIndex(startNode, nodes);
			if(index==-1) {
				nodes.add(startNode);
			}else {
				nodes.remove(index);
				nodes.add(startNode);
			}
			endNode=map(a[7],a[8], a[9],a[10]);
			index=getIndex(endNode, nodes);
			if(index==-1) {
				nodes.add(endNode);
			}else {
				nodes.remove(index);
				nodes.add(endNode);
			}
		Map<String,Object> rel=map(a[0],a[1], a[2], a[11],a[12],a[13]);
		rels.add(rel);
		}
		
		return us.map("nodes", nodes, "rels", rels);
	}*/
/*	public void writer(String path, String fileName,org.neo4j.driver.v1.Record result,String fileType,int t,int j,String id) throws IOException {  
        Workbook wb = null; 
        String excelPath = path+File.separator+fileName+"."+fileType;
        File file = new File(excelPath);
        Sheet sheet =null;
        //创建工作文档对象   
        if (!file.exists()) {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();
                
            } else if(fileType.equals("xlsx")) {
                wb = new XSSFWorkbook();
            } else {
                throw new IOException("文件格式不正确");
            }
            //创建sheet对象   
            sheet = (Sheet) wb.createSheet("sheet1");  
            OutputStream outputStream = new FileOutputStream(excelPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
            
        } else {
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                
            } else if(fileType.equals("xlsx")) { 
            	FileInputStream fileinputStream=new FileInputStream(excelPath);
     			wb=new XSSFWorkbook(fileinputStream);  
                sheet=wb.getSheet("sheet1");
            } else {  
                throw new IOException("文件格式不正确");
            }  
        }
        
        
        
        //添加表头  
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        Cell cell1 = row.createCell(1);
        row.setHeight((short) 540); 
        String info=t+"节点的担保环的第"+j+"条记录";
        //创建第一行   
        cell.setCellValue("担保圈测试"+id);     
        cell1.setCellValue(info);
        Row row_t;
        if(sheet.getRow(t)!=null) {
        	 row_t=sheet.getRow(t);
        	 Cell cell_j=row_t.createCell(j);
             cell_j.setCellValue(result.toString());
        }else{
        	 row_t =sheet.createRow(t);
        	 Cell cell_j=row_t.createCell(j);
             cell_j.setCellValue(result.toString());
        }
        
        
        //创建文件流   
        OutputStream stream = new FileOutputStream(excelPath);  
        //写入数据   
        wb.write(stream);  
        //关闭文件流   
        stream.close();  
    } */
	public  String createCypherforDBCircle(int n) { 
		String []node=new String[n+1];
		String []ids=new String[n+1];
		for(int i=1;i<=n;i++) {
			String name="n";
			name=name+i;
			node[i]=name;
		}
		for(int i=1;i<n;i++) {
			String id="id(n";

			id=id+i+")";
			
			ids[i]=id;
		}
		
 		StringBuilder cypher=new StringBuilder("match circle=(n0:N)");
		for(int i=1;i<=n;i++) {
			cypher.append("-[:DB]->("+node[i]+":N)");
		}
		int length=node.length;
		cypher.append(" where n0="+node[length-1]);
 		for(int i=0;i<n-1;i++) {
			String name="n";
			name=name+i;
			for(int j=i+1;j<n;j++) {
				cypher.append(" and "+name+"<>"+node[j]);
			}
		}
 		cypher.append(" and n0.creditcode={creditcode} return id(n0)");
 		
 		for(int i=1;i<n;i++) {
 			cypher.append(","+ids[i]);
 		}
 		
		return cypher.toString();
	}
 	
	public  String[] createCypher() {
		String[] cypher=new String[101];
		for(int i=1;i<=100;i++) {
			cypher[i]=createCypherforDBCircle(i);
		}
		return cypher;
	}
	
	
/*
	public  void test() throws IOException {
		
		String path = "D:\\testDB";  
        String[] cypherSting=createCypher();
        Session session=driver.session();
		//StatementResult r=session.run(cypher);
		long i=0;
		
		for (int file = 0; file < 2; file++) {
			String filepath,creditcode;
			if(file==1) {
				filepath="D:/neo4jimport/0918/e_dgkh/part-";
				for(int k = 0; k < 10; k++) {
					CsvReader reader = new CsvReader(filepath+ String.format("%05d", k), '\001', Charset.forName("UTF-8"));
					while (reader.readRecord()) {
						String[] tmp = reader.getValues();
						creditcode=tmp[1];
						System.out.println(i+"--"+creditcode);
			 			i++;//节点数
			 			String fileName="test"+i;
			 			
						Map<String,Object> map=new HashMap<>();
						map.put("creditcode",creditcode);
						
						CsvWriter csvWriter=new CsvWriter(path+"\\test.csv", ',',Charset.forName("UTF-8"));
						//System.out.println(i+"--"+st_node.toString());
						//System.out.println(i+"--"+creditcode);
						long start = System.currentTimeMillis();
						int flag=1;
						for(int t=1;t<=30;t++) {
							//System.out.println(t);
							String cy=cypherSting[t];
							StatementResult rs=session.run(cy,map);
							if(flag==1&&rs.hasNext()) {
								csvWriter=new CsvWriter(path+"\\"+fileName+".csv", ',',Charset.forName("UTF-8"));
								String [] id= {creditcode};
								csvWriter.writeRecord(id);
								flag=0;
							}
							while(rs.hasNext()) {
								
								org.neo4j.driver.v1.Record st=rs.next();
								String [] re= {st.toString()};
								csvWriter.writeRecord(re);
							}
						}
						String [] time= {(System.currentTimeMillis() - start)+ "毫秒"};
						csvWriter.writeRecord(time);
						csvWriter.close();
					}
				}
			}else {
				filepath="D:/neo4jimport/0918/e_grjcxx/part-";
				for(int k = 0; k < 15; k++) {
					CsvReader reader = new CsvReader(filepath+ String.format("%05d", k), '\001', Charset.forName("UTF-8"));
					while (reader.readRecord()) {
						String[] tmp = reader.getValues();
						creditcode=tmp[1];
						//creditcode="420625a201661f0632c52ef3746f2f0f66de30";
						System.out.println(i+"--"+creditcode);
			 			i++;//节点数
			 			String fileName="test"+i;
			 			
						Map<String,Object> map=new HashMap<>();
						map.put("creditcode",creditcode);
						
						CsvWriter csvWriter=new CsvWriter(path+"\\test.csv", ',',Charset.forName("UTF-8"));
						//System.out.println(i+"--"+st_node.toString());
						//System.out.println(i+"--"+creditcode);
						long start = System.currentTimeMillis();
						int flag=1;
						for(int t=1;t<=30;t++) {
							//System.out.println(t);
							String cy=cypherSting[t];
							StatementResult rs=session.run(cy,map);
							if(flag==1&&rs.hasNext()) {
								csvWriter=new CsvWriter(path+"\\"+fileName+".csv", ',',Charset.forName("UTF-8"));
								String [] id= {creditcode};
								csvWriter.writeRecord(id);
								flag=0;
							}
							while(rs.hasNext()) {
								
								org.neo4j.driver.v1.Record st=rs.next();
								String [] re= {st.toString()};
								csvWriter.writeRecord(re);
							}
						}
						String [] time= {(System.currentTimeMillis() - start)+ "毫秒"};
						csvWriter.writeRecord(time);
						csvWriter.close();
					}
				}
			}
		
		}
	}*/
	
	
	public  void test() throws IOException {
		
		String path = "D:\\testDB";  
        String[] cypherSting=createCypher();
        Session session=driver.session();
		//StatementResult r=session.run(cypher);
		long i=0;
		
			String filepath,creditcode;
	
						//creditcode="420625a201661f0632c52ef3746f2f0f66de30";
						creditcode="4202029e977d2fe6d68d511e88b6a37454e403";
						System.out.println(i+"--"+creditcode);
			 			i++;//节点数
			 			String fileName="test"+i;
			 			
						Map<String,Object> map=new HashMap<>();
						map.put("creditcode",creditcode);
						
						CsvWriter csvWriter=new CsvWriter(path+"\\test.csv", ',',Charset.forName("UTF-8"));
						//System.out.println(i+"--"+st_node.toString());
						//System.out.println(i+"--"+creditcode);
						long start = System.currentTimeMillis();
						int flag=1;
						for(int t=1;t<=30;t++) {
							//System.out.println(t);
							String cy=cypherSting[t];
							StatementResult rs=session.run(cy,map);
							if(flag==1&&rs.hasNext()) {
								csvWriter=new CsvWriter(path+"\\"+fileName+".csv", ',',Charset.forName("UTF-8"));
								String [] id= {creditcode};
								csvWriter.writeRecord(id);
								flag=0;
							}
							while(rs.hasNext()) {
								
								org.neo4j.driver.v1.Record st=rs.next();
								String [] re= {st.toString()};
								csvWriter.writeRecord(re);
							}
						}
						String [] time= {(System.currentTimeMillis() - start)+ "毫秒"};
						csvWriter.writeRecord(time);
						csvWriter.close();
					}
}
