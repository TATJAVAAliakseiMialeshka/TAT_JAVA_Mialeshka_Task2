package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTestEditProfile {

	
	private Controller controller;
	private SessionStorage sessionStorage;
	
	private static final String DB_ACTIVATED_USERS = "../../db/library.sql";
	
	private static final String rootPath = ControllerTestSignIn.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
	@BeforeClass
	public void testSetUp(){
		sessionStorage = SessionStorage.getInstance();
		controller = Controller.getInstance();
		ControllerTestUtil.loadDB(rootPath+DB_ACTIVATED_USERS);
		User user = ControllerTestUtil.emulateUser();
		sessionStorage.setSessionUser(user);
	}
	
	
	@DataProvider
	public Object[][] command_editProfile_validLoginPassword_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth' 'tenth2' 'tenth'","User profile successfully updated."},
	};
	}
	
	@DataProvider
	public Object[][] command_editProfile_validLoginPlusInvalidSymbols_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' ' tenth ' 'tenth' 'tenth'","User profile update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth!@#$%^&*()' 'tenth2' 'tenth'","User profile update operation failed due to wrong arguments format."},
	};
	}
	
	
	@DataProvider
	public Object[][] command_editProfile_invalidLogin_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' ' smth ' 'tenth'","User profile update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_PROFILE.name(),"'10' '!@#$%^&*()smth' 'tenth'","User profile update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_PROFILE.name(),"'10' '' 'tenth' 'tenth'","User profile update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_PROFILE.name(),"'10' '' 'tenth' 'tenth'","User profile update operation failed due to wrong arguments format."},
	};
	}
	
	@DataProvider
	public Object[][] command_editProfile_validPasswordPlusInvalidSymbols_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth' 'tenth' 'tenth ~'","User profile update operation failed due to wrong arguments format."},
	};
	}
	
	@DataProvider
	public Object[][] command_editProfile_invalidPassword_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth'  ' ~' 'tenth'","User profile update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth' '' 'tenth'","User profile update operation failed due to wrong arguments format."},
	};
	}
	
	
	
	@Test (dataProvider="command_editProfile_validLoginPassword_DP", groups={"user","editProfile"})
	public void command_editProfile_validLoginPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_validLoginPlusInvalidSymbols_DP", groups={"user","editProfile"})
	public void command_editProfile_validLoginPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_invalidLogin_DP", groups={"user","editProfile"})
	public void command_editProfile_invalidLogin(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_validPasswordPlusInvalidSymbols_DP", groups={"user","editProfile"})
	public void command_editProfile_validPasswordPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_invalidPassword_DP", groups={"user","editProfile"})
	public void command_editProfile_invalidPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
}
