package edu.csupomona.cs480.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class CsvJsonConverter {

	public static void main(String[] args) throws Exception {
		String csvName = "csv_prices1.csv";
		FileReader filereader = new FileReader(new File(csvName));
		BufferedReader csvreader = new BufferedReader(filereader);
		
		String jsonName = "food-map.json";
		PrintWriter filewriter = new PrintWriter(new File(jsonName));

		//filewriter.println("{"); // beginning of json file
		filewriter.println("[");
		
		/*
		 * For every line read in from prices.csv, use the first field (a 
		 * numerical value beginning at 1) as a key for the Food object and the
		 * remaining fields as the attribute of that Food object
		 * 
		 * e.g. in prices.csv
		 * 1,Jamba Juice,Classic Smoothie (Strawberries Wild),4.49
		 * 2,Jamba Juice,Classic Smoothie (Mango-A-Go-GO),4.49
		 * 
		 * produces in food-map.json
		 * {
		 * "1":{"id":"Classic Smoothie (Strawberries Wild)","description":"Jamba Juice","price":"4.49"},
		 * "2":{"id":"Classic Smoothie (Mango-A-Go-GO)","description":"Jamba Juice","price":"4.49"}
		 * }
		 */
		for (String s; (s = csvreader.readLine()) != null; ) {
			String[] strings = s.split(",");
			//filewriter.print("\"" + strings[0] + "\":");
			filewriter.print("{\"id\":\"" + strings[2] + "\",");
			filewriter.print("\"description\":\"" + strings[1] + "\",");
			filewriter.print("\"price\":" + strings[3] + "}");			
			
			// After line is read, peek at next char. If end of file, break.
			// Else, read next line/item.
			csvreader.mark(1);
			char peek = (char)csvreader.read();
			if (peek == (char)-1)
				break;
			else {
				csvreader.reset();
				filewriter.println(",");
			}
		}		
		
		//filewriter.print("}"); // end of json file
		filewriter.println("]");
		filewriter.close();
		csvreader.close();
	}
}
