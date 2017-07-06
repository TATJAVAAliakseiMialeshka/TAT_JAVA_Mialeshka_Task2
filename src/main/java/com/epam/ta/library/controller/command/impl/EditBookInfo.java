package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommBookParamValidator;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class EditBookInfo implements Command {

	private final static Logger log = Logger.getLogger(EditBookInfo.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 5) {
				Integer bId = Integer.parseInt(paramArr[0]);
				String bName = paramArr[1];
				Integer bYear = Integer.parseInt(paramArr[2]);
				String bDescr = paramArr[3];
				Integer bQuantity = Integer.parseInt(paramArr[4]);

				if (CommBookParamValidator.validateBookName(bName) && CommBookParamValidator.validateBookName(bDescr)
						&& CommBookParamValidator.validateBookYear(bYear) && bQuantity > -1 && bId > 0) {
					Book book = CommandUtil.buildBook(bId, bName, bYear, bDescr, bQuantity);

					if (adminService.updateBook(book)) {
						responce = "Book description successfully updated.";
					} else {
						responce = "Book description update operation failed. Maybe there is no such book.";
					}
				} else {
					responce = "Book description update operation failed due to wrong arguments format.";
				}
			} else {
				responce = "Book description update operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "Book description update operation failed due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during update book description operation.";
		}
		return responce;
	}

}
