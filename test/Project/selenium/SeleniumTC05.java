package Project.selenium;

import java.util.Calendar;
import java.util.List;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import functions.Project_Functions;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import java.util.logging.Level;
import java.util.logging.Logger;



@RunWith(JUnitParamsRunner.class)
public class SeleniumTC05 extends Project_Functions {
	
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
  @FileParameters("Excel/TC05a.csv")
  public void TC05a(int testCaseNo, String title1, String errmsg1, String unamelabel, String username, 
		  String pwdlabel, String password, String usernameerr, String passworderr, String title2, 
		  String button2, String title3, String button3, String title4, String button4, 
		  String title5, String title6, String title7, String main, String title8, String upuser, 
		  String uppwd,String upln, String upfn, String uprole, String uputaid, String upphone,
		  String upemail, String upsno, String upsname, String upcity, String upstate, String upzc) 
		  throws Exception {
	  
	String methodName = new Throwable().getStackTrace()[0].getMethodName();
	
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	
	//Login page tests
	if (!title1.equals(""))
		verifyLoginHeaders(title1,unamelabel,pwdlabel, methodName+"_verifyLoginHeaders_test_case_no"+testCaseNo);
	Login(driver, username, password, methodName+"_Login_test_case_no"+testCaseNo);
	
	if(! errmsg1.equals("") )
	{
		verifyLoginErrors(errmsg1,usernameerr,passworderr, methodName+"_verifyLoginErrors_test_case_no"+testCaseNo);
		
		driver.findElement(By.xpath(prop.getProperty("Login_Lnk_Index"))).click();
	    Thread.sleep(1_500);
	}
	
	else {
		
		//User Homepage Tests
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserHomePage_Txt_Title"))).getText().equals(title2));
	    takeScreenshot(driver, methodName + "_verifyTitleUserHomePage_test_case_no"+testCaseNo);
		
	    verifyUserHomeLinks(title2,title3,title4, title5, main, methodName+"_verifyUserHomeLinks_test_case_no"+testCaseNo);
	    
		//View User Profile
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_User_Profile"))).click();
		verifyUserProfileHeaders(title5, methodName+"_verifyUserProfileHeaders_test_case_no"+testCaseNo);
		verifyUserProfileLinks(title5, title2, title8, main, methodName+"_verifyUserProfileLinks_test_case_no"+testCaseNo);
		verifyUserProfileTitles(driver, title5, upuser, uppwd, upln, upfn, uprole, uputaid, upphone, upemail, upsno, upsname, 
				                upcity, upstate, upzc,methodName+"_verifyUserProfileTitles_test_case_no"+testCaseNo); 
		
		verifyUserProfileData(driver,username,methodName+"_verifyUserProfileData_test_case_no"+testCaseNo);
		
		Logout("ViewProfile_Btn_Logout");
		
	}
  }
  
  
  @Test
  @FileParameters("Excel/TC05b.csv")
  public void TC05b(int testCaseNo, String username, String password, String title2, 
		  String button2, String title3, String button3, String title4, String button4, 
		  String title5, String title6, String title7, String main, 
		  String errmsg2, String datelabel, String date, String timelabel, String time, String date_err) 
		  throws Exception {
	  
	String methodName = new Throwable().getStackTrace()[0].getMethodName();
	if (date.equals("Today"))
		date = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
	
	
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username, password, "none");
	driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_Events"))).click();
		
	// User search for events page tests
	if (!title3.equals("")) {
		verifyUserSearchHeaders(title3,datelabel,timelabel, methodName+"_verifyUserSearchHeaders_test_case_no"+testCaseNo);
	
		verifyUserSearchLinks(title3,main, methodName+"_verifyUserSearchLinks_test_case_no"+testCaseNo);
	}
	ViewUserEvent(date,time, methodName+"_UserSearch_test_case_no"+testCaseNo);
		
	if(! errmsg2.equals("") ) {
		verifyUserSearchErrors(errmsg2,date_err, methodName+"_verifyUserSearchErrors_test_case_no"+testCaseNo);
		
		Logout("SearchUserEventSummary_Btn_Logout");
	}
		
