package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTestEditBookInfo {

	
	private Controller controller;
	private SessionStorage sessionStorage;
	private static final String EXISTING_BOOK_ID = "1";
	
	private static final String rootPath = ControllerTestAddBookDescription.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static final String DB_DEFAULT = "../../db/library_add_test_books.sql";

	@BeforeClass
	public void testSetUp(){
		sessionStorage = SessionStorage.getInstance();
		User user = ControllerTestUtil.emulateAdminUser();
		sessionStorage.setSessionUser(user);
		controller = Controller.getInstance();
		ControllerTestUtil.loadDB(rootPath+DB_DEFAULT);
	}
	
	
	@DataProvider
	public Object[][] command_editBookInfo_validData_DP() {
	return new Object[][] {
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' 'b_descr' '100'","Book description successfully updated."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' 'b_descr' '0'","Book description successfully updated."},
	};
	}
	
	@DataProvider
	public Object[][] command_editBookInfo_invalidData_DP() {
	return new Object[][] {
		{ CommandName.EDIT_BOOK_INFO.name(),"'100' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed. Maybe there is no such book."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'-1' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'0' 'b_name' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' '' '9999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '99' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '99999' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '0' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '-1' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' 'text' 'b_descr' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' '!@#$%' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' '' '100'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' 'b_descr' 'text'","Book description update operation failed due to wrong arguments format."},
		{ CommandName.EDIT_BOOK_INFO.name(),"'113' 'b_name' '9999' 'b_descr' '-1'","Book description update operation failed due to wrong arguments format."},
	};
	}
	
	
	
	@Test (dataProvider="command_editBookInfo_validData_DP", groups={"admin","editBookInfo"})
	public void command_editBookInfo_validData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (dataProvider="command_editBookInfo_invalidData_DP", groups={"admin","editBookInfo"})
	public void command_editBookInfo_invalidData(String commandName, String userId, String expectedResponce){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateAdminUser());
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (groups={"admin","editBookInfo"})
	public void command_editBookInfo_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test (groups={"admin","editBookInfo"})
	public void command_editBookInfo_withoutAuthorizatin(){
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
}
