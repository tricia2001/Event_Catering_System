package Project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Project.util.SQLConnection;
import Project.controller.EventController;
import Project.model.Event;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.sql.Date;

public class EventDAO {

	private static final Logger LOGGER = Logger.getLogger(EventDAO.class.getName());
	   
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Event> ReturnMatchingEventsList (String queryString) {
		ArrayList<Event> eventListInDB = new ArrayList<Event>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Event event = new Event(); 
				event.setAttendees(eventList.getString("attendees"));
				event.setCatererUsername(eventList.getString("caterer_username"));
				event.setCatererLast(eventList.getString("caterer_last_name")); 
				event.setCatererFirst(eventList.getString("caterer_first_name")); 
				event.setDate(eventList.getString("date"));
				event.setDrinkType(eventList.getString("drink_type")); 
				event.setDuration(eventList.getString("duration")); 
				event.setEntertainment(eventList.getString("entertainment")); 
				event.setEstimatedCost(eventList.getString("estimated_cost")); 
				event.setEventId(eventList.getInt("eventID")); 
				event.setEventName(eventList.getString("event_name")); 
				event.setFoodType(eventList.getString("food"));  
				event.setFormality(eventList.getString("formality")); 
				event.setHall(eventList.getString("hall")); 
				event.setMeal(eventList.getString("meal")); 
				event.setStatus(eventList.getString("status")); 
				event.setTime(eventList.getString("start_time")); 
				event.setUsername(eventList.getString("username"));
				event.setUserLast(eventList.getString("last_name"));
				event.setUserFirst(eventList.getString("first_name")); 
				eventListInDB.add(event);	
			}
		} catch (SQLException e) {}
		return eventListInDB;
	}
	
	private static ArrayList<Event> ReturnMatchingEventsListNoNames (String queryString) {
		ArrayList<Event> eventListInDB = new ArrayList<Event>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Event event = new Event(); 
				event.setAttendees(eventList.getString("attendees"));
				event.setCatererUsername(eventList.getString("caterer_username"));
				event.setDate(eventList.getString("date"));
				event.setDrinkType(eventList.getString("drink_type")); 
				event.setDuration(eventList.getString("duration")); 
				event.setEntertainment(eventList.getString("entertainment")); 
				event.setEstimatedCost(eventList.getString("estimated_cost")); 
				event.setEventId(eventList.getInt("eventID")); 
				event.setEventName(eventList.getString("event_name")); 
				event.setFoodType(eventList.getString("food"));  
				event.setFormality(eventList.getString("formality")); 
				event.setHall(eventList.getString("hall")); 
				event.setMeal(eventList.getString("meal")); 
				event.setStatus(eventList.getString("status")); 
				event.setTime(eventList.getString("start_time")); 
				event.setUsername(eventList.getString("username"));
				eventListInDB.add(event);	
			}
		} catch (SQLException e) {}
		return eventListInDB;
	}
	
	public static void insertEvent (Event event) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertEvent = "INSERT INTO CATERING.EVENT (eventID,username,date,start_time,duration,hall,attendees,"
					+ "event_name,food,meal,formality,drink_type,entertainment,status,estimated_cost) " + " VALUES ("  
					+ event.getEventId()  + ",'"
					+ event.getUsername() + "',"		
					+ "STR_TO_DATE('"+event.getDate() +"', '%Y-%m-%d'),'"
					+ event.getTime() + "','"
					+ event.getDuration() + "','"
					+ event.getHall() + "',"
					+ event.getAttendees() + ",'"
					+ event.getEventName() + "','"
					+ event.getFoodType() + "','"
					+ event.getMeal() + "','"
					+ event.getFormality() + "','"
					+ event.getDrinkType() + "','"
					+ event.getEntertainment() + "',"
					+ "'saved',"
					+ event.getEstimatedCost().substring(1) + ")";
			stmt.executeUpdate(insertEvent);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	public static void updateEvent (Event event) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertEvent = "UPDATE CATERING.EVENT SET "   
					+ "username = (SELECT username FROM catering.system_user " +
					"WHERE '"+event.getUserLast()+"' = last_name AND '"+event.getUserFirst()+"' "
					+" = first_name),"		
					+ "date = STR_TO_DATE('"+event.getDate() +"', '%Y-%m-%d'),"
					+ "start_time = '"+event.getTime() + "',"
					+ "duration = '"+event.getDuration() + "', "
					+ "hall = '"+event.getHall() + "', "
					+ "attendees = '"+event.getAttendees() + "', "
					+ "event_name = '"+event.getEventName() + "', "
					+ "food = '"+event.getFoodType() + "', "
					+ "meal = '"+event.getMeal() + "', "
					+ "formality = '"+event.getFormality() + "', "
					+ "drink_type = '"+event.getDrinkType() + "', "
					+ "entertainment = '"+event.getEntertainment() + "', "
					+ "status = '"+event.getStatus()+"' "
					+ "WHERE eventID = "+event.getEventId();
			stmt.executeUpdate(insertEvent);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	//Event search for caterer manager
	public static ArrayList<Event>  listCatererEvents(String date, String time) {  
			return ReturnMatchingEventsList("SELECT e.*, u.last_name, u.first_name, " +
					"c.last_name caterer_last_name, c.first_name caterer_first_name " + 
					"from CATERING.EVENT e " + 
					"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username " + 
					"LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username " + 
					"WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') " + 
					"AND start_time >= '"+time+"' AND status = 'reserved' ORDER BY date, start_time");
			
			
	}
	//Event search for caterer staff
	public static ArrayList<Event>  listMyCatererEvents(String date, String time, String username) {  
		return ReturnMatchingEventsList(" SELECT e.*, u.last_name, u.first_name, " +
					"c.last_name caterer_last_name, c.first_name caterer_first_name " +
					"from CATERING.EVENT e " +
					"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username " 
					+ "LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') "
					+ "AND start_time >= '"+time+"' AND caterer_username = '"+username+"' AND status = 'reserved' ORDER BY date, start_time");
	}
	//Search for Event by ID
	public static Event  idSearch(int id) { 
		ArrayList<Event> eventListInDB = ReturnMatchingEventsList(" SELECT e.*, u.last_name, u.first_name, " +
			"c.last_name caterer_last_name, c.first_name caterer_first_name " +
			"from CATERING.EVENT e " +
			"INNER JOIN CATERING.SYSTEM_USER u ON e.username = u.username " 
			+ "LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username WHERE eventID = "+id );
		return eventListInDB.get(0);
	}
	//Event search for user
	public static ArrayList<Event>  listMyUserEvents(String date, String time, String username) { 
		return ReturnMatchingEventsList(" SELECT e.*, u.last_name, u.first_name, " +
			"c.last_name caterer_last_name, c.first_name caterer_first_name " +
			"from CATERING.EVENT e "+
			"INNER JOIN CATERING.SYSTEM_USER u on e.username = u.username "
			+ "LEFT JOIN CATERING.SYSTEM_USER c on e.caterer_username = c.username WHERE date >= STR_TO_DATE('"+date+"', '%Y-%m-%d') "
			+ "AND start_time >= '"+time+"' AND e.username = '"+username+"' AND status = 'reserved' ORDER BY date, start_time");
}
	//Assign catering staff (manager function)
	public static void assignStaff(String last, String first, int eventId) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String update = "UPDATE CATERING.EVENT SET caterer_username = " + 
					"(SELECT username from CATERING.SYSTEM_USER " + 
					"WHERE last_name = '"+last+"' AND first_name = '"+first+"' AND role = 'Caterer Staff') " + 
					"WHERE eventID = "+eventId;
			stmt.executeUpdate(update);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	public static int getNewId() {
		Statement stmt = null;
		int id = -2;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT MAX(eventId) as max FROM CATERING.EVENT";
			ResultSet lastID = stmt.executeQuery(queryString);
			while (lastID.next()) {
				id = lastID.getInt("max");
			}
		} catch (SQLException e) {}
		return id+1;
	}
	
	public static void reserveEvent(int id) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertEvent = "UPDATE CATERING.EVENT SET status = 'reserved' "
					+ "WHERE eventID = "+id;
			stmt.executeUpdate(insertEvent);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	//determine if date is already has 2 bookings
	public static int eventDateFull(String date, String user)  {  
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		int answer = 0;
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT COUNT(*) FROM CATERING.EVENT WHERE STR_TO_DATE('"+date+"', '%Y-%m-%d') = date AND username = '"+user+"'";
		
			ResultSet one = stmt.executeQuery(queryString);	
			
			while(one.next()) {
				answer = one.getInt(1);
			}
		} catch (SQLException e) {}

		
		return answer;
	}
	
	//determine if week already has 5 bookings
		public static int eventWeekFull(String date1, String date2, String user)  {  
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();
			int answer = 0;
			
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT COUNT(*) FROM CATERING.EVENT WHERE STR_TO_DATE('"+date1+"', '%Y-%m-%d') <= date AND STR_TO_DATE('"+date2+"', '%Y-%m-%d') >= date AND username = '"+user+"'";
				ResultSet one = stmt.executeQuery(queryString);	
				while(one.next())
					answer = one.getInt(1);
	
			} catch (SQLException e) {}
			
			return answer;
		}
	
	//determine if hall booking overlaps
	public static ArrayList<Event> hallConflict(String date, double time, double duration, String hall, int id) {
		return ReturnMatchingEventsListNoNames("SELECT * FROM CATERING.EVENT WHERE STR_TO_DATE('"+date+"', '%Y-%m-%d') = date AND '"+hall+"' = hall AND eventID != "+id);
			
	}
	
	public static ArrayList<Event> hallConflict(String date, double time, double duration, String hall) {
		    return ReturnMatchingEventsListNoNames("SELECT * FROM CATERING.EVENT WHERE STR_TO_DATE('"+date+"', '%Y-%m-%d') = date AND '"+hall+"' = hall ");
	}
	
	public static int hallCapacity(String hall) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		int cap = 0;
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT capacity FROM CATERING.HALL WHERE hall_name = '"+hall+"'";
			ResultSet capacity = stmt.executeQuery(queryString);
			while (capacity.next()) {
				cap = capacity.getInt("capacity");
			}
		} catch (SQLException e) {}
		return cap;
	}
	
	public static double mealCost(String meal) {
		Statement stmt = null;
		double mealCost = 0;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT cost FROM CATERING.MEAL_TYPE WHERE meal_name = '"+meal+"'";
			ResultSet capacity = stmt.executeQuery(queryString);
			while (capacity.next()) {
				mealCost = capacity.getInt("cost");
			}
		} catch (SQLException e) {}
		return mealCost;
	}
	
	public static boolean namesMatch(String last, String first) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		boolean answer = true;
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT * FROM CATERING.SYSTEM_USER WHERE last_name = '"+last+"' AND first_name = '"+first+"'";
			ResultSet one = stmt.executeQuery(queryString);	
			if (!one.next()) answer =  false;
			
		} catch (SQLException e) {}
		return answer;
	}
	
	public static double formalMult(String formality) {
		Statement stmt = null;
		double formal = 0;
		Connection conn = SQLConnection.getDBConnection(); 
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT multiplier FROM CATERING.MEAL_FORMALITY WHERE formality_name = '"+formality+"'";
			ResultSet capacity = stmt.executeQuery(queryString);
			while (capacity.next()) {
				formal = capacity.getDouble(1);
			}
		} catch (SQLException e) {}
		return formal;
	}
	
	public static double drinkCost(String drink_type) {
		Statement stmt = null;
		double drinkCost = 0;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT added_cost FROM CATERING.DRINK_TYPE WHERE drink_name = '"+drink_type+"'";
			ResultSet capacity = stmt.executeQuery(queryString);
			while (capacity.next()) {
				drinkCost = capacity.getInt("added_cost");
			}
		} catch (SQLException e) {}
		return drinkCost;
	}
	//determine if name entered is a caterer in the system
	public static Boolean isCaterer(String last, String first)  {  
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		boolean answer = true;
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT * FROM CATERING.SYSTEM_USER WHERE last_name = '"+last+"' AND first_name = '"+first+"' AND role = 'Caterer Staff'";
			ResultSet one = stmt.executeQuery(queryString);	
			if (!one.next()) answer =  false;
		} catch (SQLException e) {}
		return answer;
	}
	
	
	
	//determine if event name is already in the system
		public static Boolean isUnique(String name, int id)  {  
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection(); 
			boolean answer = false;
			try {
				stmt = conn.createStatement();
				String queryString = "SELECT * FROM CATERING.EVENT WHERE event_name = '"+name+"' AND eventID != "+id;
				ResultSet one = stmt.executeQuery(queryString);	
				if (!one.next()) answer = true;
				
			} catch (SQLException e) {}
			return answer;
		}
		
		//determine if event name is already in the system
				public static Boolean isUnique(String name)  {  
					Statement stmt = null;
					Connection conn = SQLConnection.getDBConnection(); 
					boolean answer = false;
					try {
						stmt = conn.createStatement();
						String queryString = "SELECT * FROM CATERING.EVENT WHERE event_name = '"+name+"'";
						ResultSet one = stmt.executeQuery(queryString);	
						if (!one.next()) answer = true;
						
					} catch (SQLException e) {}
					return answer;
				}
		

}
