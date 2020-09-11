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
public class SeleniumTC02 extends Project_Functions {
	
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
  @FileParameters("Excel/TC02.csv")
  //This one is for all true Login validation
  public void TC02a(int testCaseNo, String title1, String errmsg1, String unamelabel, String username, String pwdlabel, 
		  String password, String usernameerr, String passworderr, String title2, String button2, 
		  String title3, String button3, String title4, String title5, String main, String errmsg2, String datelabel, 
		  String date, String timelabel, String time, String date_err) throws Exception {
	  
	String methodName = new Throwable().getStackTrace()[0].getMethodName();
	if (date.equals("Today"))
		date = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
	
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
	    Thread.sleep(500);
	}
	
	else {
		//Caterer Manager Homepage Tests
	    if (!title2.equals("")) {
	    	assertTrue(driver.findElement(By.xpath(prop.getProperty("MgrHomePage_Txt_Title"))).getText().equals(title2));
	    	takeScreenshot(driver, methodName + "_verifyTitleCatererManagerHomePage_test_case_no"+testCaseNo);
	    	verifyCMHomeLinks(title2,button2,title4,main, methodName+"_verifyCMHomeLinks_test_case_no"+testCaseNo);
	    }
		driver.findElement(By.xpath(prop.getProperty("MgrHomePage_Btn_View_Caterer_Event_Summary"))).click();
		
		//Caterer Manager Event Search Page Tests
		if (!title4.equals("")) {
			verifyCMSearchHeaders(title4,datelabel,timelabel, methodName+"_verifyCMSearchHeaders_test_case_no"+testCaseNo);
		
			verifyCMSearchLinks(title4,main, methodName+"_verifyCMSearchLinks_test_case_no"+testCaseNo);
		}
		catererMgrSearch(date,time, methodName+"_catererMgrSearch_test_case_no"+testCaseNo);
		
		
		if(! errmsg2.equals("") ) {
			verifyCMSearchErrors(errmsg2,date_err, methodName+"_verifyCMSearchErrors_test_case_no"+testCaseNo);
		
			Logout("CatererMgrSearch_Btn_Logout");
		}
		
	    else {
	    	//Verify the search was successful by checking title on next page
	    	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_Title"))).getText().equals(title5));
	    	takeScreenshot(driver, methodName + "_verifySuccessCatererManagerSearch_test_case_no"+testCaseNo);
	    
	    	Logout("CatererMgrEventList_Btn_Logout");
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


