package com.epam.ta.library.dao.factory;

import com.epam.ta.library.dao.AdminDao;
import com.epam.ta.library.dao.BookDao;
import com.epam.ta.library.dao.LoginDao;
import com.epam.ta.library.dao.SuperadminDao;
import com.epam.ta.library.dao.UserDao;

/**
 * Abstract dao factory class
 * create get dao methods,
 * which will be implemented
 * by concrete generators
 */
public abstract class DaoFactory {

	/**
	 * get <code>LoginDao</code> 
	 * must be realized by 
	 * concrete generator
	 * @return <code>LoginDao</code> 
	 */
	public abstract LoginDao getLoginDao();
	
	/**
	 * get <code>UserDao</code> 
	 * must be realized by 
	 * concrete generator
	 * @return <code>UserDao</code> 
	 */
	public abstract UserDao getUserDao();
	
	/**
	 * get <code>AdminDao</code> 
	 * must be realized by 
	 * concrete generator
	 * @return <code>AdminDao</code> 
	 */
	public abstract AdminDao getAdminDao();
	
	/**
	 * get <code>SuperadminDao</code> 
	 * must be realized by 
	 * concrete generator
	 * @return <code>SuperadminDao</code> 
	 */
	public abstract SuperadminDao getSuperadminDao();
		
	/**
	 * get <code>BookDao</code> 
	 * must be realized by 
	 * concrete generator
	 * @return <code>BookDao</code> 
	 */
	public abstract BookDao getBookDao();
	
	/**
	 * method to choose
	 * concrete dao generator
	 * @param factoryName
	 * @return
	 */
	public static DaoFactory getDaoFactory(DBType dbType){
				
		switch (dbType) {
			case MYSQL:
				return  MySQLDao.getInstance();
			default:
				return null;
			}
	}	

}
