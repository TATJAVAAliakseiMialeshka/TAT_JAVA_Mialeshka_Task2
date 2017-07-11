package com.epam.ta.library.controller;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;


public class ControllerTestAddBookDescription {

	private Controller controller;
	private SessionStorage sessionStorage;
	private static final String EXISTING_BOOK_ID = "1";
	
	private static final String rootPath = ControllerTestAddBookDescription.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static final String DB_DEFAULT = "../../db/library.sql";

	@BeforeClass
	public void testSetUp(){
		sessionStorage = SessionStorage.getInstance();
		User user = ControllerTestUtil.emulateAdminUser();
		sessionStorage.setSessionUser(user);
		controller = Controller.getInstance();
		ControllerTestUtil.loadDB(rootPath+DB_DEFAULT);
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
	
	@Test (dataProvider="command_addBookDescription_validData_DP", groups={"admin","addBookDescription"}, priority=1)
	public void command_addBookDescription_validData(String commandName, String userId, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (dataProvider="command_addBookDescription_invalidData_DP", groups={"admin","addBookDescription"}, priority=2)
	public void command_addBookDescription_invalidData(String commandName, String userId, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (groups={"admin","addBookDescription"}, priority=3)
	public void command_addBookDescription_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test (groups={"admin","addBookDescription"}, priority=4)
	public void command_addBookDescription_withoutAuthorizatin(){
		sessionStorage.setSessionUser(null);
		Assert.assertEquals(controller.executeTask(CommandName.ADD_BOOK_DESCRIPTION.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
}
