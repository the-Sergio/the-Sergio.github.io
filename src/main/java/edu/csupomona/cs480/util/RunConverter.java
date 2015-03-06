package edu.csupomona.cs480.util;

public class RunConverter {
	public static void main(String[] args) throws Exception {
		ConverterFactory factory = new ConverterFactory();
		Converter converter = (factory.getConverter("JSON"));
		converter.convert("prices.csv");
	}
}
