package com.epam.ta.library.controller.command.impl.util;

import java.util.List;

import com.epam.ta.library.bean.Book;

public class CommandPrintUtil {

	public static void printBookList(List<Book> books) {
		for (Book book : books) {
			System.out.println("======Library books:=======");
			System.out.print("title: " + book.getName() + "; year: " + book.getYear());
			if (null != book.getAuthorList()) {
				System.out.print("; authors: ");
				for (String author : book.getAuthorList()) {
					System.out.print(author + ", ");
				}
			}
			System.out.println("; is available: " + book.getIsAvailable());
		}
	}
}
