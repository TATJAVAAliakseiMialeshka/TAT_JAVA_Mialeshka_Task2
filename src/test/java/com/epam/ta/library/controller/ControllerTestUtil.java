package com.epam.ta.library.controller;

import java.util.ArrayList;
import java.util.List;

import com.epam.ta.library.bean.Role;
import com.epam.ta.library.bean.Role.Authority;
import com.epam.ta.library.bean.User;

public class ControllerTestUtil {

	public static User emulateAdminUser(){
		User admin = new User();
		List<Role> roles  = new ArrayList<>();
		Role role = new Role();
		role.setAuthority(Authority.ROLE_ADMIN.name());
		roles.add(role);
		admin.setRoleList(roles);
		return admin;
	}
	
	public static User emulateUser(){
		User admin = new User();
		List<Role> roles  = new ArrayList<>();
		Role role = new Role();
		role.setAuthority(Authority.ROLE_USER.name());
		roles.add(role);
		admin.setRoleList(roles);
		return admin;
	}
}
