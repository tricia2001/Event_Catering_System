package Project.model;

import java.io.Serializable;
import Project.data.EventDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.time.*;

public class CreditCard implements Serializable{

	private static final long serialVersionUID = 3L;
	private String cardNo;
	private String cardPin;
	private String cardExp;
		
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardPin() {
		return cardPin;
	}

	public void setCardPin(String cardPin) {
		this.cardPin = cardPin;
	}

	public String getCardExp() {
		return cardExp;
	}

	public void setCardExp(String cardExp) {
		this.cardExp = cardExp;
	}
	
	public void setCard (String cardNo, String cardPin, String cardExp) {
		setCardNo(cardNo);
		setCardPin(cardPin);
		setCardExp(cardExp);
	}
	
	public void validateCard (CreditCard card, CardErrorMsgs errorMsgs) {
		errorMsgs.setCardNumberError(validateCardNo(card.getCardNo()));
		errorMsgs.setCardPinError(validateCardPin(card.getCardPin()));
		errorMsgs.setCardExpError(validateCardExp(card.getCardExp()));
		
		errorMsgs.setErrorMsg();
	}

	private String validateCardNo(String number) {
		String result="";
		if(!isTextAnInteger(number)) 
			result = "Credit card number must be a number";
		else if(number.length() != 16)
			result = "Credit card number must be 16 digits";
		
		return result;
	}
	
	private String validateCardPin(String pin) {
		String result="";
		if(!isTextAnInteger(pin)) 
			result = "Credit card pin must be a number";
		else if(pin.length() != 4)
			result = "Credit card pin must be 4 digits";
		
		return result;
	}
	
	private String validateCardExp(String exp) {
		String result="";
		String current = new SimpleDateFormat("yyMM").format(Calendar.getInstance().getTime());
		exp = exp.substring(2)+exp.substring(0,2);
		if(!isTextAnInteger(exp)) 
			result = "Credit card exp date must be a number";
		else if(exp.length() != 4)
			result = "Credit card exp date must be 4 digits";
		else if(exp.compareTo(current) < 0)
			result = "Credit card exp date must not be in the past";
		
		return result;
	}

//	This section is for general purpose methods used internally in this class
	
	
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
