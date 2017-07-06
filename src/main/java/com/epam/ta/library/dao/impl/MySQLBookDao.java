package com.epam.ta.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.dao.BookDao;
import com.epam.ta.library.dao.exception.DaoException;
import com.epam.ta.library.dao.factory.MySQLDao;

public class MySQLBookDao implements BookDao{

	private static MySQLBookDao instance;
	
	private final static String SQL_GET_ALL_BOOKS = "select b.b_id, b.b_name, b.b_year, b.b_description, b.b_is_available, GROUP_CONCAT(a.a_name) from books b join books_has_authors using(b_id) join authors a using(a_id) group by b.b_name;";
	
	private static final String ERROR_DB_OPERATION_FAILED = "Database operation failed.";
	private static final String ERROR_SLOSING_CONNECTION = "Failed to close database connection.";
	
	private final static String SQL_CHANGE_BOOK_STATUS_TO_NOT_AVAILABLE = "update books set b_is_available = 'N' where b_id=?";
	
	private final static int ZERO_AFFECTED_ROWS = 0;
	
	private MySQLBookDao() {
		super();

	}

	public static MySQLBookDao getInstance() {
		if (instance == null) {
			instance = new MySQLBookDao();
		}
		return instance;
	}
	
	@Override
	public List<Book> getAllBooks() throws DaoException {
		List<Book> books = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL_GET_ALL_BOOKS);
			books = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book();
				
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setYear(rs.getInt(3));
				book.setDescription(rs.getString(4));
				//book.setQuantity(rs.getInt(5));
				book.setIsAvailable(rs.getString(5));
				
				String authorsString = rs.getString(6);
				if(null!=authorsString){
					String [] authorsArr = authorsString.split(",");
					List<String> authors = new ArrayList<>(Arrays.asList(authorsArr));
					book.setAuthorList(authors);
				}
				books.add(book);
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
		return books;
	}
	
	@Override
	public boolean updateBook(Book book) throws DaoException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("update books set b_name=?, b_year=?, b_description=?, b_quantity=? where b_id=?");
			stm.setString(1, book.getName());
			stm.setInt(2, book.getYear());
			stm.setString(3, book.getDescription());
			stm.setInt(4, book.getQuantity());
			stm.setInt(5, book.getId());
			
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
	public boolean disableBook(int bookId) throws DaoException{
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement(SQL_CHANGE_BOOK_STATUS_TO_NOT_AVAILABLE);

			stm.setInt(1, bookId);
			
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
	public boolean addBook(Book book) throws DaoException{
		Connection conn = null;
		PreparedStatement bookStm = null;
		PreparedStatement authorStm = null;
		PreparedStatement bookAuthorStm = null;
		Integer bookId = null;
		Integer authorId = null;
		
		try {
			conn = MySQLDao.createConnection();
			bookStm = conn.prepareStatement("insert into books (b_name, b_year, b_description, b_quantity, b_is_available) values (?,?,?,?,(case when b_quantity>0 then 'Y' else  'N' end))", Statement.RETURN_GENERATED_KEYS);
			bookStm.setString(1, book.getName());
			bookStm.setInt(2, book.getYear());
			bookStm.setString(3, book.getDescription());
			bookStm.setInt(4, book.getQuantity());
			
			int affectedRows = bookStm.executeUpdate();
			
			ResultSet rs = bookStm.getGeneratedKeys();
			if (rs.next()) {
				bookId = rs.getInt(1);
			}
			
			if(null!= book.getAuthorList()){
			for(String author: book.getAuthorList()){
				authorStm = conn.prepareStatement("insert into authors (a_name) values (?)", Statement.RETURN_GENERATED_KEYS);
				authorStm.setString(1, author);

				System.out.println("authors" +authorStm.executeUpdate());
				
				
				rs = authorStm.getGeneratedKeys();
				if (rs.next()) {
					authorId = rs.getInt(1);
				}

				bookAuthorStm = conn.prepareStatement("insert into books_has_authors values (?,?)");
				bookAuthorStm.setInt(1, bookId);
				bookAuthorStm.setInt(2, authorId);
				
				bookAuthorStm.executeUpdate();
				
				}
			}
			
			if(affectedRows>ZERO_AFFECTED_ROWS){
				//conn.commit();
				return true;
			}

		} catch (SQLException ex) {
			throw new DaoException(ERROR_DB_OPERATION_FAILED, ex);

		} finally {
			if (bookStm != null || conn != null) {
				try {
					bookStm.close();
					conn.close();
				} catch (SQLException ex) {
					throw new DaoException(ERROR_SLOSING_CONNECTION, ex);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean updateBookStatus(int bookId, String status) throws DaoException {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = MySQLDao.createConnection();
			stm = conn.prepareStatement("update users set u_status = ? where u_id = ?");
			
			stm.setString(1, status);
			stm.setInt(2, bookId);
			
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
}
