package cardValidator;

public class SumFinalCheck extends Thread  {

	/*
	public SumFinalCheck() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkSum();
		}
	}
	*/
	@Override
	public void run() {
		for(int i = 0; i < Main.cards.size(); i++) {
			Main.cards.get(i).checkSum();
		}
	}
}
