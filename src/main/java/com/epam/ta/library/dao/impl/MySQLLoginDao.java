package com.epam.ta.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.LoginDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.MySQLDao;

public final class MySQLLoginDao implements LoginDao{

	private static MySQLLoginDao instance = null;

	private final static String SQL_UPDATE_USERNAME = "update users set u_name=? where u_id=? and u_status != 'B'";
	private final static String SQL_UPDATE_PASSWORD= "update users set u_password=? where u_id=? and u_status != 'B'";
	private final static String SQL_ADD_USER= "insert into users (u_name, u_password) values(?,?)";
	private final static String SQL_SELECT_USER_BY_NAME_PASS = "select u_id, u_name, u_status from users where u_name=? and u_password=?";
	private final static String SQL_SELECT_USER_BY_ID = "select u_id, u_name, u_status from users where u_id=?";
	private final static String SQL_SELECT_USER_BY_LOGIN = "select u_id from users where u_name = ?";
	
	private static final String ERROR_DB_OPERATION_FAILED = "Database operation failed.";
	private static final String ERROR_CLOSING_CONNECTION = "Failed to close database connection.";
	private static final String ERROR_USER_NOT_FOUND = "User not found.";
	
	private final static int ZERO_AFFECTED_ROWS = 0;

	private MySQLLoginDao() {
		super();

	}
	
	public static MySQLLoginDao getInstance() {
		if (instance == null) {
			instance = new MySQLLoginDao();
		}
		return instance;
	}
	
	@Override
	public boolean createUser(String name, String password) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;
		if (!checkUserExists(name)) {
			try {
				conn = MySQLDao.createConnection();
				stm = conn.prepareStatement(SQL_ADD_USER);
				stm.setString(1, name);
				stm.setString(2, password);

				if (stm.executeUpdate() > ZERO_AFFECTED_ROWS) {
					return true;
				}

			} catch (SQLException ex) {
				throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

			} finally {
				if (stm != null || conn != null) {
					try {
						stm.close();
						conn.close();
					} catch (SQLException ex) {
						throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public User getUserByNamePassword(String name, String password) throws DaoException {
		User user = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_SELECT_USER_BY_NAME_PASS);
			stm.setString(1, name);
			stm.setString(2, password);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setStatus(rs.getString(3));
			} else {
				throw new DaoException(ERROR_USER_NOT_FOUND);
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stm != null || conn != null) {
				try {
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return user;
	}
	
	@Override
	public User getUserById(int id) throws DaoException {
		User user = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_SELECT_USER_BY_ID);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setStatus(rs.getString(3));
			} else {
				throw new DaoException(ERROR_USER_NOT_FOUND);
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stm != null || conn != null) {
				try {
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return user;
	}
	
	
	@Override
	public boolean checkUserExists(String name) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
			stm.setString(1, name);
			rs = stm.executeQuery();
			if(rs.next()){
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stm != null || conn != null) {
				try {
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}

	
	@Override
	public boolean changeUsername(int userId, String newName) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_UPDATE_USERNAME);
			stm.setString(1, newName);
			stm.setInt(2, userId);
			
			if(stm.executeUpdate()>ZERO_AFFECTED_ROWS){
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stm != null || conn != null) {
				try {
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean changePassword(int userId, String newPassword) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_UPDATE_PASSWORD);
			stm.setString(1, newPassword);
			stm.setInt(2, userId);
			
			if(stm.executeUpdate()>ZERO_AFFECTED_ROWS){
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stm != null || conn != null) {
				try {
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}

}
