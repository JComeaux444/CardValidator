package cardValidator;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	//4388576018402626,4388576018410707
	//example cards from assignment for quick copypasta
	
	//More examples used and are from https://www.paypalobjects.com/en_GB/vhelp/paypalmanager_help/credit_card_numbers.htm 
	//Seems like real test cards and are all valid. 
	//Stuck with Ones we focused on and not AE Corp. Australian, diners club, JCB, or processor cards.
	//4388576018402626,4388576018410707,420696812345678,378282246310005,371449635398431,6011111111111117,6011000990139424,5555555555554444,5105105105105100,4111111111111111,4012888888881881,4222222222222,374245455400126,4999999999999108
	//added second last from https://support.bluesnap.com/docs/test-credit-card-numbers then 
	//https://www.windcave.com/support-merchant-frequently-asked-questions-testing-details
	//Had a hard time finding cards that were just invalid from failing the checks
	
	//List of cards should also fail the third in the array, 3 fails total in above list
	
	public String userInput = "nothing,nothing2";
	public String [] inputArray;

	public static ArrayList<Card> cards = new ArrayList<Card>();//Arraylist so can add as you go unlike array

	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a credit card number. \n"
				+ "Use a comma (,) to seperate more than one card: ");
		
		String userInput = input.nextLine();
		long start = System.currentTimeMillis(); //starts when user presses enter 
		userInput = userInput.replaceAll("\\s", "");//replaces all empty spaces with nothing.
		String[] inputArray = userInput.split(",");
		
		for(int i = 0; i < inputArray.length; i++) {
			Card n = new Card(inputArray[i]);
			cards.add(n);
			//System.out.println("Got in for loop ln27 main.");
		}
		//below is for checking things as i went
		/*
		for(int i = 0; i < inputArray.length; i++) {
			System.out.println(inputArray[i]);
			//System.out.print("  "+inputArray[i].charAt(inputArray[i].length()-2));
		}
		*/
		
		
		/*
		 * Commented this out mainly because it was what i used first to check step by step
		 * 
		//put all the steps here to run and pray it works o7 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//cards.get(0).checkCardLen();//step1
		//cards.get(1).checkCardLen();
		
		//cards.get(0).checkIssuer();//step2
		//cards.get(1).checkIssuer();
		
		//cards.get(0).doubleEverySecond();// step 3
		//System.out.println(cards.get(0).getSum());
		//These lines(above line and similar) check sum after each step here to make sure it was correct
		//cards.get(1).doubleEverySecond();
		
		//cards.get(0).addOddPlaces();//step 4
		//System.out.println(cards.get(0).getSum());
		//cards.get(1).addOddPlaces();
		//Step 5 done during  3-4
		
		//cards.get(0).checkSum();//step 6
		cardSumAndFinalCheck();
		*/
		
		//Where everything is done, meaning what actually triggers 'checks' for each card for a non threaded version
		/*
		lengthCheck();
		issuerCheck();
		doubleSecondPlaces();
		addOddPlaces();
		cardSumAndFinalCheck();
		*/
		
		
		//below is where threaded version is, just comment out upto above the long end line, meaning the system time one
		/**/
		CardAsker step1 = new CardAsker();
		IssuerCheck step2 = new IssuerCheck();
		CardDoublerS3 step3 = new CardDoublerS3();
		CardAdderS4 step4 = new CardAdderS4();
		//step 5 done during 3-4
		SumFinalCheck step6 = new SumFinalCheck();
		
		step1.start();
		step2.start();
		step3.start();
		step4.start();
		
		try {
		step1.join();
		step2.join();
		step3.join();
		step4.join();
		
		} catch (InterruptedException e){
			System.out.println("\n\n Error in the Join methods part 1");
		}
		
	
		
		
		
		
		
		step6.start();
		
		try {
			step6.join();
		} catch (InterruptedException e) {
			System.out.println("\n\n Error in the Join methods part 2");
		}
		
		
		long end = System.currentTimeMillis();
		System.out.println("This took " + (end-start) + " milliseconds to run");
		printAll();
		input.close();

	}
	
	
	
	//I left these methods in and un commented as they won't be used, But were remade into classes to extend thread so we can use them
	
	//step 1
	public static void lengthCheck() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkCardLen();
		}
	}
	
	//step 2
	public static void issuerCheck() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkIssuer();
		}
	}
	
	//step 3
	public static void doubleSecondPlaces() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).doubleEverySecond();
		}
	}
	
	//step 4
	public static void addOddPlaces() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).addOddPlaces();
		}
	}
	
	/*
	 * Step 5 done in 3-4 and synchronization should keep the sum, in the card object, as the
	 * correct number so threads don't fight to add or change things
	 */
	
	
	
	//step 6
	//run this after all threads have joined to have a final check on the sum and make sure its divisible by 10
	public static void cardSumAndFinalCheck() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkSum();
		}
	}
	
	//Used to print all the cards out and their validity / bank after everything is ran properly 
	public static void printAll() {
		System.out.println(" ");
		for(int i = 0; i < Main.cards.size(); i++) {
			Card tempCard = Main.cards.get(i);
			boolean tempBool = tempCard.getValidity();
			String validState = " is valid ";
			if(tempBool == false) {validState = " is not valid ";}
			System.out.println("The card "+tempCard.getCardNum()+validState+
					"and was issued by"+tempCard.getBank());
			
		}
	}


	

}
