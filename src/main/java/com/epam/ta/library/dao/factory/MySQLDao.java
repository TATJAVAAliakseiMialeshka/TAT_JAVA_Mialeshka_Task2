package com.epam.ta.library.dao.factory;

import java.sql.Connection;

import com.epam.ta.library.dao.BookDao;
import com.epam.ta.library.dao.connection.MySQLConnection;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.impl.MySQLAdminDao;
import com.epam.ta.library.dao.impl.MySQLBookDao;
import com.epam.ta.library.dao.impl.MySQLLoginDao;
import com.epam.ta.library.dao.impl.MySQLSuperadminDao;
import com.epam.ta.library.dao.impl.MySQLUserDao;


public class MySQLDao extends DaoFactory {
	
	
	private static MySQLDao instance = null;
	
	private MySQLDao(){
		super();
	}
	
	public static MySQLDao getInstance(){
		if (instance == null){
			instance = new MySQLDao();
		}
		return instance;
	}
	
	public static Connection createConnection() throws DaoException  {
		MySQLConnection mySQLConn = MySQLConnection.getInstance();
		return mySQLConn.getConnection();
	}
	
	@Override
	public MySQLUserDao getUserDao() {
		return MySQLUserDao.getInstance();
	}

	@Override
	public MySQLLoginDao getLoginDao() {
		return MySQLLoginDao.getInstance();
	}

	@Override
	public MySQLAdminDao getAdminDao() {
		return MySQLAdminDao.getInstance();
	}

	@Override
	public MySQLSuperadminDao getSuperadminDao() {
		return MySQLSuperadminDao.getInstance();
	}

	@Override
	public BookDao getBookDao() {
		return MySQLBookDao.getInstance();
	}
	

}
