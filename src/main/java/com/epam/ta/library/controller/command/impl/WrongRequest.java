package com.epam.ta.library.controller.command.impl;

import com.epam.ta.library.controller.command.Command;

public class WrongRequest implements Command{

	@Override
	public String execute(String paramStr) {
		return "Wrong request.";
	}
}
