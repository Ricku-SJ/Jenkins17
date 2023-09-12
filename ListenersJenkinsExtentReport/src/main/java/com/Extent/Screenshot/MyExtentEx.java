package com.Extent.Screenshot;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyExtentEx {
static WebDriver driver;
ExtentReports extent=new ExtentReports();
ExtentSparkReporter spark=new ExtentSparkReporter("MyExtentReport.html");
@BeforeClass
public void open()
{
	extent.attachReporter(spark);
	Reporter.log("Open",true);
	if(System.getProperty("browser").equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("headless");
		option.addArguments("window-size=1980,1080");
		driver=new ChromeDriver();
	}
	else if(System.getProperty("browser").equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		EdgeOptions option =new EdgeOptions();
		option.addArguments("headless");
		option.addArguments("window-size=1980,1080");
		driver=new EdgeDriver();
	}
}
@BeforeMethod
public void login() throws IOException
{
	Reporter.log("Login",true);
	driver.get(System.getProperty("url"));
	String Title = driver.getTitle();
String path=captureScreenshot(Title+".png");
String base64Code=captureScreenshot();

extent
.createTest("This is for Screenshot Test 1","This Screenshot capture for Test level")
.info("This is a info msg")
.addScreenCaptureFromBase64String(base64Code,Title);
	
extent
.createTest("This is for Screenshot Test 2","This Screenshot capture for Test level")
.info("This is a info msg")
.addScreenCaptureFromPath(path,Title);

extent
.createTest("This is for Screenshot for Modify Test Script","This Screenshot capture for Log level")
.info("This is a info msg")
.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,Title).build());

Throwable t=new Throwable();
extent
.createTest("This is for Screenshot Create Test Script","This Screenshot capture for Log level")
.info("This is a info msg")
.pass(t,MediaEntityBuilder.createScreenCaptureFromPath(path, Title).build());

}
@AfterMethod
public void logout()
{
	Reporter.log("Logout",true);
}
@AfterClass
public void close() throws IOException
{
	Reporter.log("Close",true);
	extent.flush();
	Desktop.getDesktop().browse(new File("MyExtentReport.html").toURI());
}
public static String captureScreenshot (String  filename) throws IOException
{
	TakesScreenshot t=(TakesScreenshot)driver;
	File scr = t.getScreenshotAs(OutputType.FILE);
	File f=new File("./Screenshot/"+filename);
	FileUtils.copyFile(scr, f);
	return f.getAbsolutePath();
}
public static String captureScreenshot()
{
	TakesScreenshot t= (TakesScreenshot)driver;
	String base64Code = t.getScreenshotAs(OutputType.BASE64);
	return base64Code;
	
}
}
