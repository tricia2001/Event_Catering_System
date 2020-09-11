package Project.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.*;

@RunWith(JUnitParamsRunner.class)
public class CreditCardTest {
	
	CreditCard card;
	CardErrorMsgs Cerrors;

	@Before
	public void setUp() throws Exception {
		card = new CreditCard();
		Cerrors = new CardErrorMsgs();
	}

	@Test
	@FileParameters("test/Project/model/Card_test_cases.csv")
	public void test(int testcase, String cardNo, String cardPin, String cardExp, String errorMsg,
			String cardNoError, String cardPinError, String cardExpError) {
		if (cardExp.equals("last")) {
			cardExp = new SimpleDateFormat("MMyy").format(Calendar.getInstance().getTime());
			cardExp = "0"+(Integer.parseInt(cardExp.substring(0,2))-1)+cardExp.substring(2);
		}
		if (cardExp.equals("this"))
			cardExp = new SimpleDateFormat("MMyy").format(Calendar.getInstance().getTime());
		
		card.setCard(cardNo, cardPin, cardExp);
		
		card.validateCard(card, Cerrors);
		
		assertTrue(errorMsg.equals(Cerrors.getErrorMsg()));
		assertTrue(cardNoError.equals(Cerrors.getCardNumberError()));
		assertTrue(cardPinError.equals(Cerrors.getCardPinError()));
		assertTrue(cardExpError.equals(Cerrors.getCardExpError()));
		
	}

}
