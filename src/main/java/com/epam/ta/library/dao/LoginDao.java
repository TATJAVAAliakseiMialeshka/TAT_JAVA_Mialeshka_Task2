package com.epam.ta.library.dao;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.exception.DaoException;

public interface LoginDao {

	boolean checkUserExists(String name) throws DaoException;

	boolean createUser(String name, String password) throws DaoException;

	User getUserByNamePassword(String name, String password) throws DaoException;
	
	User getUserById(int userId) throws DaoException;

	boolean changeUsername(int userId, String newName) throws DaoException;

	boolean changePassword(int userId, String newPassword) throws DaoException;

}
