package com.epam.ta.library.view;

import java.util.Map;

import com.epam.ta.library.controller.Controller;
import com.epam.ta.library.view.exception.ViewException;

public class Performer {

	private ParamExtractor extractror;

	private Map<String, String> commandMap;

	private Controller controller;

	public Performer(String params[]) {
		super();
		this.extractror = new ParamExtractor(params);
	}

	public void perform() throws ViewException {
		commandMap = extractror.formCommandMap();
		controller = Controller.getInstance();
		for (Map.Entry<String, String> command : commandMap.entrySet()) {
			controller.executeTask(command.getKey(), command.getValue());
		}
	}
}
