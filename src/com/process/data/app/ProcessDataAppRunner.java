package com.process.data.app;

import com.process.data.app.datafile.DataFilePathResolver;
import com.process.data.app.datafile.DataFileProcessor;
import com.process.data.app.output.OutputFormatResolver;
import com.process.data.app.output.OutputProcessor;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProcessDataAppRunner {
	final private static DateTimeFormatter defaultDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	final private static String defaultOutputFormat = "str";

	final private static List<String[]> knownIDMisspellings = List.of(new String[] { "O", "0" }, new String[] {});

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Path filePath = DataFilePathResolver.resolveFilePath(scanner);

		DataFileProcessor processedData = new DataFileProcessor(filePath, defaultDateFormat, knownIDMisspellings);

		String outputFormat = OutputFormatResolver.resolveOutputFormat(scanner, defaultOutputFormat);

		OutputProcessor.printOutput(outputFormat, processedData);

		scanner.close();
	}


}
