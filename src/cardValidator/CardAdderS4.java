package cardValidator;

public class CardAdderS4 extends Thread  {
	/*
	public CardAdderS4() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).addOddPlaces();
		}
	}
	*/
	@Override
	public void run() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).addOddPlaces();
		}
	}

}
