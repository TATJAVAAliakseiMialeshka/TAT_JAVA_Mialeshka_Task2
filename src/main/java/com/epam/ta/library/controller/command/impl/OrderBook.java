package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public final class OrderBook  implements Command{

	private static final String ORDER_BOOK_SUCCESS = "Order for book successfully placed. Please, wait for your order approval.";
	private static final String ORDER_BOOK_FAILED_WRONG_FORMAT = "Order book operation failed due to wrong arguments format.";
	private static final String ORDER_BOOK_FAILED_NO_SUCH_BOOK = "Order for book cannot be placed. Book cannot be found or not available.";
	private static final String ORDER_BOOK_FAILED = "Error during placing order for book.";
	
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
					responce = ORDER_BOOK_SUCCESS;
				} else {
					responce = ORDER_BOOK_FAILED_NO_SUCH_BOOK;
				}
			} else {
				responce = ORDER_BOOK_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = ORDER_BOOK_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = ORDER_BOOK_FAILED;
		}

		return responce;
	}

}