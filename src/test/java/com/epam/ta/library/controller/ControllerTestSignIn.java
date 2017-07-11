package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTestSignIn {

	private Controller controller;
	private SessionStorage sessionStorage;
	
	private static final String DB_ACTIVATED_USERS = "../../db/library_activated_users.sql";
	
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
	public Object[][] command_SignIn_validLoginPassword_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"'tenth' 'tenth'","Welcome"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_validLoginPlusInvalidSymbols_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"' tenth ' 'tenth'","Login operation failed. Wrong credentials"},
		{ CommandName.AUTHORIZATION.name(),"'tenth!@#$%^&*()' 'tenth'","Login operation failed. Wrong credentials"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_notExistingValidLogin_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"'some_-0login' 'tenth'","Login operation failed. Wrong credentials"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_invalidLogin_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"' smth ' 'tenth'","Login operation failed. Wrong credentials"},
		{ CommandName.AUTHORIZATION.name(),"'!@#$%^&*()smth' 'tenth'","Login operation failed. Wrong credentials"},
		{ CommandName.AUTHORIZATION.name(),"'' 'tenth'","Login operation failed. Wrong credentials"},
		{ CommandName.AUTHORIZATION.name(),"'' 'tenth'","Login operation failed. Wrong credentials"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_notExistingValidPassword_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"'tenth' 'aZ0_!$%&#'","Login operation failed. Wrong credentials"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_validPasswordPlusInvalidSymbols_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"'tenth' 'tenth ~'","Login operation failed. Wrong credentials"},
	};
	}
	
	@DataProvider
	public Object[][] command_SignIn_invalidPassword_DP() {
	return new Object[][] {
		{ CommandName.AUTHORIZATION.name(),"'tenth' ' ~'","Login operation failed. Wrong credentials"},
		{ CommandName.AUTHORIZATION.name(),"'tenth' ''","Login operation failed. Wrong credentials"},
	};
	}
	

	
	@Test (dataProvider="command_SignIn_validLoginPassword_DP", groups={"user","signIn"})
	public void command_SignIn_validLoginPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertTrue(controller.executeTask(commandName, paramString).startsWith(expectedResponce) );
	}
	
	@Test (dataProvider="command_SignIn_validLoginPlusInvalidSymbols_DP", groups={"user","signIn"})
	public void command_SignIn_validLoginPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_notExistingValidLogin_DP", groups={"user","signIn"})
	public void command_SignIn_notExistingValidLogin(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_invalidLogin_DP", groups={"user","signIn"})
	public void command_SignIn_invalidLogin(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_validPasswordPlusInvalidSymbols_DP", groups={"user","signIn"})
	public void command_SignIn_validPasswordPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_invalidPassword_DP", groups={"user","signIn"})
	public void command_SignIn_invalidPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (groups={"user","signIn"})
	public void command_SignIn_nullParameters(){
		Assert.assertEquals(controller.executeTask( CommandName.AUTHORIZATION.name(), null), "Login operation failed. Wrong credentials");
	}
	
}
