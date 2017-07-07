package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class DisableBook implements Command {

	private static final String DISABLE_BOOK_SUCCESS = "Book successfully disabled for ordering.";
	private static final String DISABLE_BOOK_FAILED_WRONG_FORMAT = "Book disable operation failed due to wrong arguments format.";
	private static final String DISABLE_BOOK_FAILED_NO_SUCH_BOOK = "Book disable operation failed. Probably there is no such book.";
	private static final String DISABLE_BOOK_FAILED = "Error during disable book for ordering.";
	
	private final static Logger log = Logger.getLogger(DisableBook.class);

	@Override
	public String execute(String paramStr) {

		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer bookId = Integer.parseInt(paramArr[0]);
				if (bookId > 0 && adminService.disableBook(bookId)) {
					responce = DISABLE_BOOK_SUCCESS;
				} else {
					responce = DISABLE_BOOK_FAILED_NO_SUCH_BOOK;
				}
			} else {
				responce = DISABLE_BOOK_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = DISABLE_BOOK_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = DISABLE_BOOK_FAILED;
		}

		return responce;
	}

}
