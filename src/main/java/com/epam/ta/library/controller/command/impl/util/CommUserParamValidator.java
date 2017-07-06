package com.epam.ta.library.controller.command.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommUserParamValidator {

	private final static String LOGIN_PATTERN = "[A-z0-9_-]+";
	private final static String PASSWORD_PATTERN = "[A-z0-9_$%&*!#()\'\"]+";
	
	public static boolean validateLogin(String login){
		
		Matcher loginMatcher = Pattern.compile(LOGIN_PATTERN).matcher(login);
		if(loginMatcher.matches()){
			return true;
		}
		return false;
	}
	
	public static boolean validatePassword(String password){
		
		Matcher passMatcher = Pattern.compile(PASSWORD_PATTERN).matcher(password);
		if(passMatcher.matches()){
			return true;
		}
		return false;
	}

}
