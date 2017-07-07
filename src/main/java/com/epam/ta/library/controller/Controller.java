package com.epam.ta.library.controller;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.Command;
import com.epam.ta.library.controller.command.CommandProvider;

public class Controller {

	private Controller() {
	}
	
	private static final String WRONG_ARG_FORMAT = "Requested operation failed due to wrong arguments format.";
	private static Controller instance;
	private final static Logger log = Logger.getLogger(Controller.class);
	private final CommandProvider provider = new CommandProvider();

	public static Controller getInstance() {
		if (null == instance) {
			instance = new Controller();
		}
		return instance;
	}

	public String executeTask(String commandName, String commandValue) {

		Command command = null;
		String response = WRONG_ARG_FORMAT;
		if (null != commandName && !commandName.isEmpty()) {

			command = provider.getCommand(commandName);

			response = command.execute(commandValue);
		}
		log.info(response);
		return response;
	}
}
