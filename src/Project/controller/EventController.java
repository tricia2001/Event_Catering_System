package Project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Project.data.EventDAO;
import Project.data.UserDAO;
import Project.model.*;
import Project.model.Event;
import Project.controller.UserController;
import Project.model.CreditCard;
import Project.model.CardErrorMsgs;
import Project.model.User;
import java.util.logging.Logger;

@WebServlet("/EventController")
public class EventController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
     
	
	private void getEventParam (HttpServletRequest request, Event event) {
		event.setEvent(EventDAO.getNewId(),request.getParameter("user"), request.getParameter("last"), request.getParameter("first"),
				request.getParameter("date"), request.getParameter("time"),request.getParameter("duration").substring(0,1),
				request.getParameter("hall"), request.getParameter("attendees"), fixApostrophes(request.getParameter("eventName")), request.getParameter("food"),
				request.getParameter("meal"), request.getParameter("formality"), request.getParameter("drinkType"), request.getParameter("entertainment"),
				"saved");  
	}
	private void modifyEventParam (HttpServletRequest request, Event event) {
		event.setEvent(Integer.parseInt(request.getParameter("id")),request.getParameter("user"), request.getParameter("last"), request.getParameter("first"),
				request.getParameter("date"), request.getParameter("time"),request.getParameter("duration").substring(0,1),
				request.getParameter("hall"), request.getParameter("attendees"), fixApostrophes(request.getParameter("eventName")), request.getParameter("food"),
				request.getParameter("meal"), request.getParameter("formality"), request.getParameter("drinkType"), request.getParameter("entertainment"),
				"reserved");  
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		Event event = new Event();
		EventErrorMsgs EerrorMsgs = new EventErrorMsgs();
		int selectedEventIndex;
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("mgrSearch")) {
			String currentDate =
			new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String currentTime =
			new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			event.setDate(currentDate);
			event.setTime(currentTime);
			session.setAttribute("event", event);
			url="/CatererMgrSearch.jsp";
		}
		
		else if (action.equalsIgnoreCase("userSearch")) {
			if (request.getParameter("requestEvent")!=null) {
				String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String currentTime =
				new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
				event.setDate(currentDate);
				event.setTime(currentTime);
				event.setUsername(request.getParameter("user"));
				session.setAttribute("event", event);
				url="/RequestEventDates.jsp";
			}
			else  { //viewEvents
				String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String currentTime =
				new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
				event.setDate(currentDate);
				event.setTime(currentTime);
				event.setUsername(request.getParameter("user"));
				session.setAttribute("event", event);
				url="/SearchUserEventSummary.jsp";
			}
		}

		else if (action.equalsIgnoreCase("catererSearch")) {
			String currentDate =
			new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String currentTime =
			new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			String username = request.getParameter("user");
			event.setDate(currentDate);
			event.setTime(currentTime);
			session.setAttribute("event", event);
			session.setAttribute("username",username);
			url="/CatererStaffSearch.jsp";
		}

		else if (action.equalsIgnoreCase("modifyEvent")) {
			int eventID = Integer.parseInt(request.getParameter("id"));
			event = EventDAO.idSearch(eventID);
			String date = request.getParameter("date1");   
			String time = request.getParameter("time1");

			Event dtevent = new Event();
			dtevent.setDate(date);
			dtevent.setTime(time);
			if (request.getParameter("modifyButton")!=null) {
				session.setAttribute("event", dtevent);
				session.setAttribute("EVENTS", event);
				url="/CatererMgrModifyEvent.jsp";
			}
			else { //assign button pressed
				session.setAttribute("event", dtevent);
				session.setAttribute("EVENTS",  event);
				url = "/AssignCateringStaff.jsp";
			
			}
		}
		
		else if (action.equalsIgnoreCase("modifyUserEvent")) {
			int eventID = Integer.parseInt(request.getParameter("id"));
			event = EventDAO.idSearch(eventID);
			session.setAttribute("EVENTS", event);
			url="/ModifyUserEvent.jsp";
			
		}
		
		else if (action.equalsIgnoreCase("modifyE")) {
			modifyEventParam(request, event);
			event.validateEvent(action, event, EerrorMsgs);
			String date = request.getParameter("date1");   
			String time = request.getParameter("time1");
			Event dtevent = new Event();
			dtevent.setDate(date);
			dtevent.setTime(time);
			
			if (!EerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("EVENTS", event);
				session.setAttribute("event", dtevent);
				session.setAttribute("errorMsgs",EerrorMsgs);
				url="/CatererMgrModifyEvent.jsp";
			}
			else {// if no error messages
				EventDAO.updateEvent(event);
				event = EventDAO.idSearch(event.getEventId());
				EventErrorMsgs SerrorMsgs = new EventErrorMsgs();
				session.setAttribute("event", dtevent);
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs", SerrorMsgs);
				url="/CatererMgrSelectedEvent.jsp";
			}
		}
		
		else if (action.equalsIgnoreCase("modifyUserE")) {
			modifyEventParam(request, event);
			event.validateEvent(action, event, EerrorMsgs);
			if (!EerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs",EerrorMsgs);
				url="/ModifyUserEvent.jsp";
			}
			else {// if no error messages
				EventDAO.updateEvent(event);
				event = EventDAO.idSearch(event.getEventId());
				EventErrorMsgs SerrorMsgs = new EventErrorMsgs();
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs", SerrorMsgs);
				url="/UserSelectedEvent.jsp";
			}
		}
		else if (action.equalsIgnoreCase("Reserve")) {
			event.setDate(request.getParameter("date"));
			event.setTime(request.getParameter("time"));
			event.setUsername(request.getParameter("user"));
			event.validateEvent(action, event, EerrorMsgs);
			if (!EerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs",EerrorMsgs);
				url="/RequestEventDates.jsp";
			}
			else {
				EventErrorMsgs SerrorMsgs = new EventErrorMsgs();
				
				session.setAttribute("errorMsgs", SerrorMsgs);
				event.setUserLast(UserDAO.getLast(event.getUsername()));
				event.setUserFirst(UserDAO.getFirst(event.getUsername()));
				session.setAttribute("EVENTS", event);
				url="/RequestEventInputs.jsp";
			}
		}
		else if (action.equalsIgnoreCase("reserveE")) {
			getEventParam(request, event);
			event.validateEvent(action, event, EerrorMsgs);
			
			if (!EerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs",EerrorMsgs);
				url="/RequestEventInputs.jsp";
			}
			else {// if no error messages
				
				
				EventDAO.insertEvent(event);
				CardErrorMsgs CerrorMsgs = new CardErrorMsgs();
				session.setAttribute("cardpin","");
				session.setAttribute("cardno","");
				session.setAttribute("cardexp","");
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				
				url="/ReserveUserEvent.jsp";
			}
		}

		else if (action.equalsIgnoreCase("reserveUsrEvent")) {
			String cardno = request.getParameter("cardno");
			String cardpin = request.getParameter("cardpin");
			String cardexp = request.getParameter("cardexp");
			CreditCard card = new CreditCard();
			CardErrorMsgs CerrorMsgs = new CardErrorMsgs();
			card.setCard(cardno, cardpin, cardexp);
			int id = Integer.parseInt(request.getParameter("id"));
			String cost = request.getParameter("cost");
			event.setEventId(id);
			event.setEstimatedCost(cost);
			card.validateCard(card,CerrorMsgs);
			if (!CerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs",CerrorMsgs);
				session.setAttribute("cardno", cardno);
				session.setAttribute("cardpin", cardpin);
				session.setAttribute("cardexp", cardexp);
				url="/ReserveUserEvent.jsp";
			}
			else {// if no error messages
				EventDAO.reserveEvent(id);
			session.setAttribute("EVENTS", EventDAO.idSearch(id));
			url="/UserSelectedEvent.jsp";
			}
		}


		else if (action.equalsIgnoreCase("assignCaterer")) {
			String first = request.getParameter("fname");
			String last = request.getParameter("lname");
			int id = Integer.parseInt(request.getParameter("id"));
			session.removeAttribute("errorMsgs");
			event.setCatererLast(last);
			event.setCatererFirst(first);
			event.setEventId(id);
			event.validateEvent(action, event, EerrorMsgs);String date = request.getParameter("date1");   
			String time = request.getParameter("time1");

			Event dtevent = new Event();
			dtevent.setDate(date);
			dtevent.setTime(time);
			if (!EerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("event", dtevent);
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs",EerrorMsgs);
				url="/AssignCateringStaff.jsp";
			}
			else {// if no error messages
				EventDAO.assignStaff(last, first, id);
				event = EventDAO.idSearch(id);
				EventErrorMsgs SerrorMsgs = new EventErrorMsgs();
				session.setAttribute("event", dtevent);
				session.setAttribute("EVENTS", event);
				session.setAttribute("errorMsgs", SerrorMsgs);
				url="/CatererMgrSelectedEvent.jsp";
			}
			
		}
		
		//search event for caterer staff
		else if (action.equalsIgnoreCase("searchCatererEvent") ) {
			String date = request.getParameter("date");   
			String time = request.getParameter("time");
			String user = request.getParameter("user");
			session.removeAttribute("errorMsgs");
			event.setDate(date);
			event.setTime(time);
			event.setUsername(user);
			event.validateEvent(action, event, EerrorMsgs);

			ArrayList<Event> eventInDB = new ArrayList<Event>();
			if (EerrorMsgs.getErrorMsg().equals("")) {
				eventInDB=EventDAO.listMyCatererEvents(date, time, user);
				session.setAttribute("EVENTS", eventInDB);	
				session.removeAttribute("event");
				event.setDate(date);
				event.setTime(time);
				event.setUsername(user);
				session.setAttribute("event", event);
				url="/CatererStaffEventList.jsp";
			}
			
			else {
				String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String currentTime =
				new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
				event.setDate(currentDate);
				event.setTime(currentTime);
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", EerrorMsgs);
				url="/CatererStaffSearch.jsp";			
			}
		}
		
		// search event for manager
		else if (action.equalsIgnoreCase("searchMgrEvent") ) {
			String date = request.getParameter("date");   
			String time = request.getParameter("time");
			session.removeAttribute("errorMsgs");
			event.setDate(date);
			event.setTime(time);
			event.validateEvent(action, event, EerrorMsgs);

			ArrayList<Event> eventInDB = new ArrayList<Event>();
			if (EerrorMsgs.getErrorMsg().equals("")) {
				eventInDB=EventDAO.listCatererEvents(date, time);
				session.setAttribute("EVENTS", eventInDB);	
				session.removeAttribute("event");
				event.setDate(date);
				event.setTime(time);
				session.setAttribute("event", event);
				url="/CatererMgrEventList.jsp";
			}
			
			else {
				String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String currentTime =
				new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
				event.setDate(currentDate);
				event.setTime(currentTime);
				session.setAttribute("event", event);
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", EerrorMsgs);
				url="/CatererMgrSearch.jsp";			
			}
		}
		
		else if (action.equalsIgnoreCase("searchUserEvent") ) {
			String date = request.getParameter("date");   
			String time = request.getParameter("time");
			String username = request.getParameter("user");
			session.removeAttribute("errorMsgs");
			event.setDate(date);
			event.setTime(time);
			event.validateEvent(action, event, EerrorMsgs);

			ArrayList<Event> eventInDB = new ArrayList<Event>();
			if (EerrorMsgs.getErrorMsg().equals("")) {
				eventInDB=EventDAO.listMyUserEvents(date, time, username);
				session.setAttribute("EVENTS", eventInDB);
				session.removeAttribute("event");
				event.setDate(date);
				event.setTime(time);
				event.setUsername(username);
				session.setAttribute("event", event);
				url="/ListUserEventSummary.jsp";
			}
			
			else {
				String currentDate =
				new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				String currentTime =
				new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
				event.setDate(currentDate);
				event.setTime(currentTime);
				session.setAttribute("event", event);
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", EerrorMsgs);
				url="/SearchUserEventSummary.jsp";			
			}
		}

		else if (action.equalsIgnoreCase("listMySpecificEvent")){ 
			ArrayList<Event> eventInDB = new ArrayList<Event>();
			Event selectedEvent = new Event();
			if (request.getParameter("radioEvent")!=null) {
				selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1;
				String date = request.getParameter("date");   
				String time = request.getParameter("time");
				String user = request.getParameter("user");
				eventInDB=EventDAO.listMyCatererEvents(date, time, user); 
				session.setAttribute("EVENTS", eventInDB.get(selectedEventIndex));
				url="/CatererStaffSelectedEvent.jsp";					
			}
			else { // determine if Submit button was clicked without selecting a event
					
					event.setDate(request.getParameter("date"));
					event.setTime(request.getParameter("time"));
					event.setUsername(request.getParameter("user"));
					session.setAttribute("event", event);
					String errorMsgs =  "Please select an Event";
					session.setAttribute("errorMsgs",errorMsgs);
					url="/CatererStaffEventList.jsp";					
				
				
			}
		}
		
		else if (action.equalsIgnoreCase("listSpecificUserEvent") ) { //action=listSpecificUserEvent
			ArrayList<Event> eventInDB = new ArrayList<Event>();
			Event selectedEvent = new Event();
			String username = request.getParameter("user");
			if (request.getParameter("radioEvent")!=null) {
				selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1;
				String date = request.getParameter("date");   
				String time = request.getParameter("time");
				eventInDB=EventDAO.listMyUserEvents(date, time, username); 
				session.setAttribute("EVENTS", eventInDB.get(selectedEventIndex));
				url="/UserSelectedEvent.jsp";					
			}
			else { // determine if Submit button was clicked without selecting a event
				
					
					event.setDate(request.getParameter("date"));
					event.setTime(request.getParameter("time"));
					event.setUsername(request.getParameter("user"));
					session.setAttribute("event", event);
					String errorMsgs =  "Please select an Event";
					session.setAttribute("errorMsgs",errorMsgs);
					url="/ListUserEventSummary.jsp";
			}
		}
		

		else { //action=listSpecificEvent
			ArrayList<Event> eventInDB = new ArrayList<Event>();
			if (request.getParameter("radioEvent")!=null) { 
				selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1;
				String date = request.getParameter("date");   
				String time = request.getParameter("time");
				Event dtevent = new Event();
				dtevent.setDate(date);
				dtevent.setTime(time);
				eventInDB=EventDAO.listCatererEvents(date, time); 
				session.setAttribute("EVENTS", eventInDB.get(selectedEventIndex));
				session.setAttribute("event", dtevent);
				url="/CatererMgrSelectedEvent.jsp";					
			}
			else { // Submit button was clicked without selecting an event
					event.setDate(request.getParameter("date"));
					event.setTime(request.getParameter("time"));
					session.setAttribute("event", event);
					String errorMsgs =  "Please select an Event";
					session.setAttribute("errorMsgs",errorMsgs);
					url="/CatererMgrEventList.jsp";					
				
			}
			
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
	

	private String fixApostrophes(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '\'') {
				string = string.substring(0,i)+"'"+string.substring(i);
				i++;
			}
		}
		
		return string;
	}
}

