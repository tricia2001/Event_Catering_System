package Project.model;

public class UserErrorMsgs {

	private String errorMsg;
	private String usernameError;
	private String passwordError;
	private String lastError;
	private String firstError;
	private String roleError;
	private String utaIdError;
	private String phoneError;
	private String emailError;
	private String streetNoError;
	private String streetNameError;
	private String cityError;
	private String stateError;
	private String zipError;
	
	
	public UserErrorMsgs() {
		this.errorMsg = "";
// Comment out the following to get PIT coverage even though it is not per Oracle
		this.usernameError = "";
		this.passwordError = "";
		this.lastError = "";
		this.firstError = "";
		this.roleError = "";
		this.utaIdError = "";
		this.phoneError = "";
		this.emailError = "";
		this.streetNoError = "";
		this.streetNameError = "";
		this.cityError = "";
		this.stateError = "";
		this.zipError = "";
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
		if (!usernameError.equals("") || !passwordError.equals("") || !lastError.equals("") || !firstError.equals("") ||
				!roleError.equals("") || !utaIdError.equals("") || !phoneError.equals("") || !emailError.equals("") ||
				!streetNoError.equals("") || !streetNameError.equals("") || !cityError.equals("") ||
				!stateError.equals("") || !zipError.equals(""))
			errorMsg = "Please correct the following errors";
	}
	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getLastError() {
		return lastError;
	}

	public void setLastError(String lastError) {
		this.lastError = lastError;
	}

	public String getFirstError() {
		return firstError;
	}

	public void setFirstError(String firstError) {
		this.firstError = firstError;
	}

	public String getRoleError() {
		return roleError;
	}

	public void setRoleError(String roleError) {
		this.roleError = roleError;
	}

	public String getUtaIdError() {
		return utaIdError;
	}

	public void setUtaIdError(String utaIdError) {
		this.utaIdError = utaIdError;
	}

	public String getPhoneError() {
		return phoneError;
	}

	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getStreetNoError() {
		return streetNoError;
	}

	public void setStreetNoError(String streetNoError) {
		this.streetNoError = streetNoError;
	}

	public String getStreetNameError() {
		return streetNameError;
	}

	public void setStreetNameError(String streetNameError) {
		this.streetNameError = streetNameError;
	}

	public String getCityError() {
		return cityError;
	}

	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

	public String getStateError() {
		return stateError;
	}

	public void setStateError(String stateError) {
		this.stateError = stateError;
	}

	public String getZipError() {
		return zipError;
	}

	public void setZipError(String zipError) {
		this.zipError = zipError;
	}

	
}
