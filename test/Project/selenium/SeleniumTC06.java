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
public class SeleniumTC06 extends Project_Functions {
	
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
	username = prop.getProperty("username1");
	password = prop.getProperty("password1");
	
//	Load Shared UI Map
	prop.load(new FileInputStream(sSharedUIMapPath));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  @FileParameters("Excel/TC06a.csv")
  public void TC06a(int testCaseNo, String date, String time, String duration, String hall,
		  String attendees, String eventName, String foodType, String meal,
		  String formality, String drinkType, String entertainment,
		  String error, String errorDuration, String errorAttendees,
		  String errorEventName, String title, String title1, String main, String header1, 
		  String header2, String header3, String header4, String header5, String header6, 
		  String header7, String header8, String header9, String header10, String header11, 
		  String header12, String header13, String header14)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_Request_Event"))).click();
		UserRequestEvent(date,time, methodName+"_UserRequestEvent_test_case_no"+testCaseNo);
		
		//User Request Event Inputs Page Tests
		if (!header1.equals(""))
			verifyTC06aTitles(driver,title,header1,header2,header3,header4,header5,header6,header7,header8,header9,
					header10,header11,header12,header13,header14,methodName+"_verifyTC06aTitles_test_case_no_"+testCaseNo);
		if (!title.equals(""))
			verifyRequestInputsLinks(title,title1,main,methodName+"_verifyRequestInputsLinks_test_case_no"+testCaseNo);
		
