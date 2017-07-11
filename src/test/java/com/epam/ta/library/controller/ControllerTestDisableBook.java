package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.ta.library.bean.User;
import com.epam.ta.library.controller.command.CommandName;
import com.epam.ta.library.controller.session.SessionStorage;

public class ControllerTestDisableBook {

	private Controller controller;
	private SessionStorage sessionStorage;
	private static final String EXISTING_BOOK_ID = "22";
	
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
	public Object[][] command_disableBook_idvalidData_DP() {
	return new Object[][] {
		{ CommandName.DISABLE_BOOK.name(),"'100'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'-1'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'0'","Book disable operation failed. Probably there is no such book."},
		{ CommandName.DISABLE_BOOK.name(),"'string'","Book disable operation failed due to wrong arguments format."},
	};
	}
	
	
	@Test (groups={"admin","disableBook"})
	public void command_disableBook_validData(){
		Assert.assertEquals(controller.executeTask(CommandName.DISABLE_BOOK.name(), EXISTING_BOOK_ID), "Book successfully disabled for ordering." );
	}
	
	@Test (dataProvider="command_disableBook_idvalidData_DP", groups={"admin","disableBook"})
	public void command_disableBook_idvalidData(String commandName, String userId, String expectedResponce){
		Assert.assertEquals(controller.executeTask(commandName, userId), expectedResponce );
	}
	
	@Test (groups={"admin","disableBook"})
	public void command_disableBook_withoutAdminRights(){
		sessionStorage.setSessionUser(ControllerTestUtil.emulateUser());
		Assert.assertEquals(controller.executeTask(CommandName.DISABLE_BOOK.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	@Test (groups={"admin","disableBook"})
	public void command_disableBook_withoutAuthorizatin(){
		sessionStorage.setSessionUser(null);
		Assert.assertEquals(controller.executeTask(CommandName.DISABLE_BOOK.name(), EXISTING_BOOK_ID), "You has no permission for this operation." );
	}
	
	
}
