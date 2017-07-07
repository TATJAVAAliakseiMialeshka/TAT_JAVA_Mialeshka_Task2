package com.epam.ta.library.controller.command.impl;

import com.epam.ta.library.controller.command.Command;

public class WrongRequest implements Command{

	private static final String WRONG_REQUEST = "Wrong request.";
	
	@Override
	public String execute(String paramStr) {
		return WRONG_REQUEST;
	}
}
