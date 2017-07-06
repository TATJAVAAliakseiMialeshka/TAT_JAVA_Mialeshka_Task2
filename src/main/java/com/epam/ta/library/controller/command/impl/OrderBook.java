package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class OrderBook  implements Command{

	private final static Logger log = Logger.getLogger(OrderBook.class);

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
					responce = "Order for book successfully placed. Please, wait for your order approval.";
				} else {
					responce = "Order for book cannot be placed. Book cannot be found or not available.";
				}
			} else {
				responce = "Order book operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "Order book operation failed due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during placing order for book.";
		}

		return responce;
	}

}