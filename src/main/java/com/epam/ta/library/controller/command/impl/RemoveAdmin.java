package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.SuperadminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class RemoveAdmin  implements Command{

	private static final String REMOVE_ADMIN_SUCCESS = "Admin role successfully disabled for selected user.";
	private static final String REMOVE_ADMIN_FAILED_WRONG_FORMAT = "Admin role disable operation failed due to wrong arguments format.";
	private static final String REMOVE_ADMIN_FAILED_NO_SUCH_USER = "Admin role disable operation failed. Probably there is no such user.";
	private static final String REMOVE_ADMIN_FAILED = "Error during disabled admin role for selected user.";
	
	private final static Logger log = Logger.getLogger(RemoveAdmin.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SuperadminService superadminService = serviceFactory.getSuperadminService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer userId = Integer.parseInt(paramArr[0]);
				if (superadminService.deleteAdminRole(userId)) {
					responce = REMOVE_ADMIN_SUCCESS;
				} else {
					responce = REMOVE_ADMIN_FAILED_NO_SUCH_USER;
				}
			} else {
				responce = REMOVE_ADMIN_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = REMOVE_ADMIN_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = REMOVE_ADMIN_FAILED;
		}

		return responce;
	}
}