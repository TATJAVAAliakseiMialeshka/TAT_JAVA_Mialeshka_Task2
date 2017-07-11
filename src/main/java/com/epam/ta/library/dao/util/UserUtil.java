package com.epam.ta.library.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.ta.library.bean.Role;
import com.epam.ta.library.bean.User;
import com.epam.ta.library.dao.exception.DaoException;

public class UserUtil {

	public static User buildUserWithRoles(ResultSet rs) throws DaoException, SQLException {
		User user = null;
		if (null != rs) {
			user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setStatus(rs.getString(3));
			String authoritiesString = rs.getString(4);

			if (null != authoritiesString) {
				String[] authArr = authoritiesString.split(",");
				List<Role> roles = new ArrayList<>();
				for (String roleStr : authArr) {
					Role role = new Role();
					role.setAuthority(roleStr);
					roles.add(role);
				}
				user.setRoleList(roles);
			}
		}
		return user;
	}

}
