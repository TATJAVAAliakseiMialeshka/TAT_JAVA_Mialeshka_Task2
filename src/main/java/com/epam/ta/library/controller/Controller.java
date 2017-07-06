package com.epam.ta.library.controller;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.CommandProvider;

public class Controller {

	private Controller() {
	}

	private static Controller instance;

	private final CommandProvider provider = new CommandProvider();

	public static Controller getInstance() {
		if (null == instance) {
			instance = new Controller();
		}
		return instance;
	}

	public String executeTask(String commandName, String commandValue) {

		Command command = null;
		String response = "Requested operation failed due to wrong arguments format.";
		if (null != commandName && !commandName.isEmpty()) {

			command = provider.getCommand(commandName);

			response = command.execute(commandValue);
		}

		return response;
	}
}
