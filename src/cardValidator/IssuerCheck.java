package cardValidator;

public class IssuerCheck  extends Thread {
	/*
	public IssuerCheck() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkIssuer();
		}
	}
	*/
	@Override
	public void run() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkIssuer();
		}
	}

}
