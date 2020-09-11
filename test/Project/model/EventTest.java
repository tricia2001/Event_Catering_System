package Project.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class EventTest {
	
	Event event;
	EventErrorMsgs Eerrors;

	@Before
	public void setUp() throws Exception {
		event = new Event();
		Eerrors = new EventErrorMsgs();
	}

	@Test
	@FileParameters("test/Project/model/Event_test_cases.csv")
	public void test(int testcase, String action, int eventId, String username,
			String userLast, String userFirst, String date, String time, String duration,
			String hall, String attendees, String eventName, String foodType, String meal,
			String formality, String drinkType, String entertainment, String status,
			String catererUsername, String catererLast, String catererFirst, String estimatedCost,
			String errorMsg, String eventDateError, String eventTimeError, String durationError,
			String attendeeError, String eventNameError, String catererError, String userNamesError) {
		if (date.equals("yesterday")) {
			date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			date = date.substring(0,8)+"0"+(Integer.parseInt(date.substring(8))-1);
		}
		if (date.equals("today")) {
			date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

			
		}
		event.setEvent(eventId, username, userLast, userFirst, date, time, duration, hall, attendees,
				eventName, foodType, meal, formality, drinkType, entertainment, status);
		event.setCatererUsername(catererUsername);
		event.setCatererLast(catererLast);
		event.setCatererFirst(catererFirst);
		
		event.validateEvent(action, event, Eerrors);
		assertTrue(errorMsg.equals(Eerrors.getErrorMsg()));
		assertTrue(eventDateError.equals(Eerrors.getEventDateError()));
		assertTrue(eventTimeError.equals(Eerrors.getEventTimeError()));
		assertTrue(durationError.equals(Eerrors.getDurationError()));
		assertTrue(attendeeError.equals(Eerrors.getAttendeeError()));
		assertTrue(eventNameError.equals(Eerrors.getEventNameError()));
		assertTrue(catererError.equals(Eerrors.getCatererError()));
		assertTrue(userNamesError.equals(Eerrors.getUserNamesError()));
		if (!estimatedCost.equals(""))
			assertTrue(event.getEstimatedCost().equals("$"+estimatedCost));
		assertTrue(status.equals(event.getStatus()));
		assertTrue(catererUsername.equals(event.getCatererUsername()));
		assertTrue(foodType.equals(event.getFoodType()));
	}

}
