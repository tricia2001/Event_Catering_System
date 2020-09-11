package Project.model;

import java.io.Serializable;
import Project.data.EventDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.sql.Date;

public class Event implements Serializable{

	private static final long serialVersionUID = 3L;
	private int eventId;
	private String username;
	private String userLast;
	private String userFirst;
	private String date;
	private String time;
	private String duration;
	private String hall;
	private String attendees;
	private String eventName;
	private String foodType;
	private String meal;
	private String formality;
	private String drinkType;
	private String entertainment;
	private String status;
	private String catererUsername;
	private String catererLast;
	private String catererFirst;
	private String estimatedCost;
	

	public void setEvent (int id, String user, String userLast, String userFirst, String date, String time, String duration, String hall, String attendees, 
			String eventName, String foodType, String meal, String formality, String drinkType, String entertainment, String status) {
		setEventId(id);
		setUsername(user);
		setUserLast(userLast);
		setUserFirst(userFirst);
		setDate(date);
		setTime(time);
		setDuration(duration);
		setHall(hall);
		setAttendees(attendees);
		setEventName(eventName);
		setFoodType(foodType);
		setMeal(meal);
		setFormality(formality);
		setDrinkType(drinkType);
		setEntertainment(entertainment);
		setStatus(status);
	}
	
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getFormality() {
		return formality;
	}

	public void setFormality(String formality) {
		this.formality = formality;
	}

