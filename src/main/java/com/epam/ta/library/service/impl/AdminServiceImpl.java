package com.epam.ta.library.service.impl;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.bean.Role.Authority;
import com.epam.ta.library.bean.User.UserStatus;
import com.epam.ta.library.dao.AdminDao;
import com.epam.ta.library.dao.BookDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.DBType;
import com.epam.ta.library.dao.factory.DaoFactory;
import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.exception.ServiceException;
import com.epam.ta.library.service.util.ServiceUtil;

public final class AdminServiceImpl implements AdminService {

	private static final String NULL_PARAMETER = "Received null parameter";
	private DaoFactory factory;
	private AdminDao adminDao;
	private BookDao bookDao;

	@Override
	public List<Book> fillSubscriptionsForBooksList(List<Book> books) throws ServiceException {
		if (null == books) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				books = adminDao.fillSubscriptionsForBooksList(books);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return books;
	}

	@Override
	public boolean activateSubscription(Integer userId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(userId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				if (adminDao.activateSubscription(userId)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean receiveBookBack(Integer userId, Integer bookId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(userId, bookId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				if (adminDao.receiveBookBack(userId, bookId)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean grantAdminRole(Integer userId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(userId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				if (adminDao.addUserRole(userId, Authority.ROLE_ADMIN.toString())) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean ban(Integer userId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(userId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				if (adminDao.updateUserStatus(userId, UserStatus.B.toString())) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean activateUser(Integer userId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(userId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				adminDao = factory.getAdminDao();
				if (adminDao.updateUserStatus(userId, UserStatus.A.toString())) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean disableBook(Integer bookId) throws ServiceException {
		if (!ServiceUtil.notNullCheck(bookId)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				bookDao = factory.getBookDao();
				if (bookDao.disableBook(bookId)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addBook(Book book) throws ServiceException {
		if (null == book) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				bookDao = factory.getBookDao();
				if (bookDao.addBook(book)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateBookStatus(Integer bookId, String status) throws ServiceException {
		if (!ServiceUtil.notNullCheck(bookId) && !ServiceUtil.notNullCheck(status)) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				bookDao = factory.getBookDao();
				if (bookDao.updateBookStatus(bookId, status)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateBook(Book book)
			throws ServiceException {
		
		if (null == book) {
			throw new ServiceException(NULL_PARAMETER);
		}
		try {
			factory = DaoFactory.getDaoFactory(DBType.MYSQL);
			if (null != factory) {
				bookDao = factory.getBookDao();
				if (bookDao.updateBook(book)) {
					return true;
				}
			}
			return false;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
