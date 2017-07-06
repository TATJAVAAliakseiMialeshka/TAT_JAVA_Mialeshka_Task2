package com.epam.ta.library.dao.connection;

import java.sql.Connection;

import com.epam.ta.library.dao.exception.DaoException;

/**
 * This interface should be implemented
 * by all connection pool classes
 */
public interface AbstractConnection {
	
	/**
	 * method gets a connection from connection pool
	 * @return Connection with data source
	 * @throws DaoException 
	 */
	public Connection getConnection() throws DaoException;

	
}
