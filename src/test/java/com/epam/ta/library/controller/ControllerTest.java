package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTest {

	private Controller controller;
	private SessionStorage sessionStorage;
	private static final String EXISTING_USER_ID = "10";
	private static final String EXISTING_BOOK_ID = "1";
	
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
	
	@DataProvider
	public Object[][] command_activateUser_invalidId_DP() {
	return new Object[][] {
		{ CommandName.ACTIVATE_USER.name(),"'100'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'-100'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'0'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'string'","User activation operation failed. Wrong crdentials."},
	};
	}
	
	@DataProvider
	public Object[][] command_addBookDescription_validData_DP() {
	return new Object[][] {
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' 'b_descr' '100'","Book description successfully added."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' 'b_descr' '0'","Book description successfully added."},
	};
	}
	
	@DataProvider
	public Object[][] command_addBookDescription_invalidData_DP() {
	return new Object[][] {
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'!@#$%' '9999' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'' '9999' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '99' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '99999' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '0' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '-1' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' 'text' 'b_descr' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' '!@#$%' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' '' '100'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' 'b_descr' 'text'","Book description operation failed due to wrong arguments format."},
		{ CommandName.ADD_BOOK_DESCRIPTION.name(),"'b_name' '9999' 'b_descr' '-1'","Book description operation failed due to wrong arguments format."},
	};
	}

	@DataProvider
	public Object[][] command_banUser_idvalidData_DP() {
	return new Object[][] {
		{ CommandName.ACTIVATE_USER.name(),"'100'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'-1'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'0'","User activation operation failed. Wrong crdentials."},
		{ CommandName.ACTIVATE_USER.name(),"'string'","User activation operation failed. Wrong crdentials."},
	};
	}
	
	@DataProvider
	public Object[][] command_disableBook_idvalidData_DP() {
	return new Object[][] {
		{ CommandName.DISABLE_BOOK.name(),"'100'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'-1'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'0'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'string'","Book disable operation failed due to wrong arguments format."},
	};
	}
	
	
	@DataProvider
	public Object[][] command_editBookInfo_validData_DP() {
	return new Object[][] {
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' 'b_descr' '100'","Book description successfully updated."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' 'b_descr' '0'","Book description successfully updated."},
	};
	}
	
	@DataProvider
	public Object[][] command_editBookInfo_invalidData_DP() {
	return new Object[][] {
		{ CommandName.EDIT_BOOK_INFO.name(),"'100' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed. Maybe there is no such book."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'-1' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'0' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' '' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '99' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '99999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '0' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '-1' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' 'text' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' '!@#$%' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' '' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' 'b_descr' 'text'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'29' 'b_name' '9999' 'b_descr' '-1'","Book description update operation failed due to wrong arguments format."},
	};
	}
	
	
	
	@DataProvider
	public Object[][] command_editProfile_validLoginPassword_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' 'tenth' 'tenth2' 'tenth'","User profile update operation failed. Wrong credentials."},
	};
	}
	
	@DataProvider
	public Object[][] command_editProfile_validLoginPlusInvalidSymbols_DP() {
	return new Object[][] {
		{ CommandName.EDIT_PROFILE.name(),"'10' ' tenth ' 'tenth2' 'tenth'","User profile update operation failed due to wrong arguments format."},
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
	
	
	@BeforeTest
	public void testSetUp(){
		sessionStorage = SessionStorage.getInstance();
		controller = Controller.getInstance();
	}
	
	@BeforeMethod
	public void methodSetUp(){
		sessionStorage.setSessionUser(null);
	}
	
	
	@Test
	public void nullParameters(){
		Assert.assertEquals(controller.executeTask(null, null), "Requested operation failed due to wrong arguments format.");
	}
	
	@Test
	public void emptyParameters(){
		Assert.assertEquals(controller.executeTask("",""), "Requested operation failed due to wrong arguments format.");
	}

	
	@Test (dataProvider="command_SignIn_validLoginPassword_DP")
	public void command_SignIn_validLoginPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertTrue(controller.executeTask(commandName, paramString).startsWith(expectedResponce) );
	}
	
	@Test (dataProvider="command_SignIn_validLoginPlusInvalidSymbols_DP")
	public void command_SignIn_validLoginPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_notExistingValidLogin_DP")
	public void command_SignIn_notExistingValidLogin(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_invalidLogin_DP")
	public void command_SignIn_invalidLogin(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_validPasswordPlusInvalidSymbols_DP")
	public void command_SignIn_validPasswordPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_SignIn_invalidPassword_DP")
	public void command_SignIn_invalidPassword(String commandName,String paramString, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	
	@Test
	public void command_SignIn_nullParameters(){
		Assert.assertEquals(controller.executeTask( CommandName.AUTHORIZATION.name(), null), "Login operation failed. Wrong credentials");
	}

	@Test
	public void command_SeeAllBooks_validCommand(){
		Assert.assertEquals(controller.executeTask(CommandName.SEE_ALL_BOOKS.name(), null),"Library books list successfully loaded.");
	}
	
	@Test
	public void command_activateUser_existingId(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "User successfully activated." );
	}
	
	@Test
	public void command_activateUser_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	
	@Test
	public void command_activateUser_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	
	@Test (dataProvider="command_activateUser_invalidId_DP")
	public void command_activateUser_invalidId(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (dataProvider="command_addBookDescription_validData_DP")
	public void command_addBookDescription_validData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (dataProvider="command_addBookDescription_invalidData_DP")
	public void command_addBookDescription_invalidData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test 
	public void command_addBookDescription_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test 
	public void command_addBookDescription_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	
	@Test
	public void command_banUser_validData(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(CommandName.BAN_USER.name(), EXISTING_USER_ID), "User successfully banned." );
	}
	
	@Test (dataProvider="command_banUser_idvalidData_DP")
	public void command_banUser_idvalidData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test
	public void command_banUser_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	
	@Test
	public void command_banUser_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.ACTIVATE_USER.name(), EXISTING_USER_ID), "You has no permission for this operation." );
	}
	
	
	
	@Test
	public void command_disableBook_validData(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(CommandName.BAN_USER.name(), EXISTING_USER_ID), "User successfully banned." );
	}
	
	@Test (dataProvider="command_disableBook_idvalidData_DP")
	public void command_disableBook_idvalidData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test
	public void command_disableBook_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.DISABLE_BOOK.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test
	public void command_disableBook_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.DISABLE_BOOK.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	
	
	@Test (dataProvider="command_editBookInfo_validData_DP")
	public void command_editBookInfo_validData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (dataProvider="command_editBookInfo_invalidData_DP")
	public void command_editBookInfo_invalidData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test 
	public void command_editBookInfo_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test 
	public void command_editBookInfo_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	
	
	@Test (dataProvider="command_editProfile_validLoginPassword_DP")
	public void command_editProfile_validLoginPassword(String commandName,String paramString, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_validLoginPlusInvalidSymbols_DP")
	public void command_editProfile_validLoginPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_invalidLogin_DP")
	public void command_editProfile_invalidLogin(String commandName,String paramString, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_validPasswordPlusInvalidSymbols_DP")
	public void command_editProfile_validPasswordPlusInvalidSymbols(String commandName,String paramString, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
	@Test (dataProvider="command_editProfile_invalidPassword_DP")
	public void command_editProfile_invalidPassword(String commandName,String paramString, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(commandName, paramString), expectedResponce );
	}
	
}
