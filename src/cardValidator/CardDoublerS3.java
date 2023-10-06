package cardValidator;

public class CardDoublerS3 extends Thread {
	/*
	public CardDoublerS3()  {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).doubleEverySecond();
		}
	}
	*/
	@Override
	public void run() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).doubleEverySecond();
		}
	}
}
