package Project.model;

public class EventErrorMsgs {

	private String errorMsg;
	private String eventDateError;
	private String eventTimeError;
	private String durationError;
	private String attendeeError;
	private String eventNameError;
	private String catererError;
	private String userNamesError;


	public EventErrorMsgs() {
		this.errorMsg = "";
		this.eventDateError = "";
		this.eventTimeError = "";
		this.durationError = "";
		this.attendeeError = "";
		this.eventNameError = "";
		this.catererError = "";
		this.userNamesError = "";
	}

	public String getErrorMsg() {
		return errorMsg;
	}
//
//  NOTE: 	The following code is not representative of how this would be coded in an industrial application.
//			We are using this code to maximize the ability of Pit to mutate the code to determine how
//			good the developed test cases are. This course is using this Java backend code as an application
//			of the principles learned in CSE 5321 only.
//	
	public void setErrorMsg() {
		if (!eventDateError.equals("") || !eventTimeError.equals("") || !durationError.equals("") || !attendeeError.equals("") ||
				!eventNameError.equals("") || !catererError.equals("") || !userNamesError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	public String getEventDateError() {
		return eventDateError;
	}
	public void setEventDateError(String eventDateError) {
		this.eventDateError = eventDateError;
	}
	public String getEventTimeError() {
		return eventTimeError;
	}

	public void setEventTimeError(String eventTimeError) {
		this.eventTimeError = eventTimeError;
	}
	public String getDurationError() {
		return durationError;
	}

	public void setDurationError(String durationError) {
		this.durationError = durationError;
	}
	public String getAttendeeError() {
		return attendeeError;
	}

	public void setAttendeeError(String attendeeError) {
		this.attendeeError = attendeeError;
	}
	public String getEventNameError() {
		return eventNameError;
	}

	public void setEventNameError(String eventNameError) {
		this.eventNameError = eventNameError;
	}

	public String getCatererError() {
		return catererError;
	}

	public void setCatererError(String catererError) {
		this.catererError = catererError;
	}

	public String getUserNamesError() {
		return userNamesError;
	}

	public void setUserNamesError(String userNamesError) {
		this.userNamesError = userNamesError;
	}

}
