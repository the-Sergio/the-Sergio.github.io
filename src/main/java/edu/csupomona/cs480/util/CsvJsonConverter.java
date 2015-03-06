package edu.csupomona.cs480.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class CsvJsonConverter implements Converter {
	
	public void convert(String filename) throws Exception {
		FileReader filereader = new FileReader(new File(filename));
		BufferedReader csvreader = new BufferedReader(filereader);
		
		String jsonName1 = "food-map-HASHMAP_VERSION.json";
		String jsonName2 = "food-map.json";
		PrintWriter filewriter1 = new PrintWriter(new File(jsonName1));
		PrintWriter filewriter2 = new PrintWriter(new File(jsonName2));

		filewriter1.println("{"); // beginning of json file
		filewriter2.println("{");
		
		/*
		 * For every line read in from prices.csv, use the first field (a 
		 * numerical value beginning at 1) as a key for the Food object and the
		 * remaining fields as the attribute of that Food object
		 * 
		 * e.g. in prices.csv
		 * 1,Jamba Juice,Classic Smoothie (Strawberries Wild),4.49
		 * 2,Jamba Juice,Classic Smoothie (Mango-A-Go-GO),4.49
		 * 
		 */
		for (String s; (s = csvreader.readLine()) != null; ) {
			String[] strings = s.split(",");
			filewriter1.println("  \"" + strings[0] + "\": {");
			filewriter1.println("    \"id\": \"" + strings[2] + "\",");
			filewriter1.println("    \"description\": \"" + strings[1] + "\",");
            filewriter1.println("    \"price\": " + strings[3]);
            
            filewriter2.println("  \"" + strings[0] + "\": {");
			filewriter2.println("    \"id\": \"" + strings[2] + "\",");
			filewriter2.println("    \"description\": \"" + strings[1] + "\",");
            filewriter2.println("    \"price\": \"" + strings[3] + "\"");

			// After line is read, peek at next char. If end of file, break.
			// Else, read next line/item.
			csvreader.mark(1);
			char peek = (char)csvreader.read();
			if (peek == (char)-1) {
				filewriter1.println("  }");
				filewriter2.println("  }");
				break;
			}
			else {
				csvreader.reset();
				filewriter1.println("  },");
				filewriter2.println("  },");
			}
		}		
		
		filewriter1.print("}"); // end of json file
		filewriter1.close();
		filewriter2.print("}"); // end of json file
		filewriter2.close();
		csvreader.close();
	}

}
