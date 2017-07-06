package com.epam.ta.library.service;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.bean.Subscription;
import com.epam.ta.library.bean.User;
import com.epam.ta.library.service.exception.ServiceException;

public interface UserService {

	boolean orderBook(Integer userId, Integer bookId) throws  ServiceException;

	boolean refuseOrderedBook(Integer userId, Integer bookId) throws ServiceException;

	List<Subscription> viewHistoryCard(Integer userId) throws ServiceException;

	List<Book> seeAllBooks() throws ServiceException; 
	
	User seeUserProfile(Integer userId) throws ServiceException;

	boolean updateUserProfile(Integer userId, String name, String password, String oldPassword)
			throws ServiceException;
}
