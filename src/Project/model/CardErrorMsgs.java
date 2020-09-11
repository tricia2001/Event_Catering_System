package Project.model;

public class CardErrorMsgs {

	private String errorMsg;
	private String cardNumberError;
	private String cardPinError;
	private String cardExpError;
	
	public CardErrorMsgs() {
		this.errorMsg = "";
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
		if (!cardNumberError.equals("") || !cardPinError.equals("") || !cardExpError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}
	public String getCardNumberError() {
		return cardNumberError;
	}

	public void setCardNumberError(String cardNumberError) {
		this.cardNumberError = cardNumberError;
	}

	public String getCardPinError() {
		return cardPinError;
	}

	public void setCardPinError(String cardPinError) {
		this.cardPinError = cardPinError;
	}

	public String getCardExpError() {
		return cardExpError;
	}

	public void setCardExpError(String cardExpError) {
		this.cardExpError = cardExpError;
	}

}
