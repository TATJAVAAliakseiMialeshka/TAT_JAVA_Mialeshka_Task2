package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommUserParamValidator;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.LoginService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class Register implements Command {

	private static final String REGISTRATION_SUCCESS = "You have successfully registered. Login to continue...";
	private static final String REGISTRATION_FAILED_WRONG_FORMAT = "Registration operation failed due to wrong argumets format.";
	private static final String REGISTRATION_FAILED_USER_EXISTS = "Registration operation failed. User with such login already exists.";
	private static final String REGISTRATION_FAILED = "Error during register procedure.";
	
	private final static Logger log = Logger.getLogger(Register.class);

	@Override
	public String execute(String paramStr) {

		String responce = null;
		String[] paramArr = CommandUtil.splitParams(paramStr);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LoginService loginService = serviceFactory.getLoginService();

		try {
			if (null != paramArr && paramArr.length == 2) {
				String name = paramArr[0];
				String password = paramArr[1];
				if (CommUserParamValidator.validateLogin(name) && CommUserParamValidator.validatePassword(password)) {
					if (loginService.registerUser(name, password)) {
						responce = REGISTRATION_SUCCESS;
					} else {
						responce = REGISTRATION_FAILED_USER_EXISTS;
					}
				} else {
					responce = REGISTRATION_FAILED_WRONG_FORMAT;
				}
			} else {
				responce = REGISTRATION_FAILED_WRONG_FORMAT;
			}
		} catch (ServiceException e) {
			log.error(e);
			responce = REGISTRATION_FAILED;
		}
		return responce;

	}

}