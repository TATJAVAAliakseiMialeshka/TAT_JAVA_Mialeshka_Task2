package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public final class SeeProfile implements Command {

	private static final String LOAD_PROFILE_SUCCESS = "User profile successfully loaded.";
	private static final String LOAD_PROFILE_FAILED_WRONG_FORMAT = "User profile loading operation failed due to wrong arguments format.";
	private static final String LOAD_PROFILE_FAILED_NO_SUCH_USER = "Error during user profile loading operation. Probably there is no such user.";
	private static final String LOAD_PROFILE_FAILED = "Error during loading user profile operation.";
	
	private final static Logger log = Logger.getLogger(SeeProfile.class);

	
	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			if (null != paramArr && paramArr.length == 1) {
				Integer userId = Integer.parseInt(paramArr[0]);

				User user = userService.seeUserProfile(userId);
				if (null != user) {
					responce = LOAD_PROFILE_SUCCESS;
				} else {
					responce = LOAD_PROFILE_FAILED_NO_SUCH_USER;
				}
			} else {
				responce = LOAD_PROFILE_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = LOAD_PROFILE_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = LOAD_PROFILE_FAILED;
		}

		return responce;
	}
}
