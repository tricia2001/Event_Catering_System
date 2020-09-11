package Project.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	
	User user;
	UserErrorMsgs Uerrors;

	@Before
	public void setUp() throws Exception {
		user = new User();
		Uerrors = new UserErrorMsgs();
	}

	@Test
	@FileParameters("test/Project/model/User_test_cases.csv")
	public void test(int testcase, String action, String username,
			String userLast, String userFirst, String role, String Password,
			String UtaID, String phoneNum, String Email,
			String streetNumber, String streetName, String city, String state, String zipCode,
			String errorMsg, String UsernameError, String LastError, String FirstError,
			String PasswordError, String RoleError, String UtaIdError, String EmailError,
			String PhoneError, String StreetNoError, String StreetNameError, String CityError,
			String StateError, String ZipError) {
		
		
		
		
		user.setUser(username, Password, userLast, userFirst, role, UtaID, phoneNum,
				Email, streetNumber, streetName, city, state, zipCode);
		
		
		user.validateUser(action, user, Uerrors);
		
		assertTrue(errorMsg.equals(Uerrors.getErrorMsg()));
		assertTrue(UsernameError.equals(Uerrors.getUsernameError()));
		assertTrue(PasswordError.equals(Uerrors.getPasswordError()));
		assertTrue(RoleError.equals(Uerrors.getRoleError()));
		assertTrue(PhoneError.equals(Uerrors.getPhoneError()));
		assertTrue(StreetNoError.equals(Uerrors.getStreetNoError()));
		assertTrue(StreetNameError.equals(Uerrors.getStreetNameError()));
		assertTrue(CityError.equals(Uerrors.getCityError()));
		assertTrue(StateError.equals(Uerrors.getStateError()));
		assertTrue(ZipError.equals(Uerrors.getZipError()));
		assertTrue(LastError.equals(Uerrors.getLastError()));
		assertTrue(FirstError.equals(Uerrors.getFirstError()));
		assertTrue(UtaIdError.equals(Uerrors.getUtaIdError()));
		assertTrue(EmailError.equals(Uerrors.getEmailError()));
		assertTrue(username.equals(user.getUsername()));
		if (!(userLast.equals("NULL")))
			assertTrue(userLast.equals(user.getLastname()));
		assertTrue(role.equals(user.getRole()));
		assertTrue(Password.equals(user.getPassword()));
		assertTrue(UtaID.equals(user.getUtaId()));
		assertTrue(phoneNum.equals(user.getPhone()));
		assertTrue(Email.equals(user.getEmail()));
		assertTrue(streetName.equals(user.getStreetName()));
		assertTrue(streetNumber.equals(user.getStreetNumber()));
		assertTrue(city.equals(user.getCity()));
		assertTrue(state.equals(user.getState()));
		assertTrue(zipCode.equals(user.getZipcode()));
	}

}
