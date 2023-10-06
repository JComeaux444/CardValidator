package cardValidator;

//Checks SIZE of card, must be between 13-19 digits
public class CardAsker extends Thread {
	/*
	public CardAsker() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkCardLen();
		}
	}
	*/
	@Override
	public void run() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkCardLen();
		}
	}

}
