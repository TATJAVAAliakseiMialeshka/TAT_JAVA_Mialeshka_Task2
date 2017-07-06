package com.epam.ta.library.dao.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.ta.library.dao.exception.DaoException;


/**
 * This class creates a connection with MySQL database
 */
public class MySQLConnection implements AbstractConnection{

	private final String CONN_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String JDBS_CONTEXT_PATH = "jdbc:mysql://localhost/library?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
    public static final String CONNECTON_FAIL = "Failed to create the database connection.";
    public static final String DRIVER_ERROR = "Driver not found.";
   
    private static volatile MySQLConnection instance = null;

    private MySQLConnection() {
    	super();
    }
    
	public Connection getConnection() throws DaoException {
		Connection connection = null;
		 try {
					Class.forName(CONN_CLASS);
	            try {
	            	connection = DriverManager.getConnection(JDBS_CONTEXT_PATH);
	            } catch (SQLException ex) {
	            	throw new DaoException(CONNECTON_FAIL, ex);
	            }
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
            	throw new DaoException(DRIVER_ERROR, ex);
	        }
	        return connection;
	    }
   
        
    /**
     * @return an instance of
     * MySQLPoolConnection class
     */
    public static MySQLConnection getInstance(){
		if (instance == null){
			instance = new MySQLConnection();
		}
		return instance;
	}
    
}

 

   

  