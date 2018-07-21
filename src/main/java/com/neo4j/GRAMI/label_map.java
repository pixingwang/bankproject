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

/**
 * 标签转换
 * @author dell
 *
 */
public class label_map {
	private static BufferedReader br;
	
	private static BufferedReader br1;
	
	private static BufferedWriter bw;
	
	private static BufferedWriter bw1;
	
	private static BufferedWriter bw2;
	
	private static final File INPUT_PATH = new File( "C:\\Users\\dell\\Desktop\\xwpi\\node1.txt" );
	
//	private static final File OUTPUT_PATH = new File( "C:\\Users\\dell\\Desktop\\xwpi\\information.txt" );
	
	private static final File OUTPUT_PATH1 = new File( "C:\\Users\\dell\\Desktop\\xwpi\\edge1.txt" );
	
	private static final File OUTPUT_PATH2 = new File( "C:\\Users\\dell\\Desktop\\xwpi\\vertex.txt" );
	
	private static final File MAP_PATH = new File( "C:\\Users\\dell\\Desktop\\xwpi\\map.txt" );
	
	private static Map<String, Integer> map;
	
	private static Set<String> set;
	
	public static void main(String[] args) throws IOException {
		int count = 1;
		String line;
		map = new HashMap<String, Integer>();
		set = new HashSet<String>();
		
		//读取信息文件
		br = new BufferedReader(new FileReader(INPUT_PATH));
		bw = new BufferedWriter(new FileWriter(OUTPUT_PATH1));
		bw2 = new BufferedWriter(new FileWriter(OUTPUT_PATH2));
		
		br1 = new BufferedReader(new FileReader(INPUT_PATH));
		bw1 = new BufferedWriter(new FileWriter(MAP_PATH));
		
		
		while((line = br.readLine())!=null) {
			String[] parts = line.trim().split(" ");
			String type = parts[0];
			if(type.equals("v")) {
				String label = parts[2];
				set.add(label);
			}else if(type.equals("e")) {
				String label = parts[3];
				set.add(label);
			}
			
		}
		br.close();
		
		//映射文件写入
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String label = it.next();
			map.put(label, count);
			bw1.write(label+" "+count);
			bw1.newLine();
			count++;
		}
		bw1.close();

		//文件开始标志信息写入文件
//		bw.write("# t 1");
//		bw.newLine();
		while((line = br1.readLine())!=null) {
			String[] parts = line.trim().split(" ");
			String type = parts[0];
			if(type.equals("v")) {
				String id = parts[1];
				String label = parts[2];
				int map_label = map.get(label);
				String map = String.valueOf(map_label);
				bw2.append(id+" "+map);
				bw2.newLine();
			}else if(type.equals("e")) {
				String id_1 = parts[1];
				String id_2 = parts[2];
				String label = parts[3];
				int map_label = map.get(label);
				String map = String.valueOf(map_label);
//				bw.append(id_1+" "+id_2+" "+map);
				bw.append(id_1+" "+id_2);
				bw.newLine();
			}					
		}
		br1.close();
		bw2.close();
		bw.close();
		
	}
	
}