	else {
	    //Verify the search was successful by checking title on next page
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_Title"))).getText().equals(title6));
	    takeScreenshot(driver, methodName + "_verifySuccessUserSearch_test_case_no"+testCaseNo);
	    	
	    Logout("UserEventSummary_Btn_Logout");
	 }
	
  }
  
  @Test
  @FileParameters("Excel/TC05c.csv")
  public void TC05c(int testCaseNo, String title1, String username, String password, String title7, String main, 
	    			  String errmsg3, String datelabel2, String date2, String timelabel2, String time2, String date_err2) 
	    			  throws Exception {
	    		  
	    String methodName = new Throwable().getStackTrace()[0].getMethodName();
	    if (date2.equals("Today"))
	    	date2 = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
	    	
	    driver.get(sAppURL);
	    driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	    Login(driver, username, password, "none");
	    	
	    // User Request for New Event (search dates and times page)
	    	
	    driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_Request_Event"))).click();
		
	    if (!title7.equals("")) {
	    	verifyUserRequestEventHeaders(title7,datelabel2,timelabel2, methodName+"_verifyUserRequestEventHeaders_test_case_no"+testCaseNo);
	    	verifyUserRequestEventLinks(title7,main, methodName+"_verifyUserRequestEventLinks_test_case_no"+testCaseNo);
	    }
		UserRequestEvent(date2,time2, methodName+"_UserRequestEvent_test_case_no"+testCaseNo);
	    	
	    if(! errmsg3.equals("") ) {

			verifyUserRequestEventErrors(errmsg3,date_err2, methodName+"_verifyUserRequestEventDatesErrors_test_case_no"+testCaseNo);
			
			Logout("RequestEventDates_Btn_Logout");
		}
			
		else {
		    //Verify the search was successful by checking title on next page
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Title"))).getText().equals(title7));
		    takeScreenshot(driver, methodName + "_verifySuccessUserRequestEvent_test_case_no"+testCaseNo);
		    
		    Logout("RequestEventInputs_Btn_Logout");
		}	    		
	
  }

  @Test
  @FileParameters("Excel/TC05d.csv")
  public void TC05d(int testCaseNo, String username1, String password1, String role, String homePage, 
		  String LastName, String FirstName, String UTAID, String Phone, String Email, String StreetNumber, String StreetName, 
		  String City, String State, String Zipcode) throws Exception {
	  
	    /* This test is just a coverage test to make sure all the users can update their profile and are redirected back to 
	     * their respective home page. The view profile and update profile pages are the same for all users and are thoroughly tested in
	     * TC05a and TC04d.
	     */
  
  
	  	String methodName = new Throwable().getStackTrace()[0].getMethodName();
	  	String home = "";
	  	
	  	driver.get(sAppURL);
	  	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	
	  	Login(driver, username1, password1, "none");
	
	
	  	if (role.equals("user"))
	  		home = "UserHomePage";
	  	else if (role.equals("manager"))
	  		home = "MgrHomePage";
	  	else if (role.equals("admin"))
	  		home = "AdminHomepage";
	  	
	  	driver.findElement(By.xpath(prop.getProperty(home+"_Btn_View_My_User_Profile"))).click();
	  	Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("ViewProfile_Btn_Update_Profile"))).click();
		Thread.sleep(500);
		
		UpdateProfile (driver, username1, password1, LastName, FirstName, UTAID, Phone, Email, StreetNumber, StreetName,
	    		City, State, Zipcode, methodName+"UpdateProfile_test_case_no"+testCaseNo);
	    Thread.sleep(500);
	    
		//Verify the update was successful by checking title on home page
	    assertTrue(driver.findElement(By.xpath(prop.getProperty(home+"_Txt_Title"))).getText().equals(homePage));
	    takeScreenshot(driver, methodName + "_verifySuccessUpdatePrfile_test_case_no"+testCaseNo);
	    
		Logout(home+"_Btn_Logout");
		
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
