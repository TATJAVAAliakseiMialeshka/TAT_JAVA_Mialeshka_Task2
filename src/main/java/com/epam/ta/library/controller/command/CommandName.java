package com.epam.ta.library.controller.command;


import com.epam.ta.library.bean.Role.Authority;

public enum CommandName {

	REGISTRATION(null),
	AUTHORIZATION(null),
	SEE_PROFILE(Authority.ROLE_USER),
	EDIT_PROFILE(Authority.ROLE_USER),
	SEE_ALL_BOOKS(null),
	ORDER_BOOK(Authority.ROLE_USER),
	REFUSE_BOOK(Authority.ROLE_USER),
	GRANT_ADMIN(Authority.ROLE_ADMIN),
	REMOVE_ADMIN(Authority.ROLE_SUPERADMIN),
	ACTIVATE_USER(Authority.ROLE_ADMIN),
	BAN_USER(Authority.ROLE_ADMIN),
	REMOVE_BAN(Authority.ROLE_ADMIN),
	MARK_BOOK_RETURNED(Authority.ROLE_ADMIN),
	ADD_BOOK_DESCRIPTION(Authority.ROLE_ADMIN),
	EDIT_BOOK_INFO(Authority.ROLE_ADMIN),
	DISABLE_BOOK(Authority.ROLE_ADMIN),
	WRONG_REQUEST(null),
	ACCESS_DINIED(null);
	
	private Authority authority;

	public Authority getAuthority() {
		return authority;
	}

	private CommandName(Authority authority) {
		this.authority = authority;
	}

	

	



	

	
	
	
	
}
