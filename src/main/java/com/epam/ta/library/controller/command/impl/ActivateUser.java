package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public final class ActivateUser implements Command {

	private static final String ACTIVATION_SUCCESS = "User successfully activated.";
	private static final String ACT_FAILED_WRONG_CREDENTIALS = "User activation operation failed. Wrong crdentials.";
	private static final String ACT_FAILED = "User activation operation failed.";
	private static final String ACT_FAILED_WRONG_FORMAT = "User activation operation failed due to wrong arguments format.";

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
					responce = ACTIVATION_SUCCESS;
				} else {
					responce = ACT_FAILED_WRONG_CREDENTIALS;

				}
			} else {
				responce = ACT_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = ACT_FAILED_WRONG_CREDENTIALS;
		} catch (ServiceException e) {
			log.error(e);
			responce = ACT_FAILED;
		}

		return responce;
	}

}