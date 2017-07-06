package com.epam.ta.library.controller.command.impl.util;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.service.util.ServiceUtil;

public class CommandUtil {

		public static Book buildBook(String bName, Integer bYear, String bDescr, Integer bQuantity) {
		Book book = null;
		if (ServiceUtil.notNullCheck(bYear, bQuantity) && ServiceUtil.notNullCheck(bName, bDescr)) {
			book = new Book();
			book.setName(bName);
			book.setYear(bYear);
			book.setDescription(bDescr);
			book.setQuantity(bQuantity);
		}
		return book;

	}
	
	public static Book buildBook(Integer bId, String bName, Integer bYear, String bDescr, Integer bQuantity) {
		Book book = null;
		if (ServiceUtil.notNullCheck(bId, bYear, bQuantity) && ServiceUtil.notNullCheck(bName, bDescr)) {
			book = new Book();
			book.setId(bId);
			book.setName(bName);
			book.setYear(bYear);
			book.setDescription(bDescr);
			book.setQuantity(bQuantity);
		}
		return book;

	}
	
	public static String[] splitParams(String paramStr) {

		String[] params = null;
		if (null != paramStr && !paramStr.isEmpty()) {
			String delimiter = "'+";
			params = paramStr.replaceAll("^['\\s]", "").replaceAll("'\\s+'", "''").split(delimiter);
		}

		return params;
	}
}
