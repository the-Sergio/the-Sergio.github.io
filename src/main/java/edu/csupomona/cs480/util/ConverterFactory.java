package edu.csupomona.cs480.util;

// Assignment 9
// Roberto Rodriguez
public class ConverterFactory {
	public Converter getConverter(String converterType) {
		if (converterType == null) {
			return null;
		}
		if (converterType == "JSON") {
			return new CsvJsonConverter();
		}
		else if (converterType == "XML") {
			return new CsvXmlConverter();
		}
		else {
			return null;
		}
	}
}
