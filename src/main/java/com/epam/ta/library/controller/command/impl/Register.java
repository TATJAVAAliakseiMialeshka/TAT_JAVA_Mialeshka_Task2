package com.epam.ta.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.impl.util.CommUserParamValidator;
import com.epam.ta.library.controller.command.impl.util.CommandUtil;
import com.epam.ta.library.service.LoginService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.factory.ServiceFactory;

public class Register implements Command {

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
						responce = "You have successfully registered. Login to continue...";
					} else {
						responce = "Registration operation failed. User with such login already exists.";
					}
				} else {
					responce = "Registration operation failed due to wrong argumets format.";
				}
			} else {
				responce = "Registration operation failed due to wrong argumets format.";
			}
		} catch (ServiceException e) {
			log.error(e);
			responce = "Error during register procedure.";
		}
		return responce;

	}

}