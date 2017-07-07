package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class GrantAdmin implements Command {

	private static final String GRANT_ADMIN_SUCCESS = "Admin role successfully granted for selected user.";
	private static final String GRANT_ADMIN_FAILED_WRONG_FORMAT = "Add admin role operation failed due to wrong arguments format.";
	private static final String GRANT_ADMIN_FAILED_NO_SUCH_USER = "Add admin role operation failed. Probably there is no such user.";
	private static final String GRANT_ADMIN_FAILED = "Error during providing admin role operation for selected user.";

	private final static Logger log = Logger.getLogger(GrantAdmin.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer userId = Integer.parseInt(paramArr[0]);
				if (adminService.grantAdminRole(userId)) {
					responce = GRANT_ADMIN_SUCCESS;
				} else {
					responce = GRANT_ADMIN_FAILED_NO_SUCH_USER;

				}
			} else {
				responce = GRANT_ADMIN_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = GRANT_ADMIN_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = GRANT_ADMIN_FAILED;
		}

		return responce;
	}

}