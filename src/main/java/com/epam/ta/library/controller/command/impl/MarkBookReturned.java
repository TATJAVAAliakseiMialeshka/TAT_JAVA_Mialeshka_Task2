package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class MarkBookReturned implements Command {

	private static final String BOOK_RETURN_SUCCESS = "User subscription for this book marked as finished. Library book fund successfully updated.";
	private static final String BOOK_RETURN_FAILED_WRONG_FORMAT = "User subscription close operation failed due to wrong arguments format.";
	private static final String BOOK_RETURN_FAILED_NO_SUCH_SUBSRIPTION = "User subscription for this book failed. Probably there is no such subscription.";
	private static final String BOOK_RETURN_FAILED = "Error during close user subscription operation.";
	
	private final static Logger log = Logger.getLogger(MarkBookReturned.class);

	@Override
	public String execute(String paramStr) {

		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 2) {
				Integer userId = Integer.parseInt(paramArr[0]);
				Integer bookId = Integer.parseInt(paramArr[1]);
				if (adminService.receiveBookBack(userId, bookId)) {
					responce = BOOK_RETURN_SUCCESS;
				} else {
					responce = BOOK_RETURN_FAILED_NO_SUCH_SUBSRIPTION;
				}
			} else {
				responce = BOOK_RETURN_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = BOOK_RETURN_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = BOOK_RETURN_FAILED;
		}

		return responce;
	}
}
