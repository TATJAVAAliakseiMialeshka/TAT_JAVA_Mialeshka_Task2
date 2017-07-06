package com.epam.ta.library.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.exception.DaoException;

public class UserUtil {

	private static final String ERROR_DB_OPERATION_FAILED = "Database operation failed.";

	public static User buildUser(ResultSet rs) throws DaoException {
		User user = null;
		try {
			if(null != rs && rs.next()){
				user = new User();
				user.setId(1);
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setStatus(rs.getString(4));
			}
		} catch (SQLException e) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, e);
		}
		return user;
	}
}
