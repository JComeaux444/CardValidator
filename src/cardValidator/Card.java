package cardValidator;

public class Card {
	
	private String cardNum = "999999";
	private String bank = " an unknown bank ";
	private boolean valid = true;
	private int sum = 0;
	
	
	public Card(String inCard) {
		this.cardNum = inCard;
	}
	
	
	
	//Setters and Getters
	//for card num
	public void setCardNum(String inCard) {
		this.cardNum = inCard;
	}
	public String getCardNum() {
		return this.cardNum;
	}
	//for validity
	public void setValidity(boolean inVal) {
		this.valid = inVal;
	}
	public boolean getValidity() {
		return this.valid;
	}
	//for bank info
	public void setBank(String inBank) {
		this.bank = inBank;
	}
	public String getBank() {
		return this.bank;
	}
	
	//Getter for sum for checking during testing only
	public int getSum() { return this.sum;}
	
	//step 1
	//checking card length
	public void checkCardLen() {
		if(this.cardNum.length() < 13 || this.cardNum.length() > 19 ) {
			this.setValidity(false);
		}
	}
	
	//step 2
	//checking Issuer identification number  
	public void checkIssuer() {
		char c = cardNum.charAt(0);
		switch(c) {
			case '3':
				this.bank = " American Express ";
				break;
			case '6':
				this.bank = " Discover ";
				break;
			case '5':
				this.bank = " Master ";
				break;
			case '4':
				this.bank = " Visa ";
				break;
			default:
				this.bank = " an unknown bank ";
				this.setValidity(false);
		}
	}
	
	//step 3
	//Double every second digit from right to left. If doubling of a digit results in a two-
	//digit number, add up the two digits to get a single digit number.
	public void doubleEverySecond() {
		int curNum = 0;
		try {
			for(int i = this.cardNum.length()-2; i >= 0; i=i-2) {
				curNum = Character.getNumericValue( this.cardNum.charAt(i) );
				//For printing as you go, only for testing
				//System.out.print(" current num is: "+ curNum);
				curNum = curNum*2;
				if(curNum >= 10) {
					curNum = curNum - 9;
				}
				sum += curNum;
				
			}
		} catch (Exception e) {
			System.out.println("Error located at Card.java, doubleEverySecond. card is: "+this.cardNum);
			this.setValidity(false);
		}
	}
	
	//Add all digits in the odd places from right to left in the card number.//step 4
	public void addOddPlaces() {
		int curNum = 0;
		try {
			for(int i = this.cardNum.length()-1; i >= 0; i=i-2) {
				curNum = Character.getNumericValue( this.cardNum.charAt(i) );
				//For printing as you go, only for testing
				//System.out.print(" current num for odds is: "+ curNum);
				sum += curNum;
			}
		} catch (Exception e) {
			System.out.println("Error located at Card.java, addOddPlaces.");
			this.setValidity(false);
		}
	}
	
	// the step 5 is done as 3-4 go though the numbers and is already added to a sum
	
	//checks the sum for a final check and makes sure it can divide by 10 //step 6
	public void checkSum() {
		if(sum % 10 != 0) {
			this.setValidity(false);
			this.bank = " an unknown bank ";
		}
	}

}
