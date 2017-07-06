package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class RefuseBook  implements Command{

	private final static Logger log = Logger.getLogger(RefuseBook.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			if (null != paramArr && paramArr.length == 2) {
				Integer userId = Integer.parseInt(paramArr[0]);
				Integer bookId = Integer.parseInt(paramArr[1]);
				if (userService.orderBook(userId, bookId)) {
					responce = "Order for book successfully canceled.";
				} else {
					responce = "Cancel book order operation failed. Order is not exists or book is already on your hands.";
				}
			} else {
				responce = "Cancel book order operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "Cancel book order operation failed due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during cancel order for book.";
		}

		return responce;
	}

}