		RequestInputs(duration,hall,attendees,eventName,foodType,meal,formality,drinkType,entertainment,methodName+"_RequestInputs_test_case_no"+testCaseNo);
		if (!error.equals("")) {
			verifyRequestInputsErrorMsgs(error,errorDuration,errorAttendees,errorEventName, 
					methodName+"_verifyRequestInputsErrorMsgs_test_case_no_"+testCaseNo);
			
			Logout("RequestEventInputs_Btn_Logout");
		}
		else {
			//Verify that valid inputs leads to the reserve event page
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Title"))).getText().equals(title1));
	    	takeScreenshot(driver, methodName + "_verifySuccessRequestEventInputs_test_case_no"+testCaseNo);
			
			Logout("ReserveUserEvent_Btn_Logout");
		}
  }
 
  @Test
  @FileParameters("Excel/TC06b.csv")
  public void TC06b(int testCaseNo, String title, String errmsg, int radioBtn, String radioBtnTitle,
		  String eventNameTitle, String date, String dateTitle, String time,
		  String startTimeTitle, String durationTitle, String hallNameTitle,
		  String lastNameTitle, String firstNameTitle, String estimatedAttendeesTitle,
		  String nextPageTitle, String main)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
	    driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_Events"))).click();
	    ViewUserEvent(date,time, methodName+"_ViewUserEvent_test_case_no"+testCaseNo);
	    
	    //User Event List Tests
	    verifyTC06bTitles(driver, title, radioBtnTitle, eventNameTitle, dateTitle, startTimeTitle, durationTitle, hallNameTitle, lastNameTitle, firstNameTitle, estimatedAttendeesTitle, methodName+"_verifyTC06aTitles_test_case_no"+testCaseNo);
	    
	    verifyUserEventListData("UserEventSummary_Event_Table",username,time,date, methodName+"_verifyUserEventListData_test_case_no"+testCaseNo);
	    
	    verifyUEListLinks(errmsg,title,main,methodName+"_verifyUEListLinks_test_case_no"+testCaseNo);
	    
	    verifyRadioButtonsUE("UserEventSummary_Event_Table", nextPageTitle, methodName+"_verifyRadioButtonsUE_test_case_no"+testCaseNo);
	   
	    Logout("UserEventSummary_Btn_Logout");
  }
 
  @Test
  @FileParameters("Excel/TC06c.csv")
  public void TC06c(int testCaseNo, String title, int radioBtn, String LastNameTitle,
		  String FirstNameTitle, String date, String dateTitle, String time,
		  String startTimeTitle, String durationTitle, String hallNameTitle, 
		  String estimatedAttendeesTitle, String eventNameTitle, String foodTypeTitle,
		  String mealTitle, String mealFormalityTitle, String drinkTypeTitle,
		  String entertainmentTitle, String statusTitle, int eventID, String prevPageTitle,
		  String main)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
	    driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_Events"))).click();
	    ViewUserEvent(date,time, methodName+"_ViewUserEvent_test_case_no"+testCaseNo);
	    
		selectEventUE(radioBtn,methodName+"_selectEventUE_test_case_no"+testCaseNo);

	    //User Selected Event Page Tests
		verifyTC06cTitles(driver, title, LastNameTitle, FirstNameTitle, dateTitle, startTimeTitle,
				durationTitle, hallNameTitle, estimatedAttendeesTitle, eventNameTitle,
				foodTypeTitle, mealTitle, mealFormalityTitle, drinkTypeTitle,
				entertainmentTitle, statusTitle, methodName+"_verifyTC06bTitles_test_case_no"+testCaseNo);
	    
		verifyTC06cData(driver, 29, methodName+"_verifyTC06bData_test_case_no"+testCaseNo);
	    
		verifyUESelectedLinks(title, prevPageTitle, main, methodName+"_verifyUESelectedLinks_test_case_no"+testCaseNo);
	    	   
	    Logout("UserSelectedEvent_Btn_Logout");
  }
 
  // Reserve User Event with CC details 
  @Test
  @FileParameters("Excel/TC06d.csv")
  public void TC06d(int testCaseNo, String date, String time, String duration, String hall,
		  String attendees, String eventName, String foodType, String meal,
		  String formality, String drinkType, String entertainment,
		  String title, String title1, String main, String costTitle, String title2, String title3, String title4, String estimatedCost, 
		  String ccno, String ccexp, String ccpin, String errmsg, String cardnoerr, String cardexperr, String cardpinerr)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_Request_Event"))).click();
		UserRequestEvent(date,time, methodName+"_UserRequestEvent_test_case_no"+testCaseNo);

		RequestInputs(duration,hall,attendees,eventName,foodType,meal,formality,drinkType,entertainment,methodName+"_RequestInputs_test_case_no"+testCaseNo);
		
		assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Title"))).getText().equals(title1));
		
		//User Reserve Event Payment Page tests starts here:
		if (!main.equals("")) {
			verifyReserveUserEventLinks(title1, main, methodName+"_verifyReserveUserEventLinks_test_case_no"+testCaseNo);
			verifyReserveUserEventHeaders(costTitle,title2, title3, title4, methodName+"_verifyReserveUserEventTitles_test_case_no"+testCaseNo);
		}
		//Verify estimated cost:
		assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Estimated_Cost"))).getText().equals(estimatedCost));
    	takeScreenshot(driver, methodName + "_verifyEstimatedCost_test_case_no"+testCaseNo);
    	
	    ReserveEvent(driver, ccno, ccexp, ccpin,methodName+"_UserReserveEvent_test_case_no"+testCaseNo);
		
		if (!errmsg.equals("")) {
			verifyReserveUserEventErrors(errmsg, cardnoerr, cardexperr, cardpinerr, 
					methodName+"_verifyReserveUserEventErrorMsgs_test_case_no_"+testCaseNo);
			Logout("ReserveUserEvent_Btn_Logout");
		}
		else {
			//Verify that valid inputs leads to the selected event page
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Title"))).getText().equals(title));
	    	takeScreenshot(driver, methodName + "_verifySuccessReserveEvent_test_case_no"+testCaseNo);
	    	
			Logout("UserSelectedEvent_Btn_Logout");
		}
  }
  
 
  @Test
  @FileParameters("Excel/TC06e.csv")
  public void TC06e(int testCaseNo, String date, String time, String duration,
		  String attendee, String eventName, String hall,String foodType, String meal,
		  String formality, String drinkType, String entertainment,
		  String error, String errorDate, String errorTime,String errorDuration, String errorAttendees,
		  String errorEventName, String inputDate, String inputTime)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	if (date.equals("today"))
			date = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
		
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_Events"))).click();
		UserViewEvent(inputDate,inputTime, methodName+"_UserViewEvent_test_case_no"+testCaseNo);	
		driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Btn_Submit"))).click();
		
		//Radio col clicking
		driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Prefix_Address_Event_Table")+(2)+
	    		prop.getProperty("UserEventSummary_Radio_Col"))).click();
		
		driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Btn_View_Selected"))).click();
		driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Btn_Modify_Event"))).click();
		
		//User Modify Selected Event Error Message tests
		inputModifyEvent(date,time,duration,hall,attendee,eventName,foodType,meal,formality, drinkType,entertainment,"yes",methodName+"_inputModifyEvent_test_case_no"+testCaseNo);
		verifyModifyEventErrorMessages(error, errorDate, errorTime,errorDuration,errorAttendees,errorEventName,methodName+"_inputModifyEvent_test_case_no"+testCaseNo);
		
		Logout("ReserveUserEvent_Btn_Logout");
  }

  @Test
  @FileParameters("Excel/TC06f.csv")
  public void TC06f(int testCaseNo, String date, String time, String duration,
		  String attendee, String eventName, String hall,String foodType, String meal,
		  String formality, String drinkType, String entertainment,String inputDate, String inputTime,
		  String MTitle, String main, String LnameT,String FnameT, String DateT, String TimeT, String DurationT, String HallT, 
		  String EstimatedT, String EventT, String FoodT, String MealT, String MealFT,String DringT,String EntertainmentT, 
		  String EventST, String popUp)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();

		String username1="cityd3";
		String password1="23Mn%)fkj";
		
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username1,password1, "none");
				
		driver.findElement(By.xpath(prop.getProperty("UserHomePage_Btn_View_My_Events"))).click();
		UserViewEvent(inputDate,inputTime, methodName+"_UserViewEvent_test_case_no"+testCaseNo);	
		driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Btn_Submit"))).click();
		
		//Radio col clicking
		driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Prefix_Address_Event_Table")+(2)+
	    		prop.getProperty("UserEventSummary_Radio_Col"))).click();
		
		driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Btn_View_Selected"))).click();
		driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Btn_Modify_Event"))).click();
		
		//User Modify Selected Event page tests (see TC06e for error message verification)
		verifyTC06eTitles(driver, MTitle,  LnameT, FnameT,  DateT,  TimeT,  DurationT,  HallT,EstimatedT,  EventT,  FoodT,  MealT,  MealFT, DringT, EntertainmentT, EventST,methodName+"Modify_User_Event_testCase_"+testCaseNo);
		
		verifyModifyUserEventLinks(MTitle,main,methodName+"_verifyModifyUserEventLings_test_case_no"+testCaseNo);
		inputModifyEvent(date,time,duration,hall,attendee,eventName,foodType,meal,formality, drinkType,entertainment,popUp,methodName+"_inputModifyEvent_test_case_no"+testCaseNo);
		
		if (popUp.equals("no")) {
    		//Verify that valid inputs with a dismiss does not have updated event name
    		assertTrue(!driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Event_Name"))).getText().equals(eventName));
            
			Logout("UserSelectedEvent_Btn_Logout");
    	}
    	else {
    		//Verify that valid inputs with an accept has updated event name
    		assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Event_Name"))).getText().equals(eventName));
			
    		//Reset event for next test
    		updateUserEvent(9, "cityd3", "Carter", "Stephanie", "2020-06-05", "17:00", "2", "Maverick",
    				  "100", "Refreshments", "American", "Breakfast","formal", "alcohol", "music", "reserved");
    				
    		Logout("UserSelectedEvent_Btn_Logout");
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
