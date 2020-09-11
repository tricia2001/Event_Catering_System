package Project.model;

import java.io.Serializable;
import Project.data.UserDAO;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {

	private static final long serialVersionUID = 2L;
	private String username;
	private String lastname;
	private String firstname;
	private String role;
	private String password;
	private String utaId;
	private String phone;
	private String email;
	private String streetName;
	private String streetNumber;
	private String city;
	private String state;
	private String zipcode;
	
	
	
	public void setUser (String username, String password, String lastname, String firstname, String role, String utaId, 
			String phone, String email, String streetNumber, String streetName, String city, String state, String zipcode) {
		this.setUsername(username);
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setRole(role);
		this.setPassword(password);
		this.setUtaId(utaId);
		this.setPhone(phone);
		this.setEmail(email);
		this.setStreetName(streetName);
		this.setCity(city);
		this.setState(state);
		this.setZipcode(zipcode);
		this.setStreetNumber(streetNumber);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUtaId() {
		return utaId;
	}
	
	public void setUtaId(String utaId) {
		this.utaId = utaId;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public void validateUser (String action, User user, UserErrorMsgs errorMsgs) {
		if (action.equals("register")) {
			errorMsgs.setUsernameError(validateUsernameRegister(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordRegister(user.getPassword()));
			errorMsgs.setLastError(validateLastName(user.getLastname()));
			errorMsgs.setFirstError(validateFirstName(user.getFirstname()));
			errorMsgs.setRoleError(validateRole(user.getRole()));
			errorMsgs.setUtaIdError(validateUtaId(user.getUtaId()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setStreetNoError(validateStreetNo(user.getStreetNumber()));
			errorMsgs.setStreetNameError(validateStreetName(user.getStreetName()));
			errorMsgs.setCityError(validateCity(user.getCity()));
			errorMsgs.setStateError(validateState(user.getState()));
			errorMsgs.setZipError(validateZip(user.getZipcode()));
		}
		else if (action.equals("updateUser")) {
			errorMsgs.setUsernameError(validateUsernameUpdate(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordRegister(user.getPassword()));
			errorMsgs.setLastError(validateLastName(user.getLastname()));
			errorMsgs.setFirstError(validateFirstName(user.getFirstname()));
			errorMsgs.setUtaIdError(validateUtaIdUpdate(user.getUtaId()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setStreetNoError(validateStreetNo(user.getStreetNumber()));
			errorMsgs.setStreetNameError(validateStreetName(user.getStreetName()));
			errorMsgs.setCityError(validateCity(user.getCity()));
			errorMsgs.setStateError(validateState(user.getState()));
			errorMsgs.setZipError(validateZip(user.getZipcode()));
		}
		else if (action.equals("login")) {
			errorMsgs.setUsernameError(validateUsernameLogin(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordLogin(user.getUsername(),user.getPassword()));
		}
		else if (action.equals("searchUser")) { 
				errorMsgs.setLastError(validateLastNameSearch(user.getLastname()));
		}
		else if(action.equals("modifyUser")) {
			errorMsgs.setUsernameError(validateUsernameUpdate(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordRegister(user.getPassword()));
			errorMsgs.setLastError(validateLastName(user.getLastname()));
			errorMsgs.setFirstError(validateFirstName(user.getFirstname()));
			errorMsgs.setRoleError(validateRole(user.getRole()));
			errorMsgs.setUtaIdError(validateUtaIdUpdate(user.getUtaId()));
			errorMsgs.setPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setEmailError(validateEmail(user.getEmail()));
			errorMsgs.setStreetNoError(validateStreetNo(user.getStreetNumber()));
			errorMsgs.setStreetNameError(validateStreetName(user.getStreetName()));
			errorMsgs.setCityError(validateCity(user.getCity()));
			errorMsgs.setStateError(validateState(user.getState()));
			errorMsgs.setZipError(validateZip(user.getZipcode()));
		}
		
		errorMsgs.setErrorMsg();
	}
	
	private String validateUsernameLogin(String username) {
		String result = "";
		
		if(!UserDAO.duplicateUser(username))
			result = "Username not found in the system";
		return result;
	}
	
	private String validatePasswordLogin(String username, String password) {
		String result = "";
		if(!UserDAO.passwordMatch(username, password))
			result = "Password does not match the username";
		return result;
	}
	
	private String validateUsernameRegister(String username) {
		String result = "";
		if (!stringSize(username,5,20))
			result = "Username length must be >4 and <=20";
		else if(!Character.isLetter(username.charAt(0)))
			result = "Username must start with a letter";
		else if (specialCharacter(username))
			result = "Username cannot contain special characters";
		else if (UserDAO.duplicateUser(username))
				result = "Username already in database";
		return result;
	}
	
	private String validateUsernameUpdate(String username) {
		String result = "";
		if (!stringSize(username,5,20))
			result = "Username length must be >4 and <=20";
		else if(!Character.isLetter(username.charAt(0)))
			result = "Username must start with a letter";
		else if (specialCharacter(username))
			result = "Username cannot contain special characters";
		return result;
	}
	
	private String validatePasswordRegister(String password) {
		String result = "";
		
	 
	     
	     boolean lowercase = false;
	     boolean uppercase = false;
	     boolean digit = false;
	     boolean special = false;
	     
	    if(!stringSize(password, 8, 29))
	    {
			  result = "Password must be between 8 and 29 characters";
		}
	    else {
		    for ( int i = 0; i < password.length(); i++) {
		    	char a = password.charAt(i);
		    	if(Character.isDigit(a))
		    		digit = true;
		    	else if (Character.isUpperCase(a))
		    		uppercase = true;
		    	else if (Character.isLowerCase(a))
		    		lowercase = true;
		    	else if (a == '@' || a == '#' || a == '$' || a == '%' || a == '^' || a == '&' || a == '*' || a == '!')
		    		special = true;
		    }
		    if(!lowercase)
		    {
				  result = "Password must contain a lowercase character";
			}	
		    else if(!digit)
		    {
				  result = "Password must contain a digit";
			}
		    else if(!uppercase)
		    {
				  result = "Password must contain a uppercase character";
			}
		    else if(!special)
		    {
				  result = "Password must contain a special character";
			}
	    }
		return result;
	}
	
	private String validateLastName(String last) {
		String result = "";
		
		if (!stringSize(last,3,29))
			result = "Last name length must be >2 and <30";	
		else if (isTextAnInteger(last))
			result = "Last name cannot be a number";
		else if(!Character.isUpperCase(last.charAt(0)))
			result = "Last name must start with a capital letter";
		else if (specialCharacter(last))
			result = "Last name cannot contain special characters";
		return result;
	}
	
	private String validateLastNameSearch(String last) {
		String result = "";
		
		if (UserDAO.searchUserwithLastname(last).isEmpty())
			result = "No matching user in the database";
		return result;
	}
	
	private String validateFirstName(String first) {
		String result = "";

		if (!stringSize(first,3,29))
			result = "First name length must be >2 and <30";
		else if (isTextAnInteger(first))
			result = "First name cannot be a number";
		else if(!Character.isUpperCase(first.charAt(0)))
			result = "First name must start with a capital letter";		
		else if (specialCharacter(first))
			result = "First name cannot contain special characters";
		return result;
	}
	
	private String validateRole(String role) {
		String result = "";
		
		if (role.equals("Admin") || role.equals("Caterer Manager")) {
			if(UserDAO.duplicateRole(role))
				result = "System can only have one "+role;
		}
		return result;
	}
	
	private String validatePhone(String phone) {
		String result = "";
		
		if(phone.length() != 10)
			result = "Phone number must have 10 digits";
		else if (!isTextAnInteger(phone))
			result = "Phone number must be numeric";
		
		return result;
	}
	
	private String validateUtaId(String id) {
		String result = "";

		if(id.length() != 10)
			result = "UTA Id must have a length of 10";
		else if (!isTextAnInteger(id))
			result = "UTA Id must be numeric";
		else if (UserDAO.duplicateId(id))
			result = "UTA Id already in use";		
		
		return result;
	}
	
	private String validateUtaIdUpdate(String id) {
		String result = "";

		if(id.length() != 10)
			result = "UTA Id must have a length of 10";
		else if (!isTextAnInteger(id))
			result = "UTA Id must be numeric";	
		
		return result;
	}
	
	private String validateEmail(String email) {
		String result = "";

		if(!stringSize(email,7,45))
			result = "Email address must be between 7 and 45 characters long";
		else if (!email.contains("@"))
			result = "Email address needs to contain @";
		else {
			String domain = email.substring(email.length()-4);
			if (!(domain.equals(".com") || domain.equals(".gov") || domain.equals(".edu") ||
					domain.equals(".org") || domain.equals(".mil") || domain.equals(".net")))
				result = "Invalid domain name";
		}
					
		
		return result;
	}
	
	private String validateStreetNo(String number) {
		String result = "";
		if (!stringSize(number,1,6))
			result = "Street number length must be >0 and <7";
		else if (!isTextAnInteger(number))
			result = "Street number must be numeric";
		else if(Integer.parseInt(number) <= 0)
			result = "Street number must be >0";		
		
		return result;
	}
	
	private String validateStreetName(String name) {
		String result = "";

		if(name.length()==0)
			result = "Street name length must be greater than zero";
		else if (isTextAnInteger(name))
			result = "Street name must be non-numeric";
		else if (name.length() >=40)
			result = "Street name length must be less than 40";		
		
		return result;
	}
	
	private String validateCity(String city) {
		String result = "";

		if (!stringSize(city,3,29))
			result = "City length must be >2 and <30";	
		else if (isTextAnInteger(city))
			result = "City cannot be a number";
		else if(!Character.isUpperCase(city.charAt(0)))
			result = "City must start with a capital letter";	
		else if (specialCharacter(city))
			result = "City cannot contain special characters";
		return result;
	}
	
	private String validateState(String state) {
		String result = "";
		List<String> states = Arrays.asList("AL","AK","AS","AZ","AR","CA","CO","CT","DC","DE","FL",
				        "GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD",
				        "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY",
				        "NC","ND","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX",
				        "UT","VT","VA","WA","WV","WI","WY");	

		if(state.length()!=2)
			result = "must be a 2 letter abbreviation";
		else if (isTextAnInteger(state))
			result = "State must be non-numeric";
		else if (!states.contains(state))
			result = "State abbreviation not found";		
		
		return result;
	}
	
	private String validateZip(String zip) {
		String result = "";
		
		if(zip.length() != 5)
			result = "Zipcode must have a length of 5";	
		else if (!isTextAnInteger(zip))
			result = "Zipcode must be numeric";		
		
		return result;
	}
	
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
	private boolean specialCharacter(String string) {
		boolean result = false;
		for (int i = 1; i < string.length(); i++) {
			if (!(Character.isLetter(string.charAt(i)) || Character.isDigit(string.charAt(i)) || string.charAt(i) == ' '))
				result = true;
		}
		return result;
	}
	
}
