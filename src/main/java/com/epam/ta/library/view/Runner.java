package com.epam.ta.library.view;

import org.apache.log4j.Logger;
import com.epam.ta.library.view.exception.ViewException;

public class Runner {

	private static final String USAGE = "\nUsage:\nRun with arguments:\n<\"command_1='command_1_param_1' '...' 'command_1_param_n'\">\n<\"command_2='command_2_param_1' '...' 'command_2_param_n'\">\n...\n<\"command_n='command_n_param_1' '...' 'command_n_param_n'\">";
	
	private static final Logger log = Logger.getLogger(Runner.class);

	public static void main(String args[]) {
		
		Performer performer = null;
		
		try {
			performer = new Performer(args);
			performer.perform();

		} catch (ViewException e) {
			log.error(e);
			ConsolePrinter.printError(e.getMessage() + USAGE);
		}
		
	}
}
