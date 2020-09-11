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
public class SeleniumTC04 extends Project_Functions {
	
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
	
	//Load login properties
	prop.load(new FileInputStream("./Login/Login.properties"));
	username = prop.getProperty("username3");
	password = prop.getProperty("password3");
	
//	Load Shared UI Map
	prop.load(new FileInputStream(sSharedUIMapPath));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  @FileParameters("Excel/TC04a.csv")
  public void TC04a(int testCaseNo, String date, String time, int radioButton, String maintitle, String title1, String title2, 
		  String title3, String title4, String title5, String title6, String title7, String title8, String title, String button,
		  String button3, String button4, String error,String title11, String title12, String title13, String main) throws Exception { 
	
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	 
	//Caterer Staff Homepage Tests
	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Title"))).getText().equals(title11));
	takeScreenshot(driver, methodName + "_verifyTitleCatererManagerHomePage_test_case_no"+testCaseNo);
	verifyCSHomeLinks(title11,title12,title13,main, methodName+"_verifyCSHomeLinks_test_case_no"+testCaseNo);
			
    driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Btn_View_My_Assigned_Events"))).click();
    searchMyEvents(date,time, methodName+"_searchMyEvents_test_case_no"+testCaseNo);
    
    //Caterer Staff Event List Tests
    verifyTC04aTitles(driver, maintitle, title1, title2, title3, title4, title5, title6, title7, title8, methodName+"_verifyTC04aTitles_test_case_no"+testCaseNo);
    
	verifyEventListData("CatererStaffEventList_Event_Table",username,time,date, methodName+"_verifyEventListData_test_case_no"+testCaseNo);
    
    verifyCSListLinks(error,maintitle,main,title11,methodName+"_verifyCSListLinks_test_case_no"+testCaseNo);
    
    verifyRadioButtonsCS("CatererStaffEventList_Event_Table", button4, methodName+"_verifyRadioButtonsCM_test_case_no"+testCaseNo);
   
    Logout("CatererStaffEventList_Btn_Logout");
    
  }

  @Test
  @FileParameters("Excel/TC04b.csv")
  public void TC04b(int testCaseNo, String date, String time, int radioButton, String title,
		  int eventId, String title1, String title2, String title3, String title4,
		  String title5, String title6, String title7, String title8, String title9,
		  String title10, String title11, String title12,String title13, String title14,
		  String title15, String title16, String button, String button2
		  ) throws Exception { 

	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
		
	
	driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Btn_View_My_Assigned_Events"))).click();
	searchMyEvents(date,time, methodName+"_searchMyEvents_test_case_no"+testCaseNo);
	
	selectEventCS(radioButton,methodName+"_selectEventCS_test_case_no"+testCaseNo);
	
	 //Test for "Caterer Staff View Selected Event" begins here:
    verifyTC04bTitles(driver, title, title1, title2, title3, title4, title5, title6, title7, title8, title9, title10, 
    		title11, title12, title13, title14, title15, title16, methodName+"_verifyTC04bTitles_test_case_no"+testCaseNo);
  
    verifyTC04bData(driver,eventId, methodName+"_verifyTC04bData_test_case_no"+testCaseNo);
    verifyCSSelectedLinks(title,button,button2,methodName+"_verifyCSSelectedLinks_test_case_no"+testCaseNo);
    
    Logout("CatererStaffSelectedEvent_Btn_Logout");
  }

