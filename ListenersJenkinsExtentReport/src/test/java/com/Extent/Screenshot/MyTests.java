package com.Extent.Screenshot;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyTests extends MyExtentEx{
	@Test
	public void create()
	{
		Reporter.log("Create",true);
		ExtentTest test=extent.createTest("This is a Login test script").assignAuthor("Ricku")
				.assignCategory("Smoke Test").assignDevice("HP");
				test
				.log(Status.PASS, "This Test case execute properly")
				.pass("This can be takes all credential inputs")
				.info("This is a good executing Script");
				
				
	}
	@Test
	public void modify() 
	{
		Reporter.log("Modify",true);
		ExtentTest test=extent.createTest("This is a Logout test script").assignAuthor("Ricku")
				.assignCategory("Smoke Test").assignDevice("HP");
				test
				.log(Status.FAIL, "This Test case didn't execute properly")
				.fail("This didn't take all credential inputs")
				.info("This is not a good executing Script")
				.warning("Password alert displaying");
	}
}
