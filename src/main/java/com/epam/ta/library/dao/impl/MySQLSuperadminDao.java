package com.epam.ta.library.dao.impl;

import com.epam.ta.library.dao.SuperadminDao;

public class MySQLSuperadminDao implements SuperadminDao{

	private static MySQLSuperadminDao instance = null;
	
	private MySQLSuperadminDao() {
		super();
	}
	
	public static MySQLSuperadminDao getInstance() {
		if (instance == null) {
			instance = new MySQLSuperadminDao();
		}
		return instance;
	}
}
