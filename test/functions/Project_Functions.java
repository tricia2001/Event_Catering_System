package functions;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Project.model.Event;
import Project.util.SQLConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Properties;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import Project.data.EventDAO;
import Project.data.UserDAO;
import Project.model.*;


public class Project_Functions {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	  public static WebDriver driver;
	  public static Properties prop;

	   public void takeScreenshot(WebDriver driver, String screenshotname) {
			  try
			  {
				  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
				  FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname +".png"));
			  }
			  catch(IOException e) {}
			  try {
				  Thread.sleep(1_000);
			} catch (InterruptedException e) {}
	   }
	   
	   public void Logout (String button) {
		    driver.findElement(By.xpath(prop.getProperty(button))).click();
			  try {
				  Thread.sleep(1_000);
			} catch (InterruptedException e) {}
	   }

	   public void Login (WebDriver driver, String sUserName, String sPassword, String snapShotName ) {
		   
		try {
			  Thread.sleep(500);
		} catch (InterruptedException e) {}
		// Provide user name.
		driver.findElement(By.xpath(prop.getProperty("Login_Txt_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Login_Txt_Username"))).sendKeys(sUserName);

		 // Provide Password.
		driver.findElement(By.xpath(prop.getProperty("Login_Txt_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Login_Txt_Password"))).sendKeys(sPassword);

		try {
			  Thread.sleep(500);
		} catch (InterruptedException e) {}
		 // Click on Login button.
		driver.findElement(By.xpath(prop.getProperty("Login_Btn_Submit"))).click();
		if (!snapShotName.equals("none"))
			takeScreenshot(driver,snapShotName);
		else
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
	  }	
	   
	   
	   public void verifyRadioButtonsCM(String table, String next_title, String snapShotName) {
		   
		   WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    
		   for (int i = 1; i < rows; i++) {
			    driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Prefix_Address_Event_Table")+(i+1)+
			    		prop.getProperty("CatererMgrEventList_Radio_Col"))).click();
			    try {
					  Thread.sleep(1_000);
				} catch (InterruptedException e) {}
			    driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Btn_View_Selected"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}

				assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Title"))).getText().equals(next_title));
			    driver.navigate().back();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
		   }
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyRadioButtonsUE(String table, String next_title, String snapShotName) {
		   
		   WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    
		   for (int i = 1; i < rows; i++) {
			    driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Prefix_Address_Event_Table")+(i+1)+
			    		prop.getProperty("UserEventSummary_Radio_Col"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
			    driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Btn_View_Selected"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
			    
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Title"))).getText().equals(next_title));
			    driver.navigate().back();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
		   }
		    takeScreenshot(driver,snapShotName);
	   }

	   public void verifyRadioButtonsCS(String table, String next_title, String snapShotName) {
		   
		   WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    
		   for (int i = 1; i < rows; i++) {
			    driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Prefix_Address_Event_Table")+(i+1)+
			    		prop.getProperty("CatererStaffEventList_Radio_Col"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
			    driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Btn_View_Selected"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
			    
				assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Title"))).getText().equals(next_title));
			    driver.navigate().back();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
		   }
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   // Login Headers Check
	   public void verifyLoginHeaders(String title1,String unamelabel,String pwdlabel, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Title"))).getText().equals(title1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Username_Label"))).getText().equals(unamelabel));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Password_Label"))).getText().equals(pwdlabel));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // Login Errors Check
	   public void verifyLoginErrors(String errmsg1, String usernameerr, String passworderr, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Err_Msg"))).getAttribute("value").equals(errmsg1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Username_Err"))).getAttribute("value").equals(usernameerr));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Login_Txt_Password_Err"))).getAttribute("value").equals(passworderr));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifySearchUserErrors(String errmsg1, String nameerr, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Err_Msg"))).getAttribute("value").equals(errmsg1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Last_Name_Err"))).getAttribute("value").equals(nameerr));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // UserHomePage Links check
	   public void verifyUserHomeLinks(String title2, String title3, String title4, String title5,  String main, String snapShotName) {
		    verifyButton(driver,title2,title3,"UserHomePage_Txt_Title","SearchUserEventSummary_Txt_Title","UserHomePage_Btn_View_My_Events");
		    verifyButton(driver,title2,title4,"UserHomePage_Txt_Title","RequestEventDates_Txt_Title","UserHomePage_Btn_Request_Event");
			verifyButton(driver,title2,title5,"UserHomePage_Txt_Title","ViewProfile_Txt_Title","UserHomePage_Btn_View_My_User_Profile");
			verifyButton(driver,title2,main,"UserHomePage_Txt_Title","Index_Txt_Title","UserHomePage_Btn_Logout");
			verifyButton(driver,title2,main,"UserHomePage_Txt_Title","Index_Txt_Title","UserHomePage_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   // User Profile Page Links Check
	   public void verifyUserProfileLinks(String title5, String title2, String title8, String main, String snapShotName) {
		    verifyButton(driver,title5,main,"ViewProfile_Txt_Title","Index_Txt_Title","ViewProfile_Lnk_Index"); 
		    verifyButton(driver,title5,title8,"ViewProfile_Txt_Title","UpdateProfile_Txt_Title","ViewProfile_Btn_Update_Profile");
		    verifyButton(driver,title5,main,"ViewProfile_Txt_Title","Index_Txt_Title","ViewProfile_Btn_Logout");
			takeScreenshot(driver,snapShotName);
	   }
	   
	// Update Profile Page Links Check
	   public void verifyUpdateProfileLinks(String title8, String main, String snapShotName) {
		    verifyButton(driver,title8,main,"UpdateProfile_Txt_Title","Index_Txt_Title","UpdateProfile_Lnk_Index"); 
		    verifyButton(driver,title8,main,"UpdateProfile_Txt_Title","Index_Txt_Title","UpdateProfile_Btn_Logout");
			takeScreenshot(driver,snapShotName);
	   }
	   // Caterer Manager Homepage Links check
	   public void verifyCMHomeLinks(String title2, String button2, String title4, String main, String snapShotName) {
		    verifyButton(driver,title2,button2,"MgrHomePage_Txt_Title","ViewProfile_Txt_Title","MgrHomePage_Btn_View_My_User_Profile");
			verifyButton(driver,title2,title4,"MgrHomePage_Txt_Title","CatererMgrSearch_Txt_Title","MgrHomePage_Btn_View_Caterer_Event_Summary");
			verifyButton(driver,title2,main,"MgrHomePage_Txt_Title","Index_Txt_Title","MgrHomePage_Btn_Logout");
			verifyButton(driver,title2,main,"MgrHomePage_Txt_Title","Index_Txt_Title","MgrHomePage_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // Search Headers - User Search, User Request and Caterer Manager 
	   public void verifyUserSearchHeaders(String title3, String datelabel, String timelabel, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Title"))).getText().equals(title3));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date_Label"))).getText().equals(datelabel));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Time_Label"))).getText().equals(timelabel));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUserProfileHeaders(String title5, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Title"))).getText().equals(title5));   
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUpdateProfileHeaders(String title8, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Title"))).getText().equals(title8));   
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyReserveUserEventHeaders(String costTitle,String title2, String title3, String title4, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Estimated_Cost_Title"))).getText().equals(costTitle));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Credit_Card_Number_Title"))).getText().equals(title2));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_Expiry_Date_Title"))).getText().equals(title3));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_PIN_Title"))).getText().equals(title4));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUserRequestEventHeaders(String title7, String datelabel2, String timelabel2, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Title"))).getText().equals(title7));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Date_Label"))).getText().equals(datelabel2));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Time_Label"))).getText().equals(timelabel2));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCMSearchHeaders(String title4, String datelabel, String timelabel, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Title"))).getText().equals(title4));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Date_Label"))).getText().equals(datelabel));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Time_Label"))).getText().equals(timelabel));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // Search Links - User Search, User Request and Caterer Manager
	   
	   public void verifyUserSearchLinks(String title3, String main, String snapShotName) {
		    verifyButton(driver,title3,main,"SearchUserEventSummary_Txt_Title","Index_Txt_Title","SearchUserEventSummary_Btn_Logout");
			verifyButton(driver,title3,main,"SearchUserEventSummary_Txt_Title","Index_Txt_Title","SearchUserEventSummary_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUserRequestEventLinks(String title7, String main, String snapShotName) {
		    verifyButton(driver,title7,main,"RequestEventDates_Txt_Title","Index_Txt_Title","RequestEventDates_Btn_Logout");
			verifyButton(driver,title7,main,"RequestEventDates_Txt_Title","Index_Txt_Title","RequestEventDates_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyReserveUserEventLinks(String title1, String main, String snapShotName) {
		    verifyButton(driver,title1,main,"ReserveUserEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Btn_Logout");
			verifyButton(driver,title1,main,"ReserveUserEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyModifyUserEventLinks(String title1, String main, String snapShotName) {
		    verifyButton(driver,title1,main,"UserModifyEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Btn_Logout");
			verifyButton(driver,title1,main,"UserModifyEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyModifyUserLinks(String title1, String main, String snapShotName) {
		    verifyButton(driver,title1,main,"UserModifyEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Btn_Logout");
			verifyButton(driver,title1,main,"UserModifyEvent_Txt_Title","Index_Txt_Title","ReserveUserEvent_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCMSearchLinks(String title4, String main, String snapShotName) {
		    verifyButton(driver,title4,main,"CatererMgrSearch_Txt_Title","Index_Txt_Title","CatererMgrSearch_Btn_Logout");
			verifyButton(driver,title4,main,"CatererMgrSearch_Txt_Title","Index_Txt_Title","CatererMgrSearch_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // Errors - User Search, User Request and Caterer Manager
	   public void verifyUserSearchErrors(String errmsg2, String date_err, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Err_Msg"))).getAttribute("value").equals(errmsg2));
		    
			assertTrue(driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date_Err"))).getAttribute("value").equals(date_err));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUserRequestEventErrors(String errmsg3, String date_err2, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Err_Msg"))).getAttribute("value").equals(errmsg3));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Date_Err"))).getAttribute("value").equals(date_err2));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyReserveUserEventErrors(String errmsg, String cardnoerr, String cardexperr, String cardpinerr, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Err_Msg"))).getAttribute("value").equals(errmsg));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Credit_Card_Number_Err"))).getAttribute("value").equals(cardnoerr));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_Expiry_Date_Err"))).getAttribute("value").equals(cardexperr));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_PIN_Err"))).getAttribute("value").equals(cardpinerr));
			takeScreenshot(driver,snapShotName);
	   }
	   public void verifyCMSearchErrors(String errmsg2, String date_err, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Err_Msg"))).getAttribute("value").equals(errmsg2));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Date_Err"))).getAttribute("value").equals(date_err));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // View User Events
	   public void ViewUserEvent(String date, String time, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date"))).sendKeys(date);
			driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Start_Time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Start_Time"))).sendKeys(time);
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			
			driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Btn_Submit"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			takeScreenshot(driver,snapShotName);
	   }	   
	   
	   // User Request New Event
	   public void UserRequestEvent(String date2, String time2, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Date"))).sendKeys(date2);
			driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Start_Time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Txt_Start_Time"))).sendKeys(time2);
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			
			driver.findElement(By.xpath(prop.getProperty("RequestEventDates_Btn_Submit"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			takeScreenshot(driver,snapShotName);
	   }
	   
	   // Caterer Manager Event Search
	   public void catererMgrSearch(String date, String time, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Date"))).sendKeys(date);
			driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Start_Time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Txt_Start_Time"))).sendKeys(time);
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			
			driver.findElement(By.xpath(prop.getProperty("CatererMgrSearch_Btn_Submit"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyEventListData(String table,String time,String date, String snapShotName) {
		    WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    assertFalse(arraysDiff(getEventListFromDB(rows,"all","caterer manager",time.substring(0,5),parseDate(date)),
		    		getTableContentsFromPage(rows,"CatererMgrEventList_Prefix_Address_Event_Table")));
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyEventListData(String table, String username, String time,String date, String snapShotName) {
		    WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    assertFalse(arraysDiff(getEventListFromDB(rows,username,"caterer",time.substring(0,5),parseDate(date)),
		    		getTableContentsFromPage(rows,"CatererStaffEventList_Prefix_Address_Event_Table")));
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUserEventListData(String table, String username, String time,String date, String snapShotName) {
		    WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    assertFalse(arraysDiff(getEventListFromDB(rows,username,"user",time.substring(0,5),parseDate(date)),
		    		getTableContentsFromPage(rows,"UserEventSummary_Prefix_Address_Event_Table")));
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCMListLinks(String error, String button3, String button4, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Btn_View_Selected"))).click();
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Err_Msg"))).getAttribute("value").equals(error));
		    verifyButton(driver, button3, button4, "CatererMgrEventList_Txt_Title", "Index_Txt_Title", "CatererMgrEventList_Btn_Logout");
		    verifyButton(driver, button3, button4, "CatererMgrEventList_Txt_Title", "Index_Txt_Title", "CatererMgrEventList_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void selectEvent(int radioButton, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Prefix_Address_Event_Table")+(radioButton+1)+
		    		prop.getProperty("CatererMgrEventList_Radio_Col"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Btn_View_Selected"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    takeScreenshot(driver,snapShotName);
	   }
	   public void selectEventCS(int radioButton, String snapShotName) {
		   driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Prefix_Address_Event_Table")+(radioButton+1)+
		    		prop.getProperty("CatererStaffEventList_Radio_Col"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Btn_View_Selected"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void selectEventUE(int radioButton, String snapShotName) {
		   driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Prefix_Address_Event_Table")+(radioButton+1)+
		    		prop.getProperty("UserEventSummary_Radio_Col"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Btn_View_Selected"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		    takeScreenshot(driver,snapShotName);
	   }
	  
	  public void verifyTC03aHeaders(WebDriver driver, String button3, String title21, String title22, String title23, String title24, String title25,
			  String title26, String title27, String title28, String title29, String snapShotName) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_Title"))).getText().equals(button3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col1_Radio_Btn"))).getText().equals(title21));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col2InListEventTableHeader"))).getText().equals(title22));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col3InListEventTableHeader"))).getText().equals(title23));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col4InListEventTableHeader"))).getText().equals(title24));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col5InListEventTableHeader"))).getText().equals(title25));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col6InListEventTableHeader"))).getText().equals(title26));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col7InListEventTableHeader"))).getText().equals(title27));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col8InListEventTableHeader"))).getText().equals(title28));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrEventList_Txt_col9InListEventTableHeader"))).getText().equals(title29));
		   takeScreenshot(driver,snapShotName);
	   }
	  
	     // User Profile Verify Titles
	     public void verifyUserProfileTitles(WebDriver driver, String title5, String upuser, String uppwd, String upln, String upfn, String uprole, 
			   String uputaid, String upphone, String upemail, String upsno, String upsname, String upcity, 
			   String upstate, String upzc, String snapShotName) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Username_Title"))).getText().equals(upuser));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Password_Title"))).getText().equals(uppwd));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Last_Name_Title"))).getText().equals(upln));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_First_Name_Title"))).getText().equals(upfn));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Role_Title"))).getText().equals(uprole));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_UTA_ID_Title"))).getText().equals(uputaid));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Phone_Title"))).getText().equals(upphone));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Email_Title"))).getText().equals(upemail));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Street_Number_Title"))).getText().equals(upsno));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Street_Name_Title"))).getText().equals(upsname));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_City_Title"))).getText().equals(upcity));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_State_Title"))).getText().equals(upstate));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Zipcode_Title"))).getText().equals(upzc));
		   takeScreenshot(driver,snapShotName);
	   }
	  
	// Update Profile Verify Titles
		  public void verifyUpdateProfileTitles(WebDriver driver, String title8, String tuser, String tpwd, String tln, String tfn, String trole, 
				   String tutaid, String tphone, String temail, String tsno, String tsname, String tcity, 
				   String tstate, String tzc, String snapShotName) {
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Title"))).getText().equals(title8));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Username_Title"))).getText().equals(tuser));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Password_Title"))).getText().equals(tpwd));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Last_Name_Title"))).getText().equals(tln));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_First_Name_Title"))).getText().equals(tfn));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Role_Title"))).getText().equals(trole));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UTA_ID_Title"))).getText().equals(tutaid));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Phone_Title"))).getText().equals(tphone));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Email_Title"))).getText().equals(temail));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Number_Title"))).getText().equals(tsno));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Name_Title"))).getText().equals(tsname));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_City_Title"))).getText().equals(tcity));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_State_Title"))).getText().equals(tstate));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Zipcode_Title"))).getText().equals(tzc));
			   takeScreenshot(driver,snapShotName);
		   }
		  
		  // Update Profile Error msgs
		  public void verifyUpdateProfileErrors(WebDriver driver, String updateerror, String newusererr, String newpwderr, String newlnerr, String newfnerr, 
				  String newutaiderr, String newphoneerr, String newemailerr, String newsnoerr, String newsnameerr, String newcityerr, 
				  String newstateerr, String newzcerr, String snapShotName) {
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Error"))).getText().equals(updateerror));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UsernameError"))).getText().equals(newusererr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_PasswordError"))).getText().equals(newpwderr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_LastError"))).getText().equals(newlnerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_FirstError"))).getText().equals(newfnerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_IdError"))).getText().equals(newutaiderr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_PhoneError"))).getText().equals(newphoneerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_EmailError"))).getText().equals(newemailerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StreetNumberError"))).getText().equals(newsnoerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StreetNameError"))).getText().equals(newsnameerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_CityError"))).getText().equals(newcityerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StateError"))).getText().equals(newstateerr));
			   assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_ZipcodeError"))).getText().equals(newzcerr));
			   takeScreenshot(driver,snapShotName);
		   }
	 
	   public void verifyTC03aTitles(WebDriver driver, String title, String title1, String title2, String title3, String title4, String title5, 
			   String title6, String title7, String title8, String title9, String title10, String title11, 
			   String title12, String title13, String title14, String title15, String title16, String snapShotName) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Hall_Name_Title"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Status_Title"))).getText().equals(title14));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_Last_Title"))).getText().equals(title15));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_First_Title"))).getText().equals(title16));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   // User Profile Verify Data
	   public void verifyUserProfileData(WebDriver driver, String  username, String snapShotName) {
		   String [] user = nameSearch(username);
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Username"))).getText().equals(user[0]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Password"))).getText().equals(user[1]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Last_Name"))).getText().equals(user[2]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_First_Name"))).getText().equals(user[3]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Role"))).getText().equals(user[4]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_UTA_ID"))).getText().equals(user[5]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Phone"))).getText().equals(user[6]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Email"))).getText().equals(user[7]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Street_Number"))).getText().equals(user[8]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Street_Name"))).getText().equals(user[9]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_City"))).getText().equals(user[10]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_State"))).getText().equals(user[11]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("ViewProfile_Txt_Zipcode"))).getText().equals(user[12]));
		   takeScreenshot(driver,snapShotName);
	   }
	   
		public void UpdateProfile (WebDriver driver, String newname, String newpwd,String newln, String newfn, 
			   		String newutaid, String newphone,String newemail, String newsno, String newsname, 
			   		String newcity, String newstate, String newzc,String snapShotName) throws InterruptedException {
			driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Username"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Username"))).sendKeys(newname);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Password"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Password"))).sendKeys(newpwd);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_First_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_First_Name"))).sendKeys(newfn);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Last_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Last_Name"))).sendKeys(newln);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UTA_ID"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UTA_ID"))).sendKeys(newutaid);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Phone"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Phone"))).sendKeys(newphone);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Email"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Email"))).sendKeys(newemail);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Number"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Number"))).sendKeys(newsno);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Name"))).sendKeys(newsname);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_City"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_City"))).sendKeys(newcity);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_State"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_State"))).sendKeys(newstate);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Zipcode"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Zipcode"))).sendKeys(newzc);
		    driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Btn_Save"))).click();
		    Thread.sleep(1_000);
		    Alert javascriptAlert = driver.switchTo().alert();
			assertEquals("Are you sure you want to Update?",
					javascriptAlert.getText());
			javascriptAlert.accept();
		    //Thread.sleep(1_000);
		    //Thread.sleep(1_000);
		    takeScreenshot(driver,snapShotName);
	  }
	   
	   public void ReserveEvent (WebDriver driver, String ccno, String ccexp, String ccpin, String snapShotName) throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Credit_Card_Number"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Credit_Card_Number"))).sendKeys(ccno);
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_Expiry_Date"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_Expiry_Date"))).sendKeys(ccexp);
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_PIN"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Txt_Card_PIN"))).sendKeys(ccpin);
	    driver.findElement(By.xpath(prop.getProperty("ReserveUserEvent_Btn_Reserve"))).click();
	    Thread.sleep(1_000);
	    Alert javascriptAlert = driver.switchTo().alert();
		assertEquals("Are you sure you want to Reserve?",
				javascriptAlert.getText());
		javascriptAlert.accept();
	    //Thread.sleep(1_000);
	    //Thread.sleep(1_000);
	    takeScreenshot(driver,snapShotName);
 }
	   public String []  nameSearch(String username) {
			String query = "select * from catering.system_user where username='"+username+"'";
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			String[] user = new String[13];
			try {
				stmt = conn.createStatement();
				ResultSet userList = stmt.executeQuery(query);
				while (userList.next()) {
					user[0] = userList.getString("username");
					user[1] = userList.getString("password");
					user[2] = userList.getString("last_name");
					user[3] = userList.getString("first_name");
					user[4] = userList.getString("role");
					user[5] = userList.getString("UTA_ID");
					user[6] = userList.getString("phone");
					user[7] = userList.getString("email");
					user[8] = userList.getString("street_number");
					user[9] = userList.getString("street_name");
					user[10] = userList.getString("city");
					user[11] = userList.getString("state");
					user[12] = userList.getString("zipcode");
				}
			} catch (SQLException e) {}
			return user;
			
		}
	   
	   
	   public void verifyTC03aData(WebDriver driver, int eventId, String snapShotName) {
		   String [] event = idSearch(eventId);
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Last_Name"))).getText().equals(event[0]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_First_Name"))).getText().equals(event[1]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Date"))).getText().equals(event[2]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Start_Time"))).getText().equals(event[3]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Duration"))).getText().equals(event[4]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Hall_Name"))).getText().equals(event[5]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Attendees"))).getText().equals(event[6]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Event_Name"))).getText().equals(event[7]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Food_Type"))).getText().equals(event[8]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Meal"))).getText().equals(event[9]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Formality"))).getText().equals(event[10]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Drink_Type"))).getText().equals(event[11]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Entertainment"))).getText().equals(event[12]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Status"))).getText().equals(event[13]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_Last"))).getText().equals(event[14]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_First"))).getText().equals(event[15]));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCMSelectedLinks(String title,String button1,String button2,String button3,String button4, String snapShotName) {
		    verifyButton(driver, title, button1, "CatererMgrSelectedEvent_Txt_Title", "CatererMgrModifyEvent_Txt_Title", "CatererMgrSelectedEvent_Btn_Modify_Event");
		    verifyButton(driver, title, button2, "CatererMgrSelectedEvent_Txt_Title", "AssignCateringStaff_Txt_Title", "CatererMgrSelectedEvent_Btn_Assign_Caterer");
		    verifyButton(driver, title, button3, "CatererMgrSelectedEvent_Txt_Title", "CatererMgrEventList_Txt_Title", "CatererMgrSelectedEvent_Btn_Back");
		    verifyButton(driver, title, button4, "CatererMgrSelectedEvent_Txt_Title", "Index_Txt_Title", "CatererMgrSelectedEvent_Btn_Logout");
		    verifyButton(driver, title, button4, "CatererMgrSelectedEvent_Txt_Title", "Index_Txt_Title", "CatererMgrSelectedEvent_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyAssignTitles(String title,String title1,String title2, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Title"))).getText().equals(title));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name_Title"))).getText().equals(title1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name_Title"))).getText().equals(title2));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCMModifyEventLinks(String title, String main,String snapShotName) {
		    verifyButton(driver, title, main, "CatererMgrModifyEvent_Txt_Title", "Index_Txt_Title", "CatererMgrModifyEvent_Btn_Logout");
		    verifyButton(driver, title, main, "CatererMgrModifyEvent_Txt_Title", "Index_Txt_Title", "CatererMgrModifyEvent_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }

	   
	   public void verifyAssignLinks(String title,String button2,String button3, String snapShotName) {
		    verifyButton(driver, title, button2, "AssignCateringStaff_Txt_Title", "CatererMgrSelectedEvent_Txt_Title", "AssignCateringStaff_Btn_Back");
		    verifyButton(driver, title, button3, "AssignCateringStaff_Txt_Title", "Index_Txt_Title", "AssignCateringStaff_Lnk_Index");
		    verifyButton(driver, title, button3, "AssignCateringStaff_Txt_Title", "Index_Txt_Title", "AssignCateringStaff_Btn_Logout");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void assignCaterer(String catererLast,String catererFirst, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name"))).clear();
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name"))).sendKeys(catererLast);
			
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name"))).clear();
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name"))).sendKeys(catererFirst);
			
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Btn_Assign"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyAssignErrors(String error,String error1, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Err_Msg"))).getAttribute("value").equals(error));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name_Err"))).getAttribute("value").equals(error1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name_Err"))).getAttribute("value").equals(error1));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyAssignment(String button1,String catererLast,String catererFirst, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Title"))).getText().equals(button1));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_Last"))).getText().equals(catererLast));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Txt_Caterer_First"))).getText().equals(catererFirst));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void resetCatererinDB(String prevCatererLast,String prevCatererFirst) {
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Btn_Assign_Caterer"))).click();
		    try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name"))).clear();
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_Last_Name"))).sendKeys(prevCatererLast);
			
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name"))).clear();
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Txt_Caterer_First_Name"))).sendKeys(prevCatererFirst);
			
			driver.findElement(By.xpath(prop.getProperty("AssignCateringStaff_Btn_Assign"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	
			
			driver.findElement(By.xpath(prop.getProperty("CatererMgrSelectedEvent_Btn_Logout"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	
	   }
	   
	   public void deleteCatererMgr(WebDriver driver,String sSharedUIMapPath) throws Exception {
			  //Load login properties
			  prop.load(new FileInputStream("./Login/Login.properties"));
			  String username = prop.getProperty("username4");
			  String password = prop.getProperty("password4");
			
			  //Load Shared UI Map
			  prop.load(new FileInputStream(sSharedUIMapPath));
			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			  driver.findElement(By.xpath(prop.getProperty("Index_Btn_Login"))).click();
			  Login(driver,username, password, "none");
			  
			  driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Btn_Search_User"))).click();
			  driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Last_Name"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Last_Name"))).sendKeys("Patel");
			  driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Btn_Submit"))).click();
			  Thread.sleep(500);
			  driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+"2"+prop.getProperty("ListUser_UserTableRadioCol"))).click();
			  Thread.sleep(500);
			  driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_DeleteUser"))).click();
			  Thread.sleep(500);
			  Alert alertPopup = driver.switchTo().alert();
			  alertPopup.accept();
			  driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_Logout"))).click();
			  Thread.sleep(1_500);
		  }
	   
	   public String []  idSearch(int id) {
		   ArrayList<Event> eventListInDB = new ArrayList<Event>();
			String query = " SELECT e.*, u.last_name, u.first_name, " +
					"c.last_name caterer_last_name, c.first_name caterer_first_name " +
					"from CATERING.EVENT e " +
					"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username " 
					+ "LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username WHERE eventID = "+id;
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			String[] event = new String[16];
			try {
				stmt = conn.createStatement();
				ResultSet eventList = stmt.executeQuery(query);
				while (eventList.next()) {
					event[0] = eventList.getString("last_name");
					event[1] = eventList.getString("first_name");
					event[2] = eventList.getString("date");
					event[3] = eventList.getString("start_time");
					event[4] = eventList.getString("duration");
					event[5] = eventList.getString("hall");
					event[6] = eventList.getString("attendees");
					event[7] = eventList.getString("event_name");
					event[8] = eventList.getString("food");
					event[9] = eventList.getString("meal");
					event[10] = eventList.getString("formality");
					event[11] = eventList.getString("drink_type");
					event[12] = eventList.getString("entertainment");
					event[13] = eventList.getString("status");
					event[14] = eventList.getString("caterer_last_name");
					event[15] = eventList.getString("caterer_first_name");
							
				}
			} catch (SQLException e) {}
			return event;
			
		}
	   
	
	   public void searchMyEvents(String date,String time, String snapShotName) {
		   
		    driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Date"))).clear();
			driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Date"))).sendKeys(date);
			driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Start_Time"))).clear();
			driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Start_Time"))).sendKeys(time);
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	
			driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Btn_Submit"))).click();
			try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}	

			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCSHomeLinks(String title11, String title12, String title13, String main, String snapShotName) {
		    verifyButton(driver, title11, title12, "CatererStaffHome_Title", "CatererStaffSearch_Title", "CatererStaffHome_Btn_View_My_Assigned_Events");
		    verifyButton(driver, title11, title13, "CatererStaffHome_Title", "ViewProfile_Txt_Title", "CatererStaffHome_Btn_View_My_User_Profile");
			verifyButton(driver,title11,main,"CatererStaffHome_Title","Index_Txt_Title","CatererStaffHome_Btn_Logout");
			verifyButton(driver,title11,main,"CatererStaffHome_Title","Index_Txt_Title","CatererStaffHome_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   public void verifyCSSearchHeaders(String title, String datelabel, String timelabel, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Title"))).getText().equals(title));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Date_Label"))).getText().equals(datelabel));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Time_Label"))).getText().equals(timelabel));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCSSearchLinks(String title, String main, String snapShotName) {
		    verifyButton(driver,title,main,"CatererStaffSearch_Title","Index_Txt_Title","CatererStaffSearch_Btn_Logout");
			verifyButton(driver,title,main,"CatererStaffSearch_Title","Index_Txt_Title","CatererStaffSearch_Lnk_Index");
			takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCSSearchErrors(String errmsg2, String date_err, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Err_Msg"))).getAttribute("value").equals(errmsg2));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSearch_Txt_Date_Err"))).getAttribute("value").equals(date_err));
			takeScreenshot(driver,snapShotName);
	   }
	   
	   

		public void verifyUEListLinks(String error, String title1, String title2, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Btn_View_Selected"))).click();
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Err_Msg"))).getAttribute("value").equals(error));
		    verifyButton(driver, title1, title2, "UserEventSummary_Txt_Title", "Index_Txt_Title", "UserEventSummary_Btn_Logout");
		    verifyButton(driver, title1, title2, "UserEventSummary_Txt_Title", "Index_Txt_Title", "UserEventSummary_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
		
		public void verifyRequestInputsLinks(String title, String title1, String main,String snapShotName) {
		    verifyButton(driver, title, main, "RequestEventInputs_Txt_Title", "Index_Txt_Title", "RequestEventInputs_Btn_Logout");
		    verifyButton(driver, title, main, "RequestEventInputs_Txt_Title", "Index_Txt_Title", "RequestEventInputs_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUESelectedLinks(String title,String button1,String button2, String snapShotName) {
		    verifyButton(driver, title, button1, "UserSelectedEvent_Title", "UserEventSummary_Txt_Title", "UserSelectedEvent_Btn_Back");
		    verifyButton(driver, title, button2, "UserSelectedEvent_Title", "Index_Txt_Title", "UserSelectedEvent_Btn_Logout");
		    verifyButton(driver, title, button2, "UserSelectedEvent_Title", "Index_Txt_Title", "UserSelectedEvent_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   public void verifyCSListLinks(String error, String title1, String title2,String title_home, String snapShotName) {
		    driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Btn_View_Selected"))).click();
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Err_Msg"))).getAttribute("value").equals(error));
		    verifyButton(driver, title1, title2, "CatererStaffEventList_Txt_Title", "Index_Txt_Title", "CatererStaffEventList_Btn_Logout");
		    verifyButton(driver, title1, title2, "CatererStaffEventList_Txt_Title", "Index_Txt_Title", "CatererStaffEventList_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyCSSelectedLinks(String title,String button1,String button2, String snapShotName) {
		    verifyButton(driver, title, button1, "CatererStaffSelectedEvent_Txt_Title", "CatererStaffEventList_Txt_Title", "CatererStaffSelectedEvent_Btn_Back");
		    verifyButton(driver, title, button2, "CatererStaffSelectedEvent_Txt_Title", "Index_Txt_Title", "CatererStaffSelectedEvent_Btn_Logout");
		    verifyButton(driver, title, button2, "CatererStaffSelectedEvent_Txt_Title", "Index_Txt_Title", "CatererStaffSelectedEvent_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyUpdateProfileLinks(String button1, String button2, String button3, String snapShotName) {
		    verifyButton(driver, button1, button3, "UpdateProfile_Txt_Title", "Index_Txt_Title", "UpdateProfile_Btn_Logout");
		    verifyButton(driver, button1, button3, "UpdateProfile_Txt_Title", "Index_Txt_Title", "UpdateProfile_Lnk_Index");
		    takeScreenshot(driver,snapShotName);
	   }
	   public void verifyTC06aTitles(WebDriver driver, String title, String title1, String title2,String title3, String title4, 
			   String title5, String title6, String title7, String title8, String title9, String title10, 
			   String title11, String title12, String title13, String title14, String snapShotName) {
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Hall_Name_Title"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Status_Title"))).getText().equals(title14));
		   takeScreenshot(driver,snapShotName);
	   }
		public void verifyTC06bTitles(WebDriver driver, String title, String title1, String title2,
			   String title3, String title4, String title5, 
			   String title6, String title7, String title8, String title9, String snapShotName) {
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col1_Radio_Btn"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col2InListEventTableHeader"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col3InListEventTableHeader"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col4InListEventTableHeader"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col5InListEventTableHeader"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col6InListEventTableHeader"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col7InListEventTableHeader"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col8InListEventTableHeader"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserEventSummary_Txt_col9InListEventTableHeader"))).getText().equals(title9));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyTC06bData(WebDriver driver, int eventId, String snapShotName) {
		   String [] event = idSearch(eventId); 
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Last_Name"))).getText().equals(event[0]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_First_Name"))).getText().equals(event[1]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Date"))).getText().equals(event[2]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Start_Time"))).getText().equals(event[3]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Duration"))).getText().equals(event[4]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Hall_Name"))).getText().equals(event[5]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Attendees"))).getText().equals(event[6]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Event_Name"))).getText().equals(event[7]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Food_Type"))).getText().equals(event[8]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Meal"))).getText().equals(event[9]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Formality"))).getText().equals(event[10]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Drink_Type"))).getText().equals(event[11]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Entertainment"))).getText().equals(event[12]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Status"))).getText().equals(event[13]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_Last"))).getText().equals(event[14]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_First"))).getText().equals(event[15]));
		   takeScreenshot(driver,snapShotName);
	   }
	   public void verifyTC04aTitles(WebDriver driver, String title, String title1, String title2, String title3, String title4, String title5, 
			   String title6, String title7, String title8, String snapShotName) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col1InListEventTableHeader"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col2InListEventTableHeader"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col3InListEventTableHeader"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col4InListEventTableHeader"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col5InListEventTableHeader"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col6InListEventTableHeader"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col7InListEventTableHeader"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffEventList_Txt_col8InListEventTableHeader"))).getText().equals(title8));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyTC04bData(WebDriver driver, int eventId, String snapShotName) {
		   String [] event = idSearch(eventId); 
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Last_Name"))).getText().equals(event[0]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_First_Name"))).getText().equals(event[1]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Date"))).getText().equals(event[2]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Start_Time"))).getText().equals(event[3]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Duration"))).getText().equals(event[4]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Hall_Name"))).getText().equals(event[5]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Attendees"))).getText().equals(event[6]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Event_Name"))).getText().equals(event[7]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Food_Type"))).getText().equals(event[8]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Meal"))).getText().equals(event[9]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Formality"))).getText().equals(event[10]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Drink_Type"))).getText().equals(event[11]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Entertainment"))).getText().equals(event[12]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Status"))).getText().equals(event[13]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_Last"))).getText().equals(event[14]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_First"))).getText().equals(event[15]));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyTC04bTitles(WebDriver driver, String title, String title1, String title2, String title3, String title4, String title5, 
			   String title6, String title7, String title8, String title9, String title10, String title11, 
			   String title12, String title13, String title14, String title15, String title16, String snapShotName
			   ) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Hall_Nam_Titlee"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Status_Title"))).getText().equals(title14));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_Last_Title"))).getText().equals(title15));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererStaffSelectedEvent_Txt_Caterer_First_Title"))).getText().equals(title16));
		   takeScreenshot(driver,snapShotName);
	   }

		public void verifyTC06cTitles(WebDriver driver, String title, String title1, String title2, String title3, String title4, String title5, 
			   String title6, String title7, String title8, String title9, String title10, String title11, 
			   String title12, String title13, String title14, String snapShotName
			   ) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Hall_Name_Title"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Status_Title"))).getText().equals(title14));
		   takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyTC06cData(WebDriver driver, int eventId, String snapShotName) {
		   String [] event = idSearch(eventId); 
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Last_Name"))).getText().equals(event[0]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_First_Name"))).getText().equals(event[1]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Date"))).getText().equals(event[2]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Start_Time"))).getText().equals(event[3]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Duration"))).getText().equals(event[4]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Hall_Name"))).getText().equals(event[5]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Attendees"))).getText().equals(event[6]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Event_Name"))).getText().equals(event[7]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Food_Type"))).getText().equals(event[8]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Meal"))).getText().equals(event[9]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Formality"))).getText().equals(event[10]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Drink_Type"))).getText().equals(event[11]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Entertainment"))).getText().equals(event[12]));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSelectedEvent_Txt_Status"))).getText().equals(event[13]));
		   takeScreenshot(driver,snapShotName);
	   }
	   public String [][]  getEventListFromDB(int listSize, String username, String role, String time, String date) {
			String query = "";
			int cols = 0;
		   if (!username.equals("all") && role.equals("caterer")) {
				query = " SELECT e.*, u.last_name, u.first_name, " +
					"c.last_name caterer_last_name, c.first_name caterer_first_name " +
					"from CATERING.EVENT e " +
					"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username " 
					+ "LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') "
					+ "AND start_time >= '"+time+"' AND caterer_username = '"+username+"' AND status = 'reserved' ORDER BY date, start_time";
				cols = 7;
			}
		    else if (!username.equals("all") && role.equals("user")) {
		    	query = " SELECT e.*, u.last_name, u.first_name " +
						"from CATERING.EVENT e " +
						"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username  WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') "
						+ "AND start_time >= '"+time+"' AND status = 'reserved' AND e.username = '"+username+"' ORDER BY date, start_time";
				cols = 8;
		    }
			else {
				query = " SELECT e.*, u.last_name, u.first_name " +
						"from CATERING.EVENT e " +
						"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username  WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') "
						+ "AND start_time >= '"+time+"' AND status = 'reserved' ORDER BY date, start_time";
				cols = 8;
			}
				
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			String[][] event = new String [listSize-1][cols];
			try {
				stmt = conn.createStatement();
				ResultSet eventList = stmt.executeQuery(query);
				int i=0;
				
				while (eventList.next()) {

					event[i][0]=eventList.getString("event_name");
					event[i][1]=eventList.getString("date");
					event[i][2]=eventList.getString("start_time");
					event[i][3]=eventList.getString("duration");
					event[i][4]=eventList.getString("hall");
					event[i][5]=eventList.getString("last_name");
					event[i][6]=eventList.getString("first_name");

					if (username.equals("all") || role.equals("user")) 
						event[i][7]=eventList.getString("attendees");
					i++;		
				}
			} catch (SQLException e) {}
			
			return event;
			
		}
	 /*  public void UpdateProfile (WebDriver driver, String username, String Password,
				String userLast, String userFirst, 
				String UtaID, String phoneNum, String Email,String streetNumber, String streetName,
				String city, String state, String zipCode,String owner,String snapShotName) throws InterruptedException {
			driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Username"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Username"))).sendKeys(username);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Password"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Password"))).sendKeys(Password);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_First_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_First_Name"))).sendKeys(userFirst);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Last_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Last_Name"))).sendKeys(userLast);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_UTA_ID"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_UTA_ID"))).sendKeys(UtaID);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Phone"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Phone"))).sendKeys(phoneNum);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Email"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Email"))).sendKeys(Email);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Street_Number"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Street_Number"))).sendKeys(streetNumber);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Street_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Street_Name"))).sendKeys(streetName);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_City"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_City"))).sendKeys(city);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_State"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_State"))).sendKeys(state);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Zipcode"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Txt_Zipcode"))).sendKeys(zipCode);
		    driver.findElement(By.xpath(prop.getProperty("UpdateStaffProfile_Btn_Save"))).click();
		    Thread.sleep(1_000);
		    Thread.sleep(1_000);
		    takeScreenshot(driver,snapShotName);
	  }
*/
	  public void verifyUpdateErrorMessages(WebDriver driver, String Error, String UsernameError, String PasswordError,
			  	String LastError, String FirstError,
			  	String UtaIdError, String PhoneError, String EmailError,
			  	String StreetNoError, String StreetNameError, String CityError,
				String StateError, String ZipError, String snapShotName) {
		  	assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Error"))).getAttribute("value").equals(Error));		  		  
		  	assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UsernameError"))).getAttribute("value").equals(UsernameError));
		   	assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_PasswordError"))).getAttribute("value").equals(PasswordError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_LastError"))).getAttribute("value").equals(LastError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_FirstError"))).getAttribute("value").equals(FirstError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_IdError"))).getAttribute("value").equals(UtaIdError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_PhoneError"))).getAttribute("value").equals(PhoneError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_EmailError"))).getAttribute("value").equals(EmailError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StreetNumberError"))).getAttribute("value").equals(StreetNoError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StreetNameError"))).getAttribute("value").equals(StreetNameError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_CityError"))).getAttribute("value").equals(CityError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_StateError"))).getAttribute("value").equals(StateError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_ZipcodeError"))).getAttribute("value").equals(ZipError));
		    takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyUpdateTitles(WebDriver driver, String currentPageTitle, String title1, String title2,
			  String title3, String title4, String title5, String title6,
			  String title7, String title8, String title9,
			  String title10, String title11, String title12, String title13,
			  String title14, String snapShotName) {
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Title"))).getText().equals(currentPageTitle));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Username_Title"))).getText().equals(title1));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Password_Title"))).getText().equals(title2));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Last_Name_Title"))).getText().equals(title3));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_First_Name_Title"))).getText().equals(title4));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Role_Title"))).getText().equals(title5));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_UTA_ID_Title"))).getText().equals(title7));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Phone_Title"))).getText().equals(title8));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Email_Title"))).getText().equals(title9));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Number_Title"))).getText().equals(title10));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Street_Name_Title"))).getText().equals(title11));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_City_Title"))).getText().equals(title12));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_State_Title"))).getText().equals(title13));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("UpdateProfile_Txt_Zipcode_Title"))).getText().equals(title14));
		    takeScreenshot(driver,snapShotName);
	  }
	   public String parseDate(String date1) {
		   
		   return date1.substring(4)+"-"+date1.substring(0,2)+"-"+date1.substring(2,4);
		   
	   }
	   
	   public String [][] getTableContentsFromPage(int listSize, String prefix) { 
		   String postfix = "";	  
		   int cols = 0;
		   if (prefix.contains("Staff")) {
		   		 postfix = "CatererStaffEventList"; 
		   		 cols = 7;
		   }
		   else if (prefix.contains("Mgr") || prefix.contains("User")) {
		   		 postfix = "CatererMgrEventList_Txt"; 
		   		 cols = 8;
		   }
			  String [][] eventArray = new String[listSize-1][cols];

			  for (int i=0; i<listSize-1; i++) {
				  eventArray[i][0]=  driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_Event_Name_Col"))).getText();
				  eventArray[i][1] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_Date_Col"))).getText();
				  eventArray[i][2] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_Start_Time_Col"))).getText();
				  eventArray[i][3] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_Duration_Col"))).getText();
				  eventArray[i][4] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_Hall_Name_Col"))).getText();
				  eventArray[i][5] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_User_Last_Name_Col"))).getText();
				  eventArray[i][6] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"_User_First_Name_Col"))).getText();
				  if (cols==8) 
					  eventArray[i][7] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
							  prop.getProperty(postfix+"_Estimated_Attendees_Col"))).getText();
				  		
			  }
			  return eventArray;
		  }
	   
	   public void CMModifyEventInputs (String lastName, String firstName, String date, String time,String duration,String hall,String attendees,String eventName,String foodType,
			   String meal,String formality,String drinkType,String entertainment,String accept,String snapShotName) throws InterruptedException {
			  
		  	driver.manage().timeouts().pageLoadTimeout(1000000, TimeUnit.SECONDS);
			
		  	driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Last_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Last_Name"))).sendKeys(lastName);
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_First_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_First_Name"))).sendKeys(firstName);
		    
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Date"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Date"))).sendKeys(date);
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Start_Time"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Start_Time"))).sendKeys(time);
		    

		    Select drpDuration=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Duration"))));
		    drpDuration.selectByVisibleText(duration);
		    Select drpHall=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Hall_Name"))));
		    drpHall.selectByVisibleText(hall);
		    
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Attendees"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Attendees"))).sendKeys(attendees);
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Event_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Event_Name"))).sendKeys(eventName);
		    
		    Select drpFood=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Food_Type"))));
		    drpFood.selectByVisibleText(foodType);
		    Select drpMeal=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Meal"))));
		    drpMeal.selectByVisibleText(meal);
		    Select drpFormality=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Formality"))));
		    drpFormality.selectByVisibleText(formality);
		    Select drpDrink=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Drink_Type"))));
		    drpDrink.selectByVisibleText(drinkType);
		    Select drpEntertainment=new Select(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Entertainment"))));
		    drpEntertainment.selectByVisibleText(entertainment);
		    
		    driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Btn_Modify"))).click();
		    
		    Thread.sleep(500);
		    Alert alertPopup = driver.switchTo().alert();

		    if(accept.equals("yes"))
		    	alertPopup.accept();
		    else
		    	alertPopup.dismiss();

		    takeScreenshot(driver,snapShotName);
	  }

	   
	   public void verifyCMModifyEventInputsErrorMsgs(String error, String errorLName, String errorFName,
				  String errorDate, String errorTime, String errorDuration,String errorAttendees,
				  String errorEventName, String snapShotName) {
			  	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Error"))).getAttribute("value").equals(error));
			   	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_UserNamesErrorLast"))).getAttribute("value").equals(errorLName));
			   	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_UserNamesErrorFirst"))).getAttribute("value").equals(errorFName));
			   	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_EventDateError"))).getAttribute("value").equals(errorDate));
			   	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_StartTimeError"))).getAttribute("value").equals(errorTime));
			   	assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_DurationError"))).getAttribute("value").equals(errorDuration));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_AttendeesError"))).getAttribute("value").equals(errorAttendees));
			    assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_EventNameError"))).getAttribute("value").equals(errorEventName));
			    takeScreenshot(driver,snapShotName);
		  }

	   public void verifyTC03cTitles(WebDriver driver, String title, String title1, String title2,String title3, String title4, 
			   String title5, String title6, String title7, String title8, String title9, String title10, 
			   String title11, String title12, String title13, String title14, String title15,
			   String title16, String snapShotName) {
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Hall_Name_Title"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Status_Title"))).getText().equals(title14));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Caterer_Last_Title"))).getText().equals(title15));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("CatererMgrModifyEvent_Txt_Caterer_First_Title"))).getText().equals(title16));
		   takeScreenshot(driver,snapShotName);
	   }


	   public void Register (WebDriver driver,int testcase, String username,
				String userLast, String userFirst, String role, String Password,
				String UtaID, String phoneNum, String Email,String streetNumber, String streetName, String city, String state, String zipCode,String snapShotName) throws InterruptedException {
			  
		  	driver.manage().timeouts().pageLoadTimeout(1000000, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("Register_Txt_Username"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Username"))).sendKeys(username);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Password"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Password"))).sendKeys(Password);
		   
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_First_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_First_Name"))).sendKeys(userFirst);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Last_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Last_Name"))).sendKeys(userLast);
		    Select drpRole=new Select(driver.findElement(By.name("role")));
		    drpRole.selectByVisibleText(role);
		    
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_UTA_ID"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_UTA_ID"))).sendKeys(UtaID);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Phone"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Phone"))).sendKeys(phoneNum);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Email"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Email"))).sendKeys(Email);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Street_Number"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Street_Number"))).sendKeys(streetNumber);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Street_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Street_Name"))).sendKeys(streetName);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_City"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_City"))).sendKeys(city);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_State"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_State"))).sendKeys(state);
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Zipcode"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("Register_Txt_Zipcode"))).sendKeys(zipCode);
		    driver.findElement(By.xpath(prop.getProperty("Register_Btn_Register"))).click();
		    takeScreenshot(driver,snapShotName);
	  }
	   
	   public void RequestInputs (String duration,String hall,String attendees,String eventName,String foodType,
			   String meal,String formality,String drinkType,String entertainment,String snapShotName) throws InterruptedException {
			  
		  	driver.manage().timeouts().pageLoadTimeout(1000000, TimeUnit.SECONDS);
			
		    Select drpDuration=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Duration"))));
		    drpDuration.selectByVisibleText(duration);
		    Select drpHall=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Hall_Name"))));
		    drpHall.selectByVisibleText(hall);
		    
		    driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Attendees"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Attendees"))).sendKeys(attendees);
		    driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Event_Name"))).clear();
		    driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Event_Name"))).sendKeys(eventName);
		    Select drpFood=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Food_Type"))));
		    drpFood.selectByVisibleText(foodType);
		    Select drpMeal=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Meal"))));
		    drpMeal.selectByVisibleText(meal);
		    Select drpFormality=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Formality"))));
		    drpFormality.selectByVisibleText(formality);
		    Select drpDrink=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Drink_Type"))));
		    drpDrink.selectByVisibleText(drinkType);
		    Select drpEntertainment=new Select(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Entertainment"))));
		    drpEntertainment.selectByVisibleText(entertainment);
		    

		    driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Btn_Reserve"))).click();
		    
		    takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyRegisterErrorMessages(WebDriver driver,String errorMsg, String UsernameError, String LastError, String FirstError,
				String PasswordError, String RoleError, String UtaIdError, String EmailError,
				String PhoneError, String StreetNoError, String StreetNameError, String CityError,
				String StateError, String ZipError, String snapShotName) {
		  		  
		  	assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_Error"))).getAttribute("value").equals(errorMsg));
		   	assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_UsernameError"))).getAttribute("value").equals(UsernameError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_LastError"))).getAttribute("value").equals(LastError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_FirstError"))).getAttribute("value").equals(FirstError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_PasswordError"))).getAttribute("value").equals(PasswordError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_RoleError"))).getAttribute("value").equals(RoleError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_IdError"))).getAttribute("value").equals(UtaIdError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_EmailError"))).getAttribute("value").equals(EmailError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_PhoneError"))).getAttribute("value").equals(PhoneError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_StreetNumberError"))).getAttribute("value").equals(StreetNoError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_StreetNameError"))).getAttribute("value").equals(StreetNameError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_CityError"))).getAttribute("value").equals(CityError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_ZipcodeError"))).getAttribute("value").equals(ZipError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Txt_StateError"))).getAttribute("value").equals(StateError));
		    takeScreenshot(driver,snapShotName);
		    
	  }
	  
	  public void verifyRequestInputsErrorMsgs(String error,String errorDuration,String errorAttendees,String errorEventName, String snapShotName) {
		  		  
		  	assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_Error"))).getAttribute("value").equals(error));
		   	assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_DurationError"))).getAttribute("value").equals(errorDuration));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_AttendeesError"))).getAttribute("value").equals(errorAttendees));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("RequestEventInputs_Txt_EventNameError"))).getAttribute("value").equals(errorEventName));
		    takeScreenshot(driver,snapShotName);
		    
	  }
	  
	  public void verifyRegisterTitles(WebDriver driver,String t1,String t2,String t3,String t4,String t5,String t6,String t7,
			  String t8,String t9,String t10,String t11,String t12,String t13,String t14,String t15, String snapShotName) {
		        
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_Lnk_Index"))).getText().equals(t1));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Register_User"))).getText().equals(t2));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Username"))).getText().equals(t3));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Password"))).getText().equals(t4));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Lastname"))).getText().equals(t5));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Firstname"))).getText().equals(t6));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Role"))).getText().equals(t7));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_UTA_ID"))).getText().equals(t8));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Phone"))).getText().equals(t9));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Email"))).getText().equals(t10));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Street_Number"))).getText().equals(t11));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Street_Name"))).getText().equals(t12));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_City"))).getText().equals(t13));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_State"))).getText().equals(t14));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("Register_title_Zipcode"))).getText().equals(t15));
		    takeScreenshot(driver,snapShotName);
	  }

	  public void verifyAdminHompageLinks(String title2,String button1,String title3,String title4, String snapShotName) {
		 verifyButton(driver,title2,button1,"AdminHomepage_Txt_Title","ViewProfile_Txt_Title","AdminHomepage_Btn_View_My_User_Profile");
		 verifyButton(driver,title2,title4,"AdminHomepage_Txt_Title","Index_Txt_Title","AdminHomepage_Btn_Logout");
		 verifyButton(driver,title2,title4,"AdminHomepage_Txt_Title","Index_Txt_Title","AdminHomepage_Lnk_Index");
		 verifyButton(driver,title2,title3,"AdminHomepage_Txt_Title","AdminUserSearch_Txt_Title","AdminHomepage_Btn_Search_User");
		 takeScreenshot(driver,snapShotName);
	  }
	 
	  public void verifySearchUserLinks(String title3,String title4,String title5, String snapShotName){
		 verifyButton(driver,title3,title5,"AdminUserSearch_Txt_Title","ListUser_Txt_Title","AdminUserSearch_Btn_Submit");
		 verifyButton(driver,title3, title4,"AdminUserSearch_Txt_Title","Index_Txt_Title","AdminUserSearch_Btn_Logout");
		 takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyListUserLinks(String title5,String title6, String title7,String title2,String title4) {
		 //home
		  verifyButton(driver,title5,title2,"ListUser_Txt_Title","AdminHomepage_Txt_Title","ListUser_Btn_Home");
	  //logout
		  verifyButton(driver,title5,title4,"ListUser_Txt_Title","Index_Txt_Title","ListUser_Btn_Logout");  
	  //view selected
		  driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+("2")+
		    		prop.getProperty("ListUser_UserTableRadioCol"))).click();
		  verifyButton(driver,title5,title6,"ListUser_Txt_Title","UserSearchResults_Txt_Title","ListUser_Btn_ListSelectedUser");
	  }
	  
	  public void verifyNoUserSelection(WebDriver driver, String error) {
		  driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
		  assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_err"))).getText().equals(error));
	  }
	  public void verifySearchUserTitles(WebDriver driver, String title1, String title3, String snapShotName) {
		  assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Title"))).getText().equals(title3));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Lnk_Index"))).getText().equals(title1));
		    takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyAdminHomepageTitles(WebDriver driver,String title1,String title2, String snapShotName){
		   	assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Txt_Title"))).getText().equals(title2));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("AdminHomepage_Lnk_Index"))).getText().equals(title1));
		    takeScreenshot(driver,snapShotName);
	  }
	  
	  public void searchUser(String uLastname, String snapShotName) {
		   driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Last_Name"))).clear();
		   driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Txt_Last_Name"))).sendKeys(uLastname); 
		   driver.findElement(By.xpath(prop.getProperty("AdminUserSearch_Btn_Submit"))).click();
		   takeScreenshot(driver,snapShotName);
      }
	  
	  public void AdminDeleteUser (WebDriver driver, int radio, String snapShotName, String accept) throws Exception {
		   
		  driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+prop.getProperty("ListUser_UserTableRadioCol"))).click();
		  Thread.sleep(3_000);
		  driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_DeleteUser"))).click();
		  Thread.sleep(500);
		  Alert alertPopup = driver.switchTo().alert();
		  if (accept.equals("yes")) 
			  alertPopup.accept();
		  else
			  alertPopup.dismiss();
			  
		  takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyDeleteUser(WebDriver driver,int radio, String accept, String snapShotName) {
		  if (!accept.equals("yes"))
			  assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+prop.getProperty("ListUser_UserTableFirstNameCol"))).getText()
				  .equals("Addie"));
		  else
			  assertTrue(!driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(radio+1)+prop.getProperty("ListUser_UserTableFirstNameCol"))).getText()
					  .equals("Addie"));
		  takeScreenshot(driver,snapShotName);
	  }
	  
	  public void verifyRadioButtonsUsearch(String table, String next_title, String snapShotName) {
		   
		   WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		    
		   for (int i = 1; i < rows; i++) {
			    driver.findElement(By.xpath(prop.getProperty("ListUser_prefixAddressUserTable")+(i+1)+
			    		prop.getProperty("ListUser_UserTableRadioCol"))).click();
			    try {
					  Thread.sleep(1_000);
				} catch (InterruptedException e) {}
			    driver.findElement(By.xpath(prop.getProperty("ListUser_Btn_ListSelectedUser"))).click();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}

				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Txt_Title"))).getText().equals(next_title));
			    driver.navigate().back();
			    try {
					  Thread.sleep(500);
				} catch (InterruptedException e) {}
		   }
		    takeScreenshot(driver,snapShotName);
	   }
	   
	   public void verifyListUserTitles(String t5,String t8,String t9,String t10, String t11, String t12) {
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_Title"))).getText().equals(t5));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_SelectUserHeader"))).getText().equals(t8));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_LastNameHeader"))).getText().equals(t9));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_FirstNameHeader"))).getText().equals(t10));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_UsernameHeader"))).getText().equals(t11));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty("ListUser_Txt_RoleHeader"))).getText().equals(t12));
	 }
	   public void verifyListUserData(String table,String lname,String snapShotName) {
		   WebElement eventTable = driver.findElement(By.xpath(prop.getProperty(table)));
			int rows = eventTable.findElements(By.tagName("tr")).size();
			String[][] y=getUserListFromDB(rows,lname);
			String[][] x=getTableContentsFromPage1(rows,"ListUser_prefixAddressUserTable");
		    assertTrue(arraysDiff(y,x));
			    takeScreenshot(driver,snapShotName);
	   }
	public String[][] getUserListFromDB(int listSize, String lname) {
		String query = "";
		int cols = 4;
		if(lname.equals("")) {
			query="SELECT last_name,first_name,username,role FROM catering.system_user";
		}
		else {
			query="SELECT last_name,first_name,username,role FROM catering.system_user where last_name='"+lname+"'";
		}
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		String[][] user = new String [listSize-1][cols];
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(query);
			int i=0;
			
			while (userList.next()) {

				user[i][0]=userList.getString("last_name");
				user[i][1]=userList.getString("first_name");
				user[i][2]=userList.getString("username");
				user[i][3]=userList.getString("role");
			}	
		} catch (SQLException e) {}
		return user;
		}
	
	public String [][] getTableContentsFromPage1(int listSize, String prefix) { 
	
		   String postfix = "ListUser_UserTable";	  
		   int cols = 4;
			  String [][] UserArray = new String[listSize-1][cols];
			  for (int i=0; i<listSize-1; i++) {
				  
				  UserArray[i][0]=  driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"LastNameCol"))).getText();
				  UserArray[i][1] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"FirstNameCol"))).getText();
				  UserArray[i][2] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"USernameCol"))).getText();
				  UserArray[i][3] = driver.findElement(By.xpath(prop.getProperty(prefix)+(i+2)+
						  prop.getProperty(postfix+"RoleCol"))).getText();
			  }
				  
			  return UserArray;
		  }
		  
		  public void verifyUser(String uname, String snapShotName) {
		 String query="SELECT * FROM catering.system_user WHERE username='"+uname+"'";
		 Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			String[][] user = new String [1][13];
			try {
				stmt = conn.createStatement();
				ResultSet userList = stmt.executeQuery(query);
				int i=0;		
				while(userList.next()) {
					user[i][0]=userList.getString("username");
					user[i][1]=userList.getString("password");
					user[i][2]=userList.getString("last_name");
					user[i][3]=userList.getString("first_name");
					user[i][4]=userList.getString("role");
					user[i][5]=userList.getString("UTA_ID");
					user[i][6]=userList.getString("phone");
					user[i][7]=userList.getString("email");
					user[i][8]=userList.getString("street_number");
					user[i][9]=userList.getString("street_name");
					user[i][10]=userList.getString("city");
					user[i][11]=userList.getString("state");
					user[i][12]=userList.getString("zipcode");
				}
			} catch (SQLException e) {}
			
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Username"))).getText().equals(user[0][0]));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Password"))).getText().equals(user[0][1]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Last_Name"))).getText().equals(user[0][2]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_First_Name"))).getText().equals(user[0][3]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Role"))).getText().equals(user[0][4]));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_UTA_ID"))).getText().equals(user[0][5]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Phone"))).getText().equals(user[0][6]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_ValueEmail"))).getText().equals(user[0][7]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Street_Number"))).getText().equals(user[0][8]));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Street_Name"))).getText().equals(user[0][9]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_City"))).getText().equals(user[0][10]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_State"))).getText().equals(user[0][11]));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Value_Zipcode"))).getText().equals(user[0][12]));
			takeScreenshot(driver,snapShotName);
	 }
	 
	 public void verifySearchUserResultsTitles(String t1,String t2,String t3,String t4,String t5,String t6,String t7,
					  String t8,String t9,String t10,String t11,String t12,String t13, String title13, String snapShotName) {
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Username"))).getText().equals(t1));	
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Password"))).getText().equals(t6));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Last_Name"))).getText().equals(t3));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_First_Name"))).getText().equals(t2));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Role"))).getText().equals(t5));	
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_UTA_ID"))).getText().equals(t4));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Phone"))).getText().equals(t7));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Email"))).getText().equals(t8));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Street_Number"))).getText().equals(t10));	
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Street_Name"))).getText().equals(t9));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_City"))).getText().equals(t11));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_State"))).getText().equals(t12));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("UserSearchResults_Title_Zipcode"))).getText().equals(t13));
				takeScreenshot(driver,snapShotName);
			  }
			 
			  public void verifySearchUserResultsLinks(String title6,String title4,String title2,String title13, String snapShotName){
				  verifyButton(driver,title6,title13,"UserSearchResults_Txt_Title","ModifyUser_Title","UserSearchResults_Btn_ModifyUser");
				  verifyButton(driver,title6, title4,"UserSearchResults_Txt_Title","Index_Txt_Title","UserSearchResults_Btn_Logout");
				  takeScreenshot(driver,snapShotName);
	 }
  
	  public void verifyButton(WebDriver driver, String title, String button, String thisTitlePage, String nextTitlePage, String click) {
		   /* title = title of the current page
		    * button = title of the page you are going to
		    * thisTitlePage = SharedUIMap address of the title on the current page
		    * nextTitlePage = SharedUIMap address of the title on the next page
		    * click = SharedUIMap address of the button you are going to check
		    */
		   driver.findElement(By.xpath(prop.getProperty(click))).click();
		   try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty(nextTitlePage))).getText().equals(button));
		   
		   if(click.contains("back") || click.contains("Back")) {
			   driver.navigate().forward();
		   }
		   else
			   driver.navigate().back();
		   try {
				  Thread.sleep(500);
			} catch (InterruptedException e) {}
		   
		   assertTrue(driver.findElement(By.xpath(prop.getProperty(thisTitlePage))).getText().equals(title));
	   }
	   
	   public Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
			  Boolean diff=false || (array1.length!=array2.length) || (array1[0].length!=array2[0].length);
			  for (int i=0;i<array1.length && !diff;i++) {
				  for (int j = 0; j < array1[0].length && !diff; j++) {
					  diff  = !array1[i][j].equals(array2[i][j]); 
				  }
			  }
			  return diff;
		  }
	   
	   
		public void WaitForElementPresent(WebDriver driver , By by, int iTimeOut) throws InterruptedException
		{
			int iTotal = 0;
			int iSleepTime = 5000;
			while(iTotal < iTimeOut)
			{
				List<WebElement> oWebElements = driver.findElements(by);
				if(oWebElements.size()>0)
					return;
				else
				{
					
						Thread.sleep(iSleepTime);
						iTotal = iTotal + iSleepTime;      
					
					
				}
			}
		}
		public void verifyModifyUserTitles(String title13,String m1,String m2,String m3,String m4,String m5,String m6,String m7,String m8,String m9,String m10,String m11,String m12,String m13,String SnapShotName){
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title"))).getText().equals(title13));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Username"))).getText().equals(m1));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Password"))).getText().equals(m2));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Last_Name"))).getText().equals(m3));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_First_Name"))).getText().equals(m4));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Role"))).getText().equals(m5));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_UTA_ID"))).getText().equals(m6));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Phone"))).getText().equals(m7));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Email"))).getText().equals(m8));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Street_Number"))).getText().equals(m9));	
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Street_Name"))).getText().equals(m10));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_City"))).getText().equals(m11));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_State"))).getText().equals(m12));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Title_Zipcode"))).getText().equals(m13));
			
			takeScreenshot(driver,SnapShotName);
		}
		
	public void 	modifyUser(WebDriver driver,String username,String userLast,String userFirst,String role,String Password,String UtaID,String phoneNum,String Email,String streetNumber,
			String  streetName,String city,String state,String zipCode,String Mpopup,String accept,String snapShotName) throws InterruptedException{
		driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Username"))).sendKeys(username);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Password"))).sendKeys(Password);
	   
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_First_Name"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_First_Name"))).sendKeys(userFirst);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Last_Name"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Last_Name"))).sendKeys(userLast);
	    Select drpRole=new Select(driver.findElement(By.name("role")));
	    drpRole.selectByVisibleText(role);
	    
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_UTA_ID"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_UTA_ID"))).sendKeys(UtaID);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Phone"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Phone"))).sendKeys(phoneNum);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Email"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Email"))).sendKeys(Email);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Street_Number"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Street_Number"))).sendKeys(streetNumber);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Street_Name"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Street_Name"))).sendKeys(streetName);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_City"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_City"))).sendKeys(city);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_State"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_State"))).sendKeys(state);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Zipcode"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Zipcode"))).sendKeys(zipCode);
	    driver.findElement(By.xpath(prop.getProperty("ModifyUser_Btn_Modify"))).click();
	    Alert javascriptAlert = driver.switchTo().alert();
	    Thread.sleep(1_500);
	    assertEquals(Mpopup,javascriptAlert.getText());
	    if (accept.equals("yes"))
	    	javascriptAlert.accept();
	    else
	    	javascriptAlert.dismiss();
	    takeScreenshot(driver,snapShotName);
		}
	
	 public void verifyModifyUserErrorMessages(WebDriver driver,String errorMsg, String UsernameError, String LastError, String FirstError,
				String PasswordError, String RoleError, String UtaIdError, String EmailError,
				String PhoneError, String StreetNoError, String StreetNameError, String CityError,
				String StateError, String ZipError, String snapShotName) {
		  		  
		  	assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_Error"))).getAttribute("value").equals(errorMsg));
		   	assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_UsernameError"))).getAttribute("value").equals(UsernameError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_LastError"))).getAttribute("value").equals(LastError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_FirstError"))).getAttribute("value").equals(FirstError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_PasswordError"))).getAttribute("value").equals(PasswordError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_RoleError"))).getAttribute("value").equals(RoleError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_IdError"))).getAttribute("value").equals(UtaIdError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_EmailError"))).getAttribute("value").equals(EmailError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_PhoneError"))).getAttribute("value").equals(PhoneError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_StreetNumberError"))).getAttribute("value").equals(StreetNoError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_StreetNameError"))).getAttribute("value").equals(StreetNameError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_CityError"))).getAttribute("value").equals(CityError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_ZipcodeError"))).getAttribute("value").equals(ZipError));
		    assertTrue(driver.findElement(By.xpath(prop.getProperty("ModifyUser_Txt_StateError"))).getAttribute("value").equals(StateError));
		    takeScreenshot(driver,snapShotName);
		    
	  }
	 
	public void UserViewEvent(String inputDate,String inputTime,String snapShotName) {
	    driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Date"))).sendKeys(inputDate);
	    driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Start_Time"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("SearchUserEventSummary_Txt_Start_Time"))).sendKeys(inputTime);
	    takeScreenshot(driver,snapShotName);
		
	}
	public void inputModifyEvent(String date, String time, String duration, String hall,
			  String attendees, String eventName, String foodType, String meal,
			  String formality, String drinkType, String entertainment, String popUp,String snapShotName) throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(1000000, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Date"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Date"))).sendKeys(date);
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Start_Time"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Start_Time"))).sendKeys(time);
	    Select drpDuration=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Duration"))));
	    drpDuration.selectByVisibleText(duration);
	    Select drpHall=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Hall_Name"))));
	    drpHall.selectByVisibleText(hall);
	    
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Attendees"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Attendees"))).sendKeys(attendees);
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Event_Name"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Event_Name"))).sendKeys(eventName);
	    Select drpFood=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Food_Type"))));
	    drpFood.selectByVisibleText(foodType);
	    Select drpMeal=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Meal"))));
	    drpMeal.selectByVisibleText(meal);
	    Select drpFormality=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Formality"))));
	    drpFormality.selectByVisibleText(formality);
	    Select drpDrink=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Drink_Type"))));
	    drpDrink.selectByVisibleText(drinkType);
	    Select drpEntertainment=new Select(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Entertainment"))));
	    drpEntertainment.selectByVisibleText(entertainment);
	    Thread.sleep(800);
	    driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Btn_Modify"))).click();
	    Alert javascriptAlert = driver.switchTo().alert();
	    Thread.sleep(2_000);
	    assertEquals("Are you sure you want to modify the event?",javascriptAlert.getText());
	    if(popUp.equals("yes"))
	    	javascriptAlert.accept();
	    else
	    	javascriptAlert.dismiss();
	    takeScreenshot(driver,snapShotName);
	    
	}
	public void verifyModifyEventErrorMessages(
			  String error, String errorDate, String errorTime,String errorDuration, String errorAttendees,
			  String errorEventName, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Error"))).getAttribute("value").equals(error));
	   	assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_EventDateError"))).getAttribute("value").equals(errorDate));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_StartTimeError"))).getAttribute("value").equals(errorTime));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_DurationError"))).getAttribute("value").equals(errorDuration));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_AttendeesError"))).getAttribute("value").equals(errorAttendees));
	    assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_EventNameError"))).getAttribute("value").equals(errorEventName));
	    takeScreenshot(driver,snapShotName);
	}
	public void updateUserEvent(int id, String user, String userLast, String userFirst,
			String date, String time, String duration, String hall,
			  String attendees, String eventName, String foodType, String meal,
			  String formality, String drinkType, String entertainment, String status) {
		Event event = new Event();
		event.setEvent(id,user, userLast,userFirst, date, time, duration, hall, attendees, 
				eventName, foodType, meal, formality, drinkType, entertainment, status);
		EventDAO.updateEvent(event);
	}
	
	public void updateUser(String username, String password, String lastname, String firstname, String role, String utaId, 
			String phone, String email, String streetNumber, String streetName, String city, String state, String zipcode) {
		
		User user = new User();
	
		user.setUser (username,password, lastname, firstname, role, utaId, 
			phone, email, streetNumber, streetName, city, state, zipcode);
		UserDAO.modifyUser(user);
	}
		
	public void verifyTC06eTitles(WebDriver driver, String title, String title1, String title2, String title3, String title4, String title5, 
			   String title6, String title7, String title8, String title9, String title10, String title11, 
			   String title12, String title13, String title14, String snapShotName
			   ) {
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Title"))).getText().equals(title));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Last_Name_Title"))).getText().equals(title1));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_First_Name_Title"))).getText().equals(title2));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Date_Title"))).getText().equals(title3));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Start_Time_Title"))).getText().equals(title4));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Duration_Title"))).getText().equals(title5));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Hall_Name_Title"))).getText().equals(title6));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Attendees_Title"))).getText().equals(title7));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Event_Name_Title"))).getText().equals(title8));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Food_Type_Title"))).getText().equals(title9));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Meal_Title"))).getText().equals(title10));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Formality_Title"))).getText().equals(title11));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Drink_Type_Title"))).getText().equals(title12));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Entertainment_Title"))).getText().equals(title13));
		   assertTrue(driver.findElement(By.xpath(prop.getProperty("UserModifyEvent_Txt_Status_Title"))).getText().equals(title14));
		   takeScreenshot(driver,snapShotName);
	   }


}