package com.process.data.app.output;

import java.util.Scanner;

public class OutputFormatResolver {
	static public String resolveOutputFormat(Scanner scanner, String defaultOutputFormat) {
		System.out.print("""
				Choose output format (str or html).
				To use default (str) hit "Return".
				-> """);
		String outputFormat = scanner.nextLine();
		if (outputFormat.isBlank() || !"html".equals(outputFormat)) {
			outputFormat = defaultOutputFormat;
			System.out.println("Using default output format.");
		}
		System.out.println("Output format chosen: " + outputFormat);

		return outputFormat;
	}

}
