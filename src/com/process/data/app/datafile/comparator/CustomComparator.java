package com.process.data.app.datafile.comparator;

import java.util.Comparator;

public class CustomComparator implements Comparator<String[]> {
	@Override
	public int compare(String[] row1, String[] row2) {
		if (row1[0].charAt(0) < row2[0].charAt(0)) {
			return -1;
		}
		else if (row1[0].charAt(0) > row2[0].charAt(0)) {
			return 1;
		}
		else {
			if (Integer.parseInt(row1[0].substring(1)) < Integer.parseInt(row2[0].substring(1))) {
				return -1;
			}
			else if (Integer.parseInt(row1[0].substring(1)) > Integer.parseInt(row2[0].substring(1))) {
				return 1;
			}
		}
		return 0;
	}
}