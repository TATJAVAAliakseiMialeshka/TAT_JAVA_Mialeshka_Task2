package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class BanUser implements Command {

	private final static Logger log = Logger.getLogger(BanUser.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer userId = Integer.parseInt(paramArr[0]);
				if (userId > 0 && adminService.ban(userId)) {
					responce = "User successfully banned.";
				} else {
					responce = "User ban was not succeed. Probably there is no such user.";
				}
			} else {
				responce = "User ban operation failed due to wrong arguments format.";
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = "User ban operation failed  due to wrong arguments format.";
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during user bann operation.";
		}

		return responce;
	}

}