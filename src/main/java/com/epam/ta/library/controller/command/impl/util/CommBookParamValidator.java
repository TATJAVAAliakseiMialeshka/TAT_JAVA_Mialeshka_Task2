package com.epam.ta.library.controller.command.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommBookParamValidator {

	private final static String BOOK_NAME_DESCR_PATTERN = "[A-z0-9\\s\\.]+";
	private final static String BOOK_YEAR_PATTERN = "[0-9]{3,4}";

	public static boolean validateBookName(String bookName) {
		Matcher bookNameMatcher = Pattern.compile(BOOK_NAME_DESCR_PATTERN).matcher(bookName);
		if (bookNameMatcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean validateBookYear(Integer bYear) {
		Matcher bookNameMatcher = Pattern.compile(BOOK_YEAR_PATTERN).matcher(String.valueOf(bYear));
		if (bookNameMatcher.matches()) {
			return true;
		}
		return false;
	}

}
