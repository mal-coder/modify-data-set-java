package com.process.data.app.output;

import com.process.data.app.datafile.DataFileProcessor;

public class OutputProcessor {
	static private String output = "";

	public static void printOutput(String outputFormat, DataFileProcessor processedData) {
		if ("html".equals(outputFormat)) {
			prepareHTMLOutput(processedData);
		}
		else {
			prepareSTROutput(processedData);
		}
		System.out.println(output);
	}

	private static void prepareHTMLOutput(DataFileProcessor processedData) {
		String htmlHeader = "<html>\n<body>\n<table>\n";
		String htmlFooter = "</table>\n</body>\n</html>";

		String tableColumns = "";
		for(String[] row: processedData.getColumns()){
			tableColumns += "<th>\n";
			for(String column: row) {
				tableColumns += "<td>" + column + "</td>\n";
			};
			tableColumns += "</th>\n";
		}

		String tableRows = "";
		for (String[] row : processedData.getData()) {
			tableRows += "<tr>\n";
			for (String column : row) {
				tableRows += "<td>" + column + "</td>\n";
			}
			tableRows += "</tr>\n";
		}

		output += htmlHeader + tableColumns + tableRows + htmlFooter;
	}

	private static void prepareSTROutput(DataFileProcessor processedData) {
		for (String[] row : processedData.getColumns()) {
			for (String column : row) {
				output += column + "\t";
			}
			output += "\n";
		}

		for (String[] row : processedData.getData()) {
			for (String column : row) {
				output += column + "\t";
			}
			output += "\n";
		}
	}
}

