package com.epam.ta.library.dao;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.dao.exception.DaoException;

public interface BookDao {

	List<Book> getAllBooks() throws DaoException;

	boolean disableBook(int bookId) throws DaoException;

	boolean addBook(Book book) throws DaoException;

	boolean updateBookStatus(int bookId, String status) throws DaoException;

	boolean updateBook(Book book) throws DaoException;
}
