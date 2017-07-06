package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class ActivateUser implements Command {

	private final static Logger log = Logger.getLogger(ActivateUser.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer userId = Integer.parseInt(paramArr[0]);

				if (userId > 0 && adminService.activateUser(userId)) {
					responce = "User successfully activated.";
				} else {
					responce = "User activation operation failed. Wrong crdentials.";

				}
			} else {
				responce = "User activation operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "User activation operation failed. Wrong crdentials.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "User activation operation failed.";
		}

		return responce;
	}

}