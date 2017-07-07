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

public final class SeeAllBooks implements Command {

	private static final String LOAD_BOOKS_SUCCESS = "Library books list successfully loaded.";
	private static final String LOAD_BOOKS_FAILED_ = "Error during loading book list operation.";
	
	private final static Logger log = Logger.getLogger(SeeAllBooks.class);


	@Override
	public String execute(String paramStr) {
		String responce = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		SessionStorage session = SessionStorage.getInstance();
		try {
			List<Book> bookList = userService.seeAllBooks();
			if (null != bookList) {
				responce = LOAD_BOOKS_SUCCESS;
				session.setLibraryBooks(bookList);
				CommandPrintUtil.printBookList(bookList);
			} 
			
		
		} catch (ServiceException e) {
			log.error(e);
			responce = LOAD_BOOKS_FAILED_;
		}

		return responce;
	}

}