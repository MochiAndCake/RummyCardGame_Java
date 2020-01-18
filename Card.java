/**
* This class simulates a card that has a rank and a suit.
*
* @author Faye Lin
* @author Ann Soong
*/

public class Card{

	private int suit, rank;
	public static final int DIAMOND = 0;
	public static final int CLUB = 1;
	public static final int HEART = 2;
	public static final int SPADE = 3;

	// constructor
	public Card(int suit, int rank){
		this.suit = suit;
		this.rank = rank;
	}

	// getters
	public int getSuit(){
		return suit;
	}

	public int getRank(){
		return rank;
	}

	//methods
	public boolean equals(Object object){
		if (! (object instanceof Card )) {
			return false;
		}

		Card other;
		other = (Card) object;
		return (this.suit == other.suit && this.rank == other.rank);
	}

	public String toString(){
		return ("{" + suit + ", " + rank + "}");
	}

}