  @Test
  @FileParameters("Excel/TC04c.csv")
  public void TC04c(int testCaseNo, String date, String time, String button, String title, String next_title,
		  String date_label,String time_label, String errMsg, String dateErr) throws Exception { 
	  
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	
	if (date.equals("Today"))
		date = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
	
    driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Btn_View_My_Assigned_Events"))).click();
    
    //Caterer Staff Event Search Page Tests
    if (!title.equals("")) {
    	verifyCSSearchHeaders(title,date_label,time_label,methodName+"_verifyCSSearchHeaders_test_case_no"+testCaseNo);
    	verifyCSSearchLinks(title, button, methodName+"_verifyCSSearchLinks_test_case_no"+testCaseNo);
    }
    searchMyEvents(date,time, methodName+"_searchMyEvents_test_case_no"+testCaseNo);
    
    if(!errMsg.equals(""))
	{
    	verifyCSSearchErrors(errMsg,dateErr, methodName+"_verifyCMSearchErrors_test_case_no"+testCaseNo);
    	
    	Logout("CatererMgrSearch_Btn_Logout");
	}
    else {
    	//Verify the search was successful by checking title on next page
    	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_Title"))).getText().equals(next_title));
    	takeScreenshot(driver, methodName + "_verifySuccessCatererStaffSearch_test_case_no"+testCaseNo);
    
    	Logout("CatererStaffEventList_Btn_Logout");
    }
  }

  @Test
  @FileParameters("Excel/TC04d.csv")
  public void TC04d(int testCaseNo, String currentPageTitle,
		  String UsernameTitle, String PasswordTitle, String LastNameTitle, String FirstNameTitle,
		  String RoleTitle, String CatererStaff, String UTAIDTitle, String PhoneTitle, String EmailTitle,
		  String StreetNumberTitle, String StreetNameTitle, String CityTitle, String StateTitle, String ZipcodeTitle,
		  String Username, String Password, String LastName, String FirstName, String UTAID,
		  String Phone, String Email, String StreetNumber, String StreetName, String City,
		  String State, String Zipcode, String errMsg, String Usernameerr, String Passworderr, String LastNameerr,
		  String FirstNameerr, String UTAIDerr, String Phoneerr, String Emailerr, String StreetNumbererr, String StreetNameerr,
		  String Cityerr, String Stateerr, String Zipcodeerr, String homePageTitle,
		  String main
		  ) throws Exception {

	driver.get(sAppURL);
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username, password, "none");
	Thread.sleep(800);
    
	driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Btn_View_My_User_Profile"))).click();
	Thread.sleep(500);
	driver.findElement(By.xpath(prop.getProperty("ViewProfile_Btn_Update_Profile"))).click();

	//Update Profile Page Tests
	if (!currentPageTitle.equals("")) {
		verifyUpdateTitles(driver, currentPageTitle, UsernameTitle, PasswordTitle, LastNameTitle, FirstNameTitle,
    		RoleTitle, CatererStaff, UTAIDTitle, PhoneTitle, EmailTitle, StreetNumberTitle, 
    		StreetNameTitle, CityTitle, StateTitle, ZipcodeTitle, methodName+"_verifyUpdateTitles_test_case_no"+testCaseNo);
    	verifyUpdateProfileLinks(currentPageTitle, homePageTitle, main, methodName+"_verifyUpdateProfileLinks_test_case_no"+testCaseNo);
	}
    UpdateProfile (driver, Username, Password, LastName, FirstName, UTAID, Phone, Email, StreetNumber, StreetName,
    		City, State, Zipcode, methodName+"UpdateProfile_test_case_no"+testCaseNo);
    Thread.sleep(2_500);
    
    if (!errMsg.equals("")) {
    	verifyUpdateErrorMessages(driver, errMsg, Usernameerr, Passworderr, LastNameerr,
    		FirstNameerr, UTAIDerr, Phoneerr, Emailerr, StreetNumbererr, StreetNameerr,
    		Cityerr, Stateerr, Zipcodeerr, methodName+"_verifyUpdateErrorMessages_test_case_no"+testCaseNo);
    
    	Logout("UpdateProfile_Btn_Logout");
    }
    else {
    	//Verify the update was successful by checking title on next page
    	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffHome_Title"))).getText().equals(homePageTitle));
    	takeScreenshot(driver, methodName + "_verifySuccessCatererStaffUpdateProfile_test_case_no"+testCaseNo);
    
    	
    	Logout("CatererStaffHome_Btn_Logout");
    }
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


