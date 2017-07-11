package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTestActivateUser {

	private Controller controller;
	private SessionStorage sessionStorage;
	private static final String EXISTING_USER_ID = "10";
	
	private static final String DB_DEFAULT = "../../db/library.sql";
	private static final String rootPath = ControllerTestActivateUser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	
	@DataProvider
	public Object[][] command_activateUser_invalidId_DP() {
	return new Object[][] {
		{ CommandName.ACTIVATE_USER.name(),"'100'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'-100'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'0'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'string'","User activation operation failed. Wrong crdentials."},
	};
	}
	
	
	@BeforeClass
	public void testSetUp(){
		sessionStorage = SessionStorage.getInstance();
		controller = Controller.getInstance();
		ControllerTestUtil.loadDB(rootPath+DB_DEFAULT);
		User user = ControllerTestUtil.emulateAdminUser();
		sessionStorage.setSessionUser(user);
	}
	
	@BeforeGroups (groups="activateUser")
	public void adminSetUp(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
	}
	
	@Test (groups={"admin","activateUser"})
	public void command_activateUser_existingId(){
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "User successfully activated." );
	}
	
	@Test (dataProvider="command_activateUser_invalidId_DP", groups={"admin","activateUser"})
	public void command_activateUser_invalidId(String commandName, String userId, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (groups={"admin","activateUser"})
	public void command_activateUser_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	
	@Test (groups={"admin","activateUser"})
	public void command_activateUser_withoutAuthorizatin(){
		sessionStorage.setSessionUser(null);
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	

}
