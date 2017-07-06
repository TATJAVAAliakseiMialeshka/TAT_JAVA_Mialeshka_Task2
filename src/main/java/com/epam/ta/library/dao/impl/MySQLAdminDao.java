package com.epam.ta.library.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.bean.Subscription;
import com.epam.ta.library.dao.AdminDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.MySQLDao;

public class MySQLAdminDao implements AdminDao {

	private static final String SQL_GET_BOOK_SUBSCRIPTIONS = "select * from subscriptions where b_id=?";
	private static final String ERROR_DB_OPERATION_FAILED = "Database operation failed.";
	private static final String ERROR_SLOSING_CONNECTION = "Failed to close database connection.";

	
	private static final String SQL_ACTIVATE_SUBSCRIPTION = "update subscriptions sb set sb.sb_start = ?, sb.sb_finish = ?, sb.sb_status = 'S' where sb.u_id=?";

	
	private final static String SQL_UPDATE_BOOK_QUANTITY_INCREASE_AND_STATUS = "UPDATE books b set b.b_is_available = case when b.b_quantity = 0 then 'Y' else b.b_is_available end , b.b_quantity=b.b_quantity+1 where b.b_id = ?";

	private final static int ZERO_AFFECTED_ROWS = 0;

	private static final int SUBSCRIPTION_TIME = 30;
	
	private static MySQLAdminDao instance = null;
	
	private MySQLAdminDao() {
		super();

	}

	public static MySQLAdminDao getInstance() {
		if (instance == null) {
			instance = new MySQLAdminDao();
		}
		return instance;
	}

	@Override
	public List<Book> fillSubscriptionsForBooksList(List<Book> books) throws DaoException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		List<Subscription> subscriptions = null;
		ListIterator<Book> itr = books.listIterator();
		while (itr.hasNext()) {
			Book book = itr.next();
			try {
				int bookId = book.getId();
				subscriptions = new ArrayList<>();
				conn = MySQLDao.createConnection();
				stm = conn.prepareStatement(SQL_GET_BOOK_SUBSCRIPTIONS);
				stm.setInt(1, bookId);
				rs = stm.executeQuery();
				while (rs.next()) {
					Subscription subscription = new Subscription();
					subscription.setId(rs.getInt(1));
					subscription.setUserId(rs.getInt(2));
					subscription.setBookId(rs.getInt(3));
					subscription.setStartDate(rs.getDate(4));
					subscription.setEndDate(rs.getDate(5));
					subscription.setStatus(rs.getString(6));
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
						throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
					}
				}
			}
			if (subscriptions.size() > 0) {
				book.setSubscriptionList(subscriptions);
				itr.set(book);
			}
		}

		return books;

	}
	
	@Override
	public boolean addUserRole(int userId, String authority) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("insert into users_has_role values(?,(select r_id from role where r_authority=?))");
			stm.setInt(1, userId);
			stm.setString(2, authority);
			
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
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteUserRole(int userId, String authority) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("delete from users_has_role where u_id=? and r_id=(select r_id from role where r_authority=?)");
			stm.setInt(1, userId);
			stm.setString(2, authority);
			
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
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean activateSubscription(int userId) throws DaoException { //activateUserSubscription
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_ACTIVATE_SUBSCRIPTION);

			Calendar currentTime = Calendar.getInstance();	
			
			stm.setDate(1, convertDateToSQLFormat(currentTime));
			currentTime.add(Calendar.DATE, SUBSCRIPTION_TIME);
			stm.setDate(2, convertDateToSQLFormat(currentTime));
			stm.setInt(3, userId);
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
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean receiveBookBack(int userId, int bookId) throws DaoException { //activateUserSubscription
		Connection conn = null;
		PreparedStatement subscriptionStm = null;
		PreparedStatement stmAndIncreaseBooksCount = null;
		
		try {
			conn = MySQLDao.createConnection();
			conn.setAutoCommit(false);
			subscriptionStm = conn.prepareStatement("update subscriptions sb set sb.sb_finish = ?, sb.sb_status = 'E' where sb.u_id=? and sb.b_id=?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			Calendar currentTime = Calendar.getInstance();	
			
			subscriptionStm.setDate(1, convertDateToSQLFormat(currentTime));
			subscriptionStm.setInt(2, userId);
			subscriptionStm.setInt(3, bookId);
			subscriptionStm.addBatch();
			
			stmAndIncreaseBooksCount = conn.prepareStatement(SQL_UPDATE_BOOK_QUANTITY_INCREASE_AND_STATUS,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmAndIncreaseBooksCount.setInt(1, bookId);
			stmAndIncreaseBooksCount.addBatch();

			int[] affectedSubscriptionRows = subscriptionStm.executeBatch();
			int[] affectedBookRows = stmAndIncreaseBooksCount.executeBatch();
			
			if (affectedBookRows[0] > ZERO_AFFECTED_ROWS && affectedSubscriptionRows[0] > ZERO_AFFECTED_ROWS) {
				conn.commit();
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (subscriptionStm != null || conn != null) {
				try {
					subscriptionStm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	
	
	@Override
	public boolean updateUserStatus(int userId, String status) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("update users set u_status = ? where u_id = ?");
			
			stm.setString(1, status);
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
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	
	
	private Date convertDateToSQLFormat(Calendar calendar) {

		return new Date(calendar.getTime().getTime());
	}

}
