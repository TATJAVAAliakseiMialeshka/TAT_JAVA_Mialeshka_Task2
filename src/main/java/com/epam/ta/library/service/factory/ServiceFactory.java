package com.epam.ta.library.service.factory;

import com.epam.ta.library.service.AdminService;
import com.epam.ta.library.service.LoginService;
import com.epam.ta.library.service.SuperadminService;
import com.epam.ta.library.service.UserService;
import com.epam.ta.library.service.impl.AdminServiceImpl;
import com.epam.ta.library.service.impl.LoginServiceImpl;
import com.epam.ta.library.service.impl.SuperadminServiceImpl;
import com.epam.ta.library.service.impl.UserServiceImpl;

public final class ServiceFactory {

	private static ServiceFactory instance;
	private final AdminService adminService = new AdminServiceImpl();
	private final LoginService loginService = new LoginServiceImpl();
	private final SuperadminService superadminService = new SuperadminServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	private ServiceFactory (){
		super();
	}
	
	public static ServiceFactory getInstance(){
		if(null==instance){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public SuperadminService getSuperadminService() {
		return superadminService;
	}

	public UserService getUserService() {
		return userService;
	}
	
}
