package com.epam.ta.library.service;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.service.exception.ServiceException;

public interface AdminService {

	List<Book> fillSubscriptionsForBooksList(List<Book> books) throws ServiceException;

	boolean receiveBookBack(Integer userId, Integer bookId) throws ServiceException;

	boolean ban(Integer userId) throws ServiceException;

	boolean activateUser(Integer userId) throws ServiceException;

	boolean activateSubscription(Integer userId) throws ServiceException;

	boolean grantAdminRole(Integer userId) throws ServiceException;
	
	
	boolean addBook(Book book) throws ServiceException;

	boolean updateBook(Book book) throws ServiceException;

	boolean updateBookStatus(Integer bookId, String status) throws ServiceException;

	boolean disableBook(Integer bookId) throws ServiceException;
	
}
