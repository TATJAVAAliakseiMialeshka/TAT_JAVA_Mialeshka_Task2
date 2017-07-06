package com.epam.ta.library.service;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.service.exception.ServiceException;

public interface LoginService {

	boolean registerUser(String name, String password) throws ServiceException;

	User loginUser(String name, String password) throws ServiceException;
	
}
