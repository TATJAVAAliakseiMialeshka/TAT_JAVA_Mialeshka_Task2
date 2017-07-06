package com.epam.ta.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ta.library.bean.Subscription;
import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.UserDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.MySQLDao;
import com.epam.ta.library.dao.util.UserUtil;

public class MySQLUserDao implements UserDao {

	private static MySQLUserDao instance = null;


	private final static String SQL_ORDER_BOOK = "INSERT INTO subscriptions (u_id, b_id) values(?,?)";
	private final static String SQL_UPDATE_BOOK_QUANTITY_DECREASE_AND_STATUS = "UPDATE books b set b.b_is_available = case when b.b_quantity = 1 then 'N' else b.b_is_available end , b.b_quantity=b.b_quantity-1 where b.b_id = ? and b.b_is_available='Y'";

	private static final String ERROR_DB_OPERATION_FAILED = "Database operation failed.";// "error.db.search.failer";
	private static final String ERROR_CLOSING_CONNECTION = "Failed to close database connection.";

	private final static String SQL_REFUSE_ORDERED_BOOK = "DELETE FROM subscriptions where u_id = ? and b_id = ? and sb_status = 'P'";
	private final static String SQL_UPDATE_BOOK_QUANTITY_INCREASE_AND_STATUS = "UPDATE books b set b.b_is_available = case when b.b_quantity = 0 then 'Y' else b.b_is_available end , b.b_quantity=b.b_quantity+1 where b.b_id = ?";
	
	private final static int ZERO_AFFECTED_ROWS = 0;
	
	private final static String SQL_GET_SUBSCRIPTION = "select b.b_name, sb.sb_start, sb.sb_finish from users u join subscriptions sb using (u_id) join books b using (b_id) where u.u_id=? and sb.sb_status != 'P';";

	private final static String SQL_UPDATE_USER = "update users set u_name=?, u_password=? where u_id=? and u_password=?";

	private MySQLUserDao() {
		super();

	}

	public static MySQLUserDao getInstance() {
		if (instance == null) {
			instance = new MySQLUserDao();
		}
		return instance;
	}



	@Override
	public List<Subscription> viewHistoryCard(int userId) throws DaoException {
		List<Subscription> subscriptions = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_GET_SUBSCRIPTION);
			stm.setInt(1, userId);
			
			rs = stm.executeQuery();
			
			subscriptions = new ArrayList<>();
			while (rs.next()) {
				Subscription subscription = new Subscription();
				subscription.setBookName(rs.getString(1));
				subscription.setStartDate(rs.getDate(2));
				subscription.setEndDate(rs.getDate(2));
				subscriptions.add(subscription);
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (rs != null || stm != null || conn != null) {
				try {
					rs.close();
					stm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return subscriptions;
	}
	

	@Override
	public boolean orderBook(int userId, int bookId) throws DaoException {
		Connection conn = null;
		PreparedStatement stmOrderBook = null;
		PreparedStatement stmAndDecreaseBooksCount = null;

		try {
			conn = MySQLDao.createConnection();
			conn.setAutoCommit(false);
			stmOrderBook = conn.prepareStatement(SQL_ORDER_BOOK, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmOrderBook.setInt(1, userId);
			stmOrderBook.setInt(2, bookId);
			stmOrderBook.addBatch();

			stmAndDecreaseBooksCount = conn.prepareStatement(SQL_UPDATE_BOOK_QUANTITY_DECREASE_AND_STATUS,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmAndDecreaseBooksCount.setInt(1, bookId);
			stmAndDecreaseBooksCount.addBatch();

			int affectedSubscriptionRows[] = stmOrderBook.executeBatch();

			int[] affectedBookRows = stmAndDecreaseBooksCount.executeBatch();
			if (affectedBookRows[0] > ZERO_AFFECTED_ROWS && affectedSubscriptionRows[0] > ZERO_AFFECTED_ROWS) {
				conn.commit();
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stmOrderBook != null || stmAndDecreaseBooksCount != null || conn != null) {
				try {
					stmOrderBook.close();
					stmAndDecreaseBooksCount.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}

	@Override
	public boolean refuseOrderedBook(int userId, int bookId) throws DaoException {
		Connection conn = null;
		PreparedStatement stmRefuseOrderedBook = null;
		PreparedStatement stmAndIncreaseBooksCount = null;

		try {
			conn = MySQLDao.createConnection();
			conn.setAutoCommit(false);
			stmRefuseOrderedBook = conn.prepareStatement(SQL_REFUSE_ORDERED_BOOK, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmRefuseOrderedBook.setInt(1, userId);
			stmRefuseOrderedBook.setInt(2, bookId);
			stmRefuseOrderedBook.addBatch();

			stmAndIncreaseBooksCount = conn.prepareStatement(SQL_UPDATE_BOOK_QUANTITY_INCREASE_AND_STATUS,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmAndIncreaseBooksCount.setInt(1, bookId);
			stmAndIncreaseBooksCount.addBatch();

			int[] affectedSubscriptionRows = stmRefuseOrderedBook.executeBatch();
			int[] affectedBookRows = stmAndIncreaseBooksCount.executeBatch();
			System.out.println(affectedSubscriptionRows.length +" "+ affectedBookRows.length);
			System.out.println(affectedSubscriptionRows[0] + " "+ affectedBookRows[0]);
			if (affectedBookRows[0] > ZERO_AFFECTED_ROWS && affectedSubscriptionRows[0] > ZERO_AFFECTED_ROWS) {
				conn.commit();
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (stmRefuseOrderedBook != null || stmAndIncreaseBooksCount != null || conn != null) {
				try {
					stmRefuseOrderedBook.close();
					stmAndIncreaseBooksCount.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_CLOSING_CONNECTION, ex);
				}
			}
		}
		return true;
	}

	@Override
	public User getUserById(int userId) throws DaoException {
		User user = null;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("select * from users where u_id = ?");
			stm.setInt(1, userId);
			rs = stm.executeQuery();
			user = UserUtil.buildUser(rs);

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (rs != null || stm != null || conn != null) {
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
	public boolean updateUser(int userId, String name, String password, String oldPassword) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_UPDATE_USER);
			stm.setString(1, name);
			stm.setString(2, password);
			stm.setInt(3, userId);
			stm.setString(4, oldPassword);
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
