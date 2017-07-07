package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public final class RefuseBook  implements Command{

	private static final String REFUSE_BOOK_SUCCESS = "Order for book successfully canceled.";
	private static final String REFUSE_BOOK_FAILED_WRONG_FORMAT = "Cancel book order operation failed due to wrong arguments format.";
	private static final String REFUSE_BOOK_FAILED_NO_SUCH_ORDER = "Cancel book order operation failed. Order is not exists or book is already on your hands.";
	private static final String REFUSE_BOOK_FAILED = "Error during cancel order for book.";
	
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
					responce = REFUSE_BOOK_SUCCESS;
				} else {
					responce = REFUSE_BOOK_FAILED_NO_SUCH_ORDER;
				}
			} else {
				responce = REFUSE_BOOK_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = REFUSE_BOOK_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = REFUSE_BOOK_FAILED;
		}

		return responce;
	}

}