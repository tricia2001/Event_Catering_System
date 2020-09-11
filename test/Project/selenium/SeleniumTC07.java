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
public class SeleniumTC07 extends Project_Functions {
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
	username = prop.getProperty("username4");
	password = prop.getProperty("password4");
	
//	Load Shared UI Map
	prop.load(new FileInputStream(sSharedUIMapPath));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	}
 
  @Test
  @FileParameters("Excel/TC07a.csv")
  public void TC07a(int testCaseNo, String title1, String title2,
		  String button1, String button2, String Blogout, String title3, String Bsubmit, String lname,String title4,
		  String title5, String title6,String title7, String error,String title8,String title9, String title10, 
		  String title11,String title12, String errormsg, String error1) throws Exception { 
  
	  String methodName= new Throwable().getStackTrace()[0].getMethodName();
		
		driver.get(sAppURL);
		driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		Login(driver, username,password, "none");
		
		//Admin Homepage Tests
		if (!title1.equals("")) {
			verifyAdminHomepageTitles(driver,title1,title2,methodName+"_verifyAdminHomepageTitles_test_case_no"+testCaseNo);
			verifyAdminHompageLinks(title2,button1,title3,title4,methodName+"_verifyAdminHomepageTitles_test_case_no"+testCaseNo);
		}
		driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
		Thread.sleep(500);
		
		//Search User Page Tests
		if (!title1.equals("")) {
			verifySearchUserTitles(driver,title1, title3, methodName+"_verifySearchUserTitles_test_case_no"+testCaseNo);
			verifySearchUserLinks(title3,title4,title5, methodName+"_verifySearchUserLinks_test_case_no"+testCaseNo);
		}
		searchUser(lname, methodName+"_searchUser_test_case_no"+testCaseNo);
		
		if (!errormsg.equals("")) {
			verifySearchUserErrors(errormsg,error1, methodName+"_verifySearchUserErrors_test_case_no"+testCaseNo);
			
			driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Btn_Logout"))).click();
		}
		else {
		//NO RADIO BUTTON SELECTION
		 driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_err"))).getAttribute("value").equals(error));
		 takeScreenshot(driver,methodName+"_VerifySelectionError_test_case_no"+testCaseNo);
		 
		 
		//ListUser Tests
		verifyListUserData("ListUser_UserTable",lname, methodName+"_verifyEventListData_test_case_no"+testCaseNo);

		verifyListUserLinks(title5,title6,title7,title2,title4);
		verifyListUserTitles(title5,title8,title9,title10,title11,title12);
		verifyRadioButtonsUsearch("ListUser_Table", title6, methodName+"_verifyRadioButtonsUsearch_test_case_no"+testCaseNo);
		
		Logout("ListUser_Btn_Logout");
		}
	  }
  
  @Test
  @FileParameters("Excel/TC07b.csv")
  public void TC07b(int testCaseNo, int radio, String title1, String title2,String button1, String button2, 
		  String Blogout, String title3, String Bsubmit, String lname,String title4,
		  String title5, String title6,String title7, String error,String t1,
		  String t2,String t3,String t4,String t5,String t6,
		  String t7,String t8,String t9,String t10,String t11,
		  String t12,String t13,String title13) throws Exception { 

	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	
	driver.get(sAppURL);
	
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	
	driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
	Thread.sleep(500);
	
	searchUser(lname, methodName+"_searchUser_test_case_no"+testCaseNo);	 
	driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
	    		prop.getProperty("ListUser_UserTableRadioCol"))).click();
	
	//Get username of row that will be selected
	String uname=driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
    		prop.getProperty("ListUser_UserTableUSernameCol"))).getText();
	
	driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
	
	//verifying usersearch results links and titles
	if (!title13.equals("")) {
		verifySearchUserResultsTitles(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,title13, methodName+"_verifySearchUserResultsTitles_test_case_no_"+testCaseNo);
		verifySearchUserResultsLinks(title6,title4,title2,title13,methodName+"_verifySearchUserResultsTitles_test_case_no_"+testCaseNo);
	}
	takeScreenshot(driver,methodName+"_SearchUserResults_test_case_no"+testCaseNo);
	 
	//View Selected User Page Tests
	//Verifying  from the database all the details of a user by it's username. 
	verifyUser(uname, methodName+"_verifyUser_test_case_no_"+testCaseNo);	
	Logout("UserSearchResults_Btn_Logout");
	
  }
  
  // Delete a User - Admin
  @Test
  @FileParameters("Excel/TC07c.csv")
  public void TC07c(int testCaseNo, String lname, int radio, String accept, String uname, String userLast, 
		  String userFirst, String role, String Password, String UtaID, String phoneNum, String Email,
		  String streetNumber, String streetName, String city, String state, String zipCode) throws Exception {
	  	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	
		driver.get(sAppURL);
		
		  driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
		  Login(driver,username, password, "none");
		  
		  driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
		  Thread.sleep(500);
			
		  searchUser(lname, methodName+"_searchUser_test_case_no"+testCaseNo);	
			
		  //Delete User Test (Delete User does not have its own page, so no checking titles or links)
		  AdminDeleteUser(driver, radio,methodName+"_AdminDeleteUser_test_case_no"+testCaseNo, accept);
		  
		  verifyDeleteUser(driver,radio,accept, methodName+"_verifyDeleteUser_test_case_no"+testCaseNo);
		  
		  if (accept.equals("yes")) {
			  //Replace deleted user in DB
			  Logout("ListUser_Btn_Logout");
			  driver.findElement(By.xpath(prop.getProperty("Index_Btn_Register"))).click();
			  Thread.sleep(500);
			  Register(driver,testCaseNo,uname,userLast,userFirst,role,Password,UtaID,phoneNum,Email,streetNumber,
		    		streetName,city,state,zipCode,methodName+"_Register_test_case_no"+testCaseNo);
		  	  driver.findElement(By.xpath(prop.getProperty("Index_Btn_Register"))).click();
		  	  Thread.sleep(500);
		  }
		  else 
			  Logout("ListUser_Btn_Logout");
  }

  @Test
  @FileParameters("Excel/TC07d.csv")
  public void TC07d(int testCaseNo, int radio,String lname,String Mpopup,String username1,
			String userLast, String userFirst, String role, String Password,String UtaID, String phoneNum, String Email,
			String streetNumber, String streetName, String city, String state, String zipCode,String errorMsg, String UsernameError,
			String LastError, String FirstError,String PasswordError, String RoleError, String UtaIdError, String EmailError,
			String PhoneError, String StreetNoError, String StreetNameError, String CityError,String StateError, String ZipError) throws Exception { 

	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	
	driver.get(sAppURL);
	
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	
	driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
	Thread.sleep(500);
	
	searchUser(lname, methodName+"_searchUser_test_case_no"+testCaseNo);	 
	driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
	    		prop.getProperty("ListUser_UserTableRadioCol"))).click();
	
	//Get username of row that will be selected
	String uname=driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
    		prop.getProperty("ListUser_UserTableUSernameCol"))).getText();
	
	driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
	
	driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Btn_ModifyUser"))).click();
	
	//Modify User Page error message tests
	modifyUser(driver,username1,userLast,userFirst,role,Password,UtaID,phoneNum,Email,streetNumber,
    		streetName,city,state,zipCode, Mpopup,"yes",methodName+"_ModifyUser_test_case_no"+testCaseNo);
    verifyModifyUserErrorMessages(driver,errorMsg,UsernameError, LastError,FirstError,PasswordError,
    		RoleError,UtaIdError, EmailError,PhoneError, StreetNoError, StreetNameError, CityError,
    		StateError,ZipError,methodName+"_verifyModifyUserErrorMsgs_test_case_no"+testCaseNo);
    
	Logout("ModifyUser_Btn_Logout");
	
  }

  
  @Test
  @FileParameters("Excel/TC07e.csv")
  public void TC07e(int testCaseNo, int radio,String title13,String main, String m1,String m2,String m3,String m4,String m5,
		  String m6,String m7,String m8,String m9,String m10,String m11,String m12,String m13,String Mpopup,
		  String lname,String username1,String userLast, String userFirst, String role, String Password,String UtaID, String phoneNum, String Email,
		  String streetNumber, String streetName, String city, String state, String zipCode, String accept) throws Exception { 

	String methodName= new Throwable().getStackTrace()[0].getMethodName();
	
	driver.get(sAppURL);
	
	driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
	Login(driver, username,password, "none");
	
	driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
	Thread.sleep(500);
	
	searchUser(lname, methodName+"_searchUser_test_case_no"+testCaseNo);	 
	driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
	    		prop.getProperty("ListUser_UserTableRadioCol"))).click();
	
	//Get username of row that will be selected
	String uname=driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+
    		prop.getProperty("ListUser_UserTableUSernameCol"))).getText();
	
	driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
	
	driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Btn_ModifyUser"))).click();
	
	//Modify User page tests (see TC07d for error message verfication)
	verifyModifyUserTitles(title13,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,methodName+"_ModifyUser_Titles_testcase_no_"+testCaseNo);
	verifyModifyUserLinks(title13, main, methodName+"_verifyModifyUserLinks_testcase_no_"+testCaseNo);
	modifyUser(driver,username1,userLast,userFirst,role,Password,UtaID,phoneNum,Email,streetNumber,
    		streetName,city,state,zipCode, Mpopup,accept,methodName+"_ModifyUser_test_case_no"+testCaseNo);
    
    if (accept.equals("no")) {
		//Verify that valid inputs with a dismiss does not have updated last name
		assertTrue(!driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Last_Name"))).getText().equals(userLast));
        
		Logout("UserSearchResults_Btn_Logout");
	}
	else {
		//Verify that valid inputs with an accept has updated last name
		assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Last_Name"))).getText().equals(userLast));
		verifyUser(uname, methodName+"_verifyUser_test_case_no_"+testCaseNo);
		
		//Reset Database for next test
		updateUser("kilgo445","331@rRllll","Kilgo","Addie","User","1001433487","5124763455","addie.kilgo@mavs.uta.edu","103","W. Durham Dr.","Arlington","TX","76019");
			
		Logout("UserSearchResults_Btn_Logout");
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
