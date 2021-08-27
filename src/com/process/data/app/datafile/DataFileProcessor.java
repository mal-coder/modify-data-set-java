package com.process.data.app.datafile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.process.data.app.datafile.comparator.CustomComparator;

public class DataFileProcessor {
	private List<String[]> knownIDMisspellings = new ArrayList<>();
	private DateTimeFormatter defaultDateFormat;
	private Path filePath;

	private List<String[]> columns = new ArrayList<>();
	private List<String[]> data = new ArrayList<>();


	public List<String[]> getColumns() {
		return columns;
	}

	public List<String[]> getData() {
		return data;
	}

	public DataFileProcessor(Path filePath, DateTimeFormatter defaultDateFormat, List<String[]> knownIDMisspellings) {
		this.filePath = filePath;
		this.defaultDateFormat = defaultDateFormat;
		this.knownIDMisspellings = knownIDMisspellings;

		this.formatData();
	}

	public void formatData() {
		List<String> lines = readFile();

		for(int i = 0; i<=lines.size()-1; i++) {
			String[] line = lines.get(i).split("\\t");
			if (i == 0) {
				columns.add(line);
			} else {
				line[0] = fixIdColumn(line[0]);
				line[5] = fixCreatedDate(line[5]);
				line[6] = fixModifiedDate(line[6]);

				data.add(line);
			}
		}
		Collections.sort(data, new CustomComparator());
	}

	private List<String> readFile() {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
		} catch (NoSuchFileException e) {
			System.out.println("File not found in the provided path. Exiting.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("I/O Exception occured. Exiting.");
			e.printStackTrace();
			System.exit(0);
		}
		return lines;
	}

	private String fixIdColumn(String content) {
		Matcher matcher = Pattern.compile("[A-Z][\\dA-Z]+").matcher(content);
		if (matcher.find()) {
			content = matcher.group(0);
			for (String[] item : knownIDMisspellings) {
				if (item.length > 0) {
					content = content.substring(0, 1)
							+ content.substring(1, content.length()).replace(item[0], item[1]);
				}
			}
		}
		return content;
	}

	private String fixCreatedDate(String content) {
		LocalDateTime createdDate = LocalDateTime.parse(content, defaultDateFormat);
		ZonedDateTime modifiedCreatedDate = ZonedDateTime.of(createdDate, ZoneId.of("UTC"));
		return modifiedCreatedDate.format(DateTimeFormatter.ISO_INSTANT);
	}

	private String fixModifiedDate(String content) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return currentDateTime.format(defaultDateFormat);
	}
}

