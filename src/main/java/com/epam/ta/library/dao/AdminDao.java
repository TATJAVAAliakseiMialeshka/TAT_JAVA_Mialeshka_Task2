package com.epam.ta.library.dao;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.dao.exception.DaoException;

public interface AdminDao {

	List<Book> fillSubscriptionsForBooksList(List<Book> books) throws DaoException;

	boolean activateSubscription(int userId) throws DaoException;

	boolean receiveBookBack(int userId, int bookId) throws DaoException;

	boolean deleteUserRole(int userId, String authority) throws DaoException;

	boolean updateUserStatus(int userId, String status) throws DaoException;

	boolean addUserRole(int userId, String authority) throws DaoException;

}
