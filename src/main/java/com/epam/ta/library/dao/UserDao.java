package com.epam.ta.library.dao;

import java.util.List;

import com.epam.ta.library.bean.Subscription;
import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.exception.DaoException;

public interface UserDao {

	boolean orderBook(int userId, int bookId) throws DaoException;

	boolean refuseOrderedBook(int userId, int bookId) throws DaoException;

	List<Subscription> viewHistoryCard(int userId) throws DaoException;

	User getUserById(int userId) throws DaoException;

	boolean updateUser(int userId, String name, String password, String oldPassword) throws DaoException;

}
