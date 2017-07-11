package com.epam.ta.library.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.epam.ta.library.bean.Role;
import com.epam.ta.library.bean.Role.Authority;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.MySQLDao;
import com.epam.ta.library.bean.User;

public class ControllerTestUtil {

	public static User emulateAdminUser() {
		User admin = new User();
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setAuthority(Authority.ROLE_ADMIN.name());
		roles.add(role);
		admin.setRoleList(roles);
		return admin;
	}

	public static User emulateUser() {
		User admin = new User();
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setAuthority(Authority.ROLE_USER.name());
		roles.add(role);
		admin.setRoleList(roles);
		return admin;
	}

	public static void loadDB(String dbPath) {

		Connection conn = null;
		try {
			conn = MySQLDao.createConnection();
			org.apache.ibatis.logging.LogFactory.useNoLogging();
			ScriptRunner sr = new ScriptRunner(conn);
			Reader reader = new BufferedReader(new FileReader(dbPath));
			sr.runScript(reader);
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
