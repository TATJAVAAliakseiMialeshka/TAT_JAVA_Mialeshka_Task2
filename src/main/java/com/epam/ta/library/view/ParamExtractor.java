package com.epam.ta.library.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.ta.library.view.exception.ViewException;

public class ParamExtractor {

	private static final String NO_ARGUMENTS = "Program was ran without arguments.";
	private static final String WRONG_PARAMS_FORMAT = "Wrong params format.";
	private static final String PARAMS_PATTERN = "[A-z]+=[A-z0-9_$%&*!#\\s]+";
	private String[] params;

	public ParamExtractor(String[] params) {
		super();
		this.params = params;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public Map<String, String> formCommandMap() throws ViewException {
		checkParamsExist();
		checkParamsFormat();
		return extractParams();
	}

	public void checkParamsExist() throws ViewException {
		if (null == params || params.length < 1) {
			throw new ViewException(NO_ARGUMENTS);
		}
	}

	public void checkParamsFormat() throws ViewException {
		Matcher matcher = null;
		for (String commandString : params) {
			matcher = Pattern.compile(PARAMS_PATTERN).matcher(commandString);
			if (!matcher.matches()) {
				throw new ViewException(WRONG_PARAMS_FORMAT);
			}
		}
	}

	public Map<String, String> extractParams() {
		Map<String, String> paramsMap = new LinkedHashMap<>();
		char paramDelim = '=';
		for (String commandString : params) {
			paramsMap.put(commandString.substring(0, commandString.indexOf(paramDelim)),
					commandString.substring(commandString.indexOf(paramDelim) + 1, commandString.length() - 1));
		}
		return paramsMap;
	}

}
