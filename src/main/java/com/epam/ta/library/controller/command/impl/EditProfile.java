package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommUserParamValidator;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class EditProfile implements Command {

	private static final String USER_PROF_UPD_SUCCESS = "User profile successfully updated.";
	private static final String USER_PROF_UPD_FAILED_WRONG_FORMAT = "User profile update operation failed due to wrong arguments format.";
	private static final String USER_PROF_UPD_FAILED_WRONG_CREDENT = "User profile update operation failed. Wrong credentials.";
	private static final String USER_PROF_UPD_FAILED = "Error during updating user profile.";

	private final static Logger log = Logger.getLogger(EditProfile.class);

	@Override
	public String execute(String paramStr) {
		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			if (null != paramArr && paramArr.length == 4) {
				Integer userId = Integer.parseInt(paramArr[0]);
				String userName = paramArr[1];
				String userPassword = paramArr[2];
				String userOldPassword = paramArr[3];
				if (CommUserParamValidator.validateLogin(userName)
						&& CommUserParamValidator.validatePassword(userPassword)
						&& CommUserParamValidator.validatePassword(userOldPassword)) {
					if (userService.updateUserProfile(userId, userName, userPassword, userOldPassword)) {
						responce = USER_PROF_UPD_SUCCESS;
					} else {
						responce = USER_PROF_UPD_FAILED_WRONG_CREDENT;
					}
				} else {
					responce = USER_PROF_UPD_FAILED_WRONG_FORMAT;
				}
			} else {
				responce = USER_PROF_UPD_FAILED_WRONG_FORMAT;
			}

		} catch (NumberFormatException e) {
			log.error(e);
			responce = USER_PROF_UPD_FAILED_WRONG_FORMAT;
		} catch (ServiceException e) {
			log.error(e);
			responce = USER_PROF_UPD_FAILED;
		}

		return responce;
	}

}