	public String getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}

	public String getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(String entertainment) {
		this.entertainment = entertainment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getCatererUsername() {
		return catererUsername;
	}

	public void setCatererUsername(String catererUsername) {
		this.catererUsername = catererUsername;
	}

	public String getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(String estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public String getUserLast() {
		return userLast;
	}

	public void setUserLast(String userLast) {
		this.userLast = userLast;
	}

	public String getUserFirst() {
		return userFirst;
	}

	public void setUserFirst(String userFirst) {
		this.userFirst = userFirst;
	}

	public String getCatererLast() {
		return catererLast;
	}

	public void setCatererLast(String catererLast) {
		this.catererLast = catererLast;
	}

	public String getCatererFirst() {
		return catererFirst;
	}

	public void setCatererFirst(String catererFirst) {
		this.catererFirst = catererFirst;
	}
//
//  NOTE: 	The following code is not representative of how this would be coded in an industrial application.
//			We are using this code to maximize the ability of Pit to mutate the code to determine how
//			good the developed test cases are. This course is using this Java backend code as an application
//			of the principles learned in CSE 5321 only.
//	
	
	public void validateEvent (String action, Event event, EventErrorMsgs errorMsgs) {
		if (action.equals("newEvent")) {
			errorMsgs.setEventDateError(validateEventDate(event.getDate(), event.getTime()));
			errorMsgs.setEventTimeError(validateEventTime(event.getDate(), event.getTime()));
		}
		else if (action.equals("saveEvent")) {
			errorMsgs.setDurationError(validateDuration(event.getDate(), event.getTime(), event.getDuration(), event.getHall()));
			errorMsgs.setAttendeeError(validateAttendees(event.getHall(), event.getAttendees()));
			errorMsgs.setEventNameError(validateEventName(event.getEventName()));
		}
		else if (action.equals("reserveE")) {
			errorMsgs.setDurationError(validateDuration(event.getDate(), event.getTime(), event.getDuration(), event.getHall()));
			errorMsgs.setAttendeeError(validateAttendees(event.getHall(), event.getAttendees()));
			errorMsgs.setEventNameError(validateEventName(event.getEventName()));
			
			errorMsgs.setErrorMsg();
			
			if (errorMsgs.getErrorMsg().equals("")) {
				double capacity = EventDAO.hallCapacity(event.getHall());
				double mealCost = EventDAO.mealCost(event.getMeal());
				double attendees = Double.parseDouble(event.getAttendees());
				double duration = Double.parseDouble(event.getDuration());
				double formality = EventDAO.formalMult(event.getFormality());
				double drinkCost = EventDAO.drinkCost(event.getDrinkType());
				double music = (event.getEntertainment().equals("music")?50:0);
				
				double cost = 2*duration*capacity
						+mealCost*attendees*formality
						+drinkCost*attendees + music;
				event.setEstimatedCost("$"+String.format("%.2f", cost));
			}
		}
		else if (action.equals("modifyE") || action.equals("modifyUserE")) {
			errorMsgs.setUserNamesError(validateUserNames(event.getUserLast(), event.getUserFirst()));
			errorMsgs.setEventDateError(validateEventDateReserve(event.getDate(), event.getTime(),event.getUsername()));
			errorMsgs.setEventTimeError(validateEventTime(event.getDate(), event.getTime()));
			errorMsgs.setDurationError(validateDurationUpdate(event.getDate(), event.getTime(), event.getDuration(), event.getHall(), event.getEventId()));
			errorMsgs.setAttendeeError(validateAttendees(event.getHall(), event.getAttendees()));
			errorMsgs.setEventNameError(validateEventName(event.getEventName(), event.getEventId()));
		
			
		}
		else if (action.equals("assignCaterer")) {
			errorMsgs.setCatererError(validateCaterer(event.getCatererLast(), event.getCatererFirst()));
		}
		else if (action.equals("searchEvent") || action.equals("searchCatererEvent") || action.equals("requestEvent") || action.equals("searchUserEvent") ) {
			errorMsgs.setEventDateError(validateEventDate(event.getDate(), event.getTime()));
		}
		else { //action = Reserve
			errorMsgs.setEventDateError(validateEventDateReserve(event.getDate(), event.getTime(), event.getUsername()));
			
		}
		
		errorMsgs.setErrorMsg();
	}

	private String validateUserNames(String last, String first) {
		String result="";
		if (!EventDAO.namesMatch(last, first))
			result = "No name found for expected role";
		return result;
	}
	
	private String validateEventDate(String date, String time) {
		String result="";
		String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String currentTime =
			new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			int dateInt = Integer.parseInt(date.substring(0,4)+date.substring(5,7)+date.substring(8));
			
			int currentInt = Integer.parseInt(currentDate.substring(0,4)+currentDate.substring(5,7)+currentDate.substring(8));
			if (dateInt < currentInt) {
				result = "Date must not be in the past"; 
			}

			else if (dateInt == currentInt && time.compareTo(currentTime) < 0)
				result = "Date/time must not be in the past";
			

		return result;
	}
	private String validateEventDateReserve(String date, String time, String user) {
		String result="";
		String currentDate =
			new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String currentTime =
			new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			int dateInt = Integer.parseInt(date.substring(0,4)+date.substring(5,7)+date.substring(8));
			int currentInt = Integer.parseInt(currentDate.substring(0,4)+currentDate.substring(5,7)+currentDate.substring(8));

			String year=date.substring(0,4);
			String month=date.substring(5,7);
			String datee=date.substring(8);
			
			boolean has5bookings = false;
			int d=Integer.parseInt(datee);
			int m=Integer.parseInt(month);
			int y=Integer.parseInt(year);
			
			LocalDate reserveDate = LocalDate.of(y, m, d);
			LocalDate date1d = reserveDate;
			LocalDate date2d = reserveDate;
			
			for(int i = 0; i < 7; i++) { 
			    date1d = reserveDate.minusDays(6-i); 
			    date2d = reserveDate.plusDays(i);
			    String date1 = date1d.toString();
			    String date2 = date2d.toString();
			    
			    if (EventDAO.eventWeekFull(date1,date2,user) > 4)
			    	has5bookings = true;
			    	
			}
			
			if (dateInt < currentInt)  {
				result = "Date must not be in the past";
			}
			else if (dateInt == currentInt && time.compareTo(currentTime) < 0)
				result = "Date/time must not be in the past";
			
			else if (EventDAO.eventDateFull(date,user) > 1)
				result = "No more than 2 events can be booked per day";
				
			else if (has5bookings)
				result = "No more than 5 events can be booked in a 7 day period";
				
		return result;
	}

	
	
	private String validateEventTime(String date, String time) {
		String result="";
		int hour = 0;
		int minutes = 0;
		hour = Integer.parseInt(time.substring(0,2));
		minutes = Integer.parseInt(time.substring(3,5));

		LocalDate localDate = LocalDate.parse(date);
		int day = localDate.getDayOfWeek().getValue();
			
		if (day == 7 && hour < 12)
			result = "Catering begins at noon on Sundays";
		else if (hour < 7)
			result = "Catering begins at 7 AM";
		else if (!(day == 5 || day == 6) && ((hour > 20 && minutes != 0) || (hour > 21)))
			result = "Your time must be before 9 PM on weekdays";
		
		
		return result;
	}
	
	private String validateDuration(String date, String time, String duration, String hall) {
		String result="";
		double timed = Double.parseDouble(time.substring(0,2)) + Double.parseDouble(time.substring(3,5))/60.0;
		double durd = Double.parseDouble(duration);
		LocalDate localDate = LocalDate.parse(date);
		int day = localDate.getDayOfWeek().getValue();
		ArrayList<Event> list = EventDAO.hallConflict(date, timed, durd, hall);
		for (int i = 0; i < list.size(); i++) {
			String stime = list.get(i).getTime();
			String sdur = list.get(i).getDuration();
			double etime = Double.parseDouble(stime.substring(0,2)) + Double.parseDouble(stime.substring(3,5))/60.0;
			double edur = Double.parseDouble(sdur);
			if (((etime - timed) >= .0 && (timed + durd - etime) >= .0) ||
					 ((etime+edur) - timed) >= .0 && (timed + durd - (etime+edur)  >= .0) || 
					 ((etime+edur) - timed) >= .0 && (timed + durd - etime) >= .0) 
					result = "Hall is already booked at that time";
		}
		if ((!(day == 5 || day == 6) && ((23-timed) < durd)) || ((26-timed) < durd))
			result = "Duration cannot exceed close time";
		
		return result;
	}
		
	private String validateDurationUpdate(String date, String time, String duration, String hall, int id) {
		String result="";
		double timed = Double.parseDouble(time.substring(0,2)) + Double.parseDouble(time.substring(3,5))/60.0;
		double durd = Double.parseDouble(duration);
		LocalDate localDate = LocalDate.parse(date);
		int day = localDate.getDayOfWeek().getValue();
		ArrayList<Event> list = EventDAO.hallConflict(date, timed, durd, hall, id);
		
		for (int i = 0; i < list.size(); i++) {
			String stime = list.get(i).getTime();
			String sdur = list.get(i).getDuration();
			double etime = Double.parseDouble(stime.substring(0,2)) + Double.parseDouble(stime.substring(3,5))/60.0;
			double edur = Double.parseDouble(sdur);
			if (((etime - timed) > .01 && (timed + durd - etime) > .01) ||
					 ((etime+edur) - timed) > .01 && (timed + durd - (etime+edur)  > .01) || 
					 ((etime+edur) - timed) > .01 && (timed + durd - etime) > .01) 
					result = "Hall is already booked at that time";
		}
		if ((!(day == 5 || day == 6) && ((23-timed) < durd)) || ((26-timed) < durd))
			result = "Duration cannot exceed close time";
		return result;
	}
	
	private String validateAttendees(String hall, String attendees) {
		String result = "";
		int cap = EventDAO.hallCapacity(hall);

		if (!isTextAnInteger(attendees))
			result="Estimated attendees must be a number";
		else if(Integer.parseInt(attendees) <= 0) 
			result = "Estimated attendees must be greater than 0";
		else if (Integer.parseInt(attendees) > 100)
			result = "Estimated attendees must be <=100";
		else if (Integer.parseInt(attendees) > cap)
			result = "Chosen hall too small";
		
		return result;
	}
	
	private String validateEventName(String name, int id) {
		String result="";
		if (!stringSize(name,3,29))
			result= "Event name length must be >2 and <30";
		else if (!Character.isUpperCase(name.charAt(0)))
			result = "Event name must start with a capital letter";
		else if (!EventDAO.isUnique(name, id))
			result = "Event names must be unique";
		return result;		
	}
	
	private String validateEventName(String name) {
		String result="";
		if (!stringSize(name,3,29))
			result= "Event name length must be >2 and <30";
		else if (!Character.isUpperCase(name.charAt(0)))
			result = "Event name must start with a capital letter";
		else if (!EventDAO.isUnique(name))
			result = "Event names must be unique";
		return result;		
	}
	
	private String validateCaterer(String last, String first) {
		String result = "";
		
		if (!EventDAO.isCaterer(last, first)) 
			result = "name entered is not a caterer";
		return result;
	}

//	This section is for general purpose methods used internally in this class
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
}
