package bank.spring.data.neo4j.importdata;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.csvreader.CsvReader;

public class TestCsv {
	public static void main(String[] args) throws Exception {
		for (int i = 5; i <= 5; i++) {
			CsvReader reader = new CsvReader(
					"D:/neo4jimport/new/e_jyls/part-0000" + i, '\001',
					Charset.forName("UTF-8"));
			System.out.println(reader.getDelimiter());
			System.out.println(reader.getCaptureRawRecord());
			System.out.println(reader.getEscapeMode());
			System.out.println(reader.getSafetySwitch());
			System.out.println(reader.getColumnCount());
			int idx = 1;
			try {
				while (reader.readRecord()) {
					String[] tmp = reader.getValues();
					if (tmp.length != 30) {
						System.err.println(i + ":" + idx + " >>> " + Arrays.asList(tmp));
					}
					idx++;
				}
			} catch (Exception e) {
				System.err.println(i+":"+idx);
				e.printStackTrace();
			}finally{
				reader.close();
			}
			
			System.out.println(i+":"+idx);
		}
	}
}
