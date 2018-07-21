package com.neo4j.GRAMI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.ga.IrishAnalyzer;
import org.neo4j.register.Register.Int;

/**
 * 对元数据加工
 * @author dell
 *
 */
public class readData {
	
	private static BufferedReader br;
	
	private static BufferedReader br1;
	
	private static BufferedWriter bw;
	
	private static BufferedWriter bw1;
	
	private static Map<String, Integer> map;
	
	private static final File INPUT_PATH = new File( "C:\\Users\\dell\\Desktop\\xwpi\\instance_class_mapping.txt" );
	
	private static final File INPUT_PATH2 = new File( "C:\\Users\\dell\\Desktop\\xwpi\\instance_relationship_mapping.txt" );
	
	private static final File OUT_PATH = new File( "C:\\Users\\dell\\Desktop\\xwpi\\users.txt" );
	
	public static void main(String[] args) throws IOException {
		int count = 0;
		String line;
		map = new HashMap<String, Integer>();
		
		//读取节点信息文件
		br = new BufferedReader(new FileReader(INPUT_PATH));
		bw = new BufferedWriter(new FileWriter(OUT_PATH));
		//文件开始标志信息写入文件
//		bw.write("# t 1");
		bw.newLine();
		while((line = br.readLine())!=null) {
			String[] parts = line.trim().split(" ");
			String id = parts[0];
			String label = parts[1];
			String NodeId = String.valueOf(count);
			map.put(id, count);
			bw.write("v"+" "+NodeId+" "+label);
        	bw.newLine();
        	count++;
			
		}
		br.close();
		
		/*//读取关系信息文件
		br1 = new BufferedReader(new FileReader(INPUT_PATH2));
		while((line = br1.readLine())!=null) {
			String[] parts = line.trim().split(" ");
			String id1 = parts[0];
			String label = parts[1];
			String id2 = parts[2];
			String id_1 = String.valueOf(map.get(id1));
			String id_2 = String.valueOf(map.get(id2));
			bw.append("e"+" "+id_1+" "+id_2+" "+label);
			bw.newLine();
			
		}*/
		
		bw.close();
//		br1.close();
		
		
		
		/*br1 = new BufferedReader(new FileReader(INPUT_PATH));
		while((line = br.readLine())!=null) {
			String[] parts = line.trim().split(" ");
			String id1 = parts[0];
			String rel = parts[1];
			String id2 = parts[2];
			h1.add(id1);
			h2.add(rel);
			h1.add(id2);
			
		}
		Iterator<String> it = h1.iterator();
		while(it.hasNext()) {
			String id = it.next();
			map1.put(count, id);
			
		}*/
		
	}
}
