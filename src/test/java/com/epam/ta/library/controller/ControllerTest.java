package com.epam.ta.library.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ControllerTest {

	private Controller controller;
	
	private static final String DB_DEFAULT = "../../db/library.sql";
	
	private static final String rootPath = ControllerTestActivateUser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	
	@BeforeTest
	public void testSetUp(){
		controller = Controller.getInstance();
		ControllerTestUtil.loadDB(rootPath+DB_DEFAULT);
	}
	
	
	@Test (groups="general")
	public void nullParameters(){
		Assert.assertEquals(controller.executeTask(null, null), "Requested operation failed due to wrong arguments format.");
	}
	
	@Test (groups="general")
	public void emptyParameters(){
		Assert.assertEquals(controller.executeTask("",""), "Requested operation failed due to wrong arguments format.");
	}

}
