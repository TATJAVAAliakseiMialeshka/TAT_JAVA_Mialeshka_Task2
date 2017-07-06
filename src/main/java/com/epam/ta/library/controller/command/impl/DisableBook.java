package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class DisableBook implements Command {

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
					responce = "Book successfully disabled for ordering.";
				} else {
					responce = "Book disable operation failed. Probably there is no such book.";
				}
			} else {
				responce = "Book disable operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "Book disable operation failed due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during disable book for ordering.";
		}

		return responce;
	}

}
