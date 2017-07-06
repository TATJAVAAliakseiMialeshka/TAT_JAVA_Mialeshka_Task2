package com.epam.ta.library.controller.command.impl;

import com.epam.ta.library.controller.command.Command;

public class AccessDenied implements Command{

	@Override
	public String execute(String paramStr) {
		return "You has no permission for this operation.";
	}

}
