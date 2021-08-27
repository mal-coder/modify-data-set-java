package com.process.data.app.datafile;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataFilePathResolver {
	static private String defaultPath = "./resources/example.txt";

	static public Path resolveFilePath(Scanner scanner) {
		Path fileToRead;

		System.out.print("""
				Enter your dataset's file path (e.g. "./resources/example.txt").
				To use default hit "Return".
				-> """);
		String filePath = scanner.nextLine();
		if (filePath.isBlank()) {
			filePath = defaultPath;
			System.out.println("Using default file path.");
		}
		try {
			fileToRead = Paths.get(filePath);
		}
		catch (InvalidPathException e) {
			System.out.println("Invalid file path format. Using default.");
			fileToRead = Paths.get(defaultPath);
		}

		System.out.println("File path chosen: " + fileToRead);

		return fileToRead;
	}

}
