package com.epam.ta.library.controller.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandPrintUtil;
import com.epam.ta.library.controller.session.SessionStorage;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class SeeAllBooks implements Command {

	private final static Logger log = Logger.getLogger(SeeAllBooks.class);

	private List<Book> bookList;

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		if (null != bookList) {
			this.bookList = bookList;
		}
	}

	@Override
	public String execute(String paramStr) {
		String responce = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		SessionStorage session = SessionStorage.getInstance();
		try {
			bookList = userService.seeAllBooks();
			if (null != bookList) {
				responce = "Library books list successfully loaded.";
				session.setLibraryBooks(bookList);
				CommandPrintUtil.printBookList(bookList);
			} 
			
		
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during loading book list operation.";
		}

		return responce;
	}

}