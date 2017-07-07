package com.epam.ta.library.service.impl;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.LoginDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.DBType;
import com.epam.ta.library.dao.factory.DaoFactory;
import com.epam.ta.library.service.LoginService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.util.Encryptor;
import com.epam.ta.library.service.util.ServiceUtil;

public class LoginServiceImpl implements LoginService {

	private static final String NULL_PARAMETER = "Received null parameter";
	private DaoFactory factory;
	private LoginDao loginDao;

	@Override
	public boolean registerUser(String name, String password) throws ServiceException {
		if (!ServiceUtil.notNullCheck(name, password)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				loginDao = factory.getLoginDao();
				String encryptPass = Encryptor.encrypt(password);
				if (loginDao.createUser(name, encryptPass)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User loginUser(String name, String password) throws ServiceException {
		if (!ServiceUtil.notNullCheck(name, password)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		User user = null;
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				loginDao = factory.getLoginDao();
				String encryptPass = Encryptor.encrypt(password);
				user = loginDao.getUserByNamePassword(name, encryptPass);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

}
