package com.epam.ta.library.controller.session;

import java.util.List;

import com.epam.ta.library.bean.Book;
import com.epam.ta.library.bean.Role;
import com.epam.ta.library.bean.User;
import com.epam.ta.library.bean.Role.Authority;
import com.epam.ta.library.controller.command.CommandName;

public class SessionStorage {
	
	private SessionStorage(){}
	
	private static SessionStorage instance;
	
	private User sessionUser;
	


	private List<Book> libraryBooks; 

	public static SessionStorage getInstance(){
		if(null==instance){
			instance = new SessionStorage();
		}
		return instance;
	}

	public User getSessionUser() {
		return sessionUser;
	}


	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	public List<Book> getLibraryBooks() {
		return libraryBooks;
	}
	public void setLibraryBooks(List<Book> libraryBooks) {
		this.libraryBooks = libraryBooks;
	}
	
	public boolean checkPermission(CommandName commandName) {

		Authority authority = commandName.getAuthority();

		if (null != authority) {

			if (null != sessionUser) {
				List<Role> roles = sessionUser.getRoleList();
				for (Role role : roles) {
					if (role.getAuthority().equals(authority)) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	
	
}
