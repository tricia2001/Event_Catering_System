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
public class SeleniumTC03 extends Project_Functions {
	
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
	username = prop.getProperty("username2");
	password = prop.getProperty("password2");
	
	
//	Load Shared UI Map
	prop.load(new FileInputStream(sSharedUIMapPath));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
/*
  @Test
  @FileParameters("Excel/TC03a.csv")
  public void TC03a(int testCaseNo, String date, String time, int radioButton, String title, String title1, String title2, 
		  String title3, String title4, String title5, String title6, String title7, String title8, String title9, String title10, 
		  String title11, String title12, String title13, String title14, String title15, String title16, int eventId, String button1,
		  String button2, String button3, String button4, String title21, String title22, String title23, String title24, String title25,
		  String title26, String title27, String title28, String title29, String error) throws Exception {
	  
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
    Login(driver, username,password, "none");
    
    driver.findElement(By.xpath(prop.getProperty("MgrHomePage_Btn_View_Caterer_Event_Summary"))).click();
    Thread.sleep(500);
    catererMgrSearch(date,time, methodName+"_catererMgrSearch_test_case_no"+testCaseNo);
	
	//Test for "Caterer Manager Caterer Event Summary Page" begins here: 
    verifyTC03aHeaders(driver, button3, title21, title22, title23, title24, title25, title26, title27, title28, title29, 
    		methodName+"_verifyTC03aHeaders_test_case_no"+testCaseNo);
    verifyCMListLinks(error,button3, button4, methodName+"_verifyCMListLinks_test_case_no"+testCaseNo);
    
    verifyEventListData("CatererMgrEventList_Event_Table",time,date, methodName+"_verifyEventListData_test_case_no"+testCaseNo);
    
    verifyRadioButtonsCM("CatererMgrEventList_Event_Table", title, methodName+"_verifyRadioButtonsCM_test_case_no"+testCaseNo);
    
    selectEvent(radioButton,methodName+"_selectEvent_test_case_no"+testCaseNo);
    
    //Test for "Caterer Manager View Selected Caterer Event" begins here:
    verifyTC03aTitles(driver, title, title1, title2, title3, title4, title5, title6, title7, title8, title9, title10, 
    		title11, title12, title13, title14, title15, title16,methodName+"_verifyTC03aTitles_test_case_no"+testCaseNo);
    verifyCMSelectedLinks(title,button1,button2,button3,button4,methodName+"_verifyCMSelectedLinks_test_case_no"+testCaseNo);
     
    verifyTC03aData(driver,33,methodName+"_verifyTC03aData_test_case_no"+testCaseNo);
    
    Logout("CatererMgrSelectedEvent_Btn_Logout");
    
  }
  
  @Test
  @FileParameters("Excel/TC03b.csv")
  public void TC03b(int testCaseNo, String date, String time, int radioButton, String title, String title1, String title2, 
		  int eventId, String catererLast, String catererFirst, String prevCatererLast, String prevCatererFirst, String error, 
		  String error1, String button1, String button2, String button3) throws Exception {
	  
	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	driver.get(sAppURL);
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	    
	driver.findElement(By.xpath(prop.getProperty("MgrHomePage_Btn_View_Caterer_Event_Summary"))).click();
	Thread.sleep(1_000);
    catererMgrSearch(date,time, methodName+"_catererMgrSearch_test_case_no"+testCaseNo);
	
	selectEvent(radioButton, methodName+"_selectEvent_test_case_no"+testCaseNo);

    driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Btn_Assign_Caterer"))).click();
    Thread.sleep(500);
    
    //Test for "Assign Catering Staff" begins here:
    if (!title.equals("")) {
    verifyAssignTitles(title,title1,title2,methodName+"_verifyAssignTitles_test_case_no"+testCaseNo);
    
    verifyAssignLinks(title,button2,button3,methodName+"_verifyAssignLinks_test_case_no"+testCaseNo);
    }
    assignCaterer(catererLast,catererFirst,methodName+"_assignCaterer_test_case_no"+testCaseNo);
	
	if (!error.equals("")) {
		verifyAssignErrors(error,error1,methodName+"_verifyAssignErrors_test_case_no"+testCaseNo);
		
		Logout("CatererMgrSelectedEvent_Btn_Logout");
	}
	else {
		verifyAssignment(button1,catererLast,catererFirst,methodName+"_verifyAssignment_test_case_no"+testCaseNo);
		
		//Reset database
		resetCatererinDB(prevCatererLast,prevCatererFirst);
	}
  }
*/
  @Test
  @FileParameters("Excel/TC03c.csv")
  public void TC03c (int testCaseNo, String searchDate, String searchTime, String date, String time,
		  String lastName, String firstName, int radioButton, String duration, String hall,
		  String attendees, String eventName, String foodType, String meal,
		  String mealFormality, String drinkType, String entertainment,
		  String error, String errorLastName, String errorFirstName, String errorDate, String errorStartTime,
		  String errorDuration, String errorAttendees,
		  String errorEventName, String title, String title1, String main, String header1, 
		  String header2, String header3, String header4, String header5, String header6, 
		  String header7, String header8, String header9, String header10, String header11, 
		  String header12, String header13, String header14, String header15, String header16, String popUp)
		  throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	  	
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
				
		driver.findElement(By.xpath(prop.getProperty("MgrHomePage_Btn_View_Caterer_Event_Summary"))).click();
		Thread.sleep(500);
		catererMgrSearch(searchDate,searchTime,methodName+"_catererMgrSearch_test_case_no"+testCaseNo);
		
		selectEvent(radioButton, methodName+"_selectEvent_test_case_no"+testCaseNo);

	    driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Btn_Modify_Event"))).click();
	    Thread.sleep(500);
	    
	    if (date.equals("Today"))
			date = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
	    
	    //Caterer Manager Modify Event Page Tests
	    if (!header1.equals(""))
	    	verifyTC03cTitles(driver, title, header1, header2, header3, header4, header5, header6, header7,
	    			header8, header9, header10, header11, header12, header13, header14, header15,
	    			header16, methodName+"_verifyTC03cTitles_test_case_no_"+testCaseNo);
	    
	    if (!title1.equals(""))
	    	verifyCMModifyEventLinks (title, main, methodName+"_verifyCMModifyEventLinks_test_case_no"+testCaseNo);
	
	    CMModifyEventInputs (lastName, firstName, date, time, duration, hall, attendees, eventName, foodType, meal, mealFormality,
	    		drinkType, entertainment, popUp, methodName+"_CMModifyEventInputs_test_case_no"+testCaseNo);
	    
	    if (!error.equals("")) {
			verifyCMModifyEventInputsErrorMsgs (error, errorLastName, errorFirstName, errorDate, errorStartTime,
					errorDuration, errorAttendees,errorEventName, 
					methodName+"_verifyCMModifyEventInputsErrorMsgs_test_case_no_"+testCaseNo);
			
			Logout("CatererMgrModifyEvent_Btn_Logout");
		}
	    else {
	    	if (popUp.equals("no")) {
	    		//Verify that valid inputs with a dismiss does not have updated hall name
	    		assertTrue(!driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Hall_Name"))).getText().equals(hall));
                Thread.sleep(1_000);
				Logout("CatererMgrModifyEvent_Btn_Logout");
	    	}
	    	else {
	    		//Verify that valid inputs with an accept has updated hall name
	    		assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Hall_Name"))).getText().equals(hall));
				
	    		Logout("CatererMgrSelectedEvent_Btn_Logout");
	    	}
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


