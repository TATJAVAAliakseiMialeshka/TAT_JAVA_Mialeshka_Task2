package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommBookParamValidator;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class AddBookDescription implements Command {

	private final static Logger log = Logger.getLogger(AddBookDescription.class);

	@Override
	public String execute(String paramStr) {

		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 4) {
				String bName = paramArr[0];
				Integer bYear = Integer.parseInt(paramArr[1]);
				String bDescr = paramArr[2];
				Integer bQuantity = Integer.parseInt(paramArr[3]);
				if (CommBookParamValidator.validateBookName(bName) && CommBookParamValidator.validateBookName(bDescr)
						&& CommBookParamValidator.validateBookYear(bYear) && bQuantity > -1) {
					Book book = CommandUtil.buildBook(bName, bYear, bDescr, bQuantity);

					if (adminService.addBook(book)) {
						responce = "Book description successfully added.";
					}
				} else {
					responce = "Book description operation failed due to wrong arguments format.";
				}
			} else {
				responce = "Book description operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "Book description operation failed due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during adding book description.";
		}
		return responce;
	}

}
