package Project.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import functions.Project_Functions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC01 extends Project_Functions {
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath,username,password;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);	  
 }

  @Before
  public void setUp() throws Exception {
//	MAGIC CODE GOES HERE 
	System.setProperty("webdriver.chrome.driver","c:/ChromeDriver/chromedriver.exe");
    driver = new ChromeDriver();
    prop = new Properties();	  
    
//	Load Configuration file
    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
	sAppURL = prop.getProperty("sAppURL");
	sSharedUIMapPath = prop.getProperty("SharedUIMap");	  

//	Load Shared UI Map
	prop.load(new FileInputStream(sSharedUIMapPath));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  @FileParameters("Excel/TC01a.csv")
  public void TC01a(int testcase, String username,
			String userLast, String userFirst, String role, String Password,
			String UtaID, String phoneNum, String Email,
			String streetNumber, String streetName, String city, String state, String zipCode,
			String errorMsg, String UsernameError, String LastError, String FirstError,
			String PasswordError, String RoleError, String UtaIdError, String EmailError,
			String PhoneError, String StreetNoError, String StreetNameError, String CityError,
			String StateError, String ZipError) throws Exception {
	
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
	 	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Register"))).click();
	  	Thread.sleep(500);
	    Register(driver,testcase,username,userLast,userFirst,role,Password,UtaID,phoneNum,Email,streetNumber,
	    		streetName,city,state,zipCode,methodName+"_Register_test_case_no"+testcase);
	    verifyRegisterErrorMessages(driver,errorMsg,UsernameError, LastError,FirstError,PasswordError,
	    		RoleError,UtaIdError, EmailError,PhoneError, StreetNoError, StreetNameError, CityError,
	    		StateError,ZipError,methodName+"_verifyRegisterErrorMsgs_test_case_no"+testcase);
	    driver.findElement(By.xpath(prop.getProperty("Register_Lnk_Index"))).click();
	    Thread.sleep(1_500);
   } 
  @Test
  @FileParameters("Excel/TC01b.csv")
  public void TC01b(int testcase, String username,
			String userLast, String userFirst, String role, String Password,
			String UtaID, String phoneNum, String Email,
			String streetNumber, String streetName, String city, String state, String zipCode,
			String errorMsg, String UsernameError, String LastError, String FirstError,
			String PasswordError, String RoleError, String UtaIdError, String EmailError,
			String PhoneError, String StreetNoError, String StreetNameError, String CityError,
			String StateError, String ZipError,String t1,String t2,String t3,String t4,String t5,
			String t6,String t7,String t8,String t9,String t10,String t11,String t12,String t13,String t14,
			String t15, String linkTitle) throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	
		driver.get(sAppURL);
		deleteCatererMgr(driver,sSharedUIMapPath);
	 	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Register"))).click();
	  	Thread.sleep(500);
	  	verifyButton(driver, t2, linkTitle, "Register_title_Register_User", "Index_Txt_Title", "Register_Lnk_Index");
	  	takeScreenshot(driver, methodName + "_RegisterVerifyLink_test_case_no"+testcase);
		verifyRegisterTitles(driver,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15, methodName+"_verifyRegisterTitles_test_case_no"+testcase);
		Register(driver,testcase,username,userLast,userFirst,role,Password,UtaID,phoneNum,Email,streetNumber,
	    		streetName,city,state,zipCode,methodName+"_Register_test_case_no"+testcase);
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Index_Txt_Title"))).getText().equals(linkTitle));
		Thread.sleep(1_500);

  }
  
  

@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
