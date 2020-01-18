/**
* The class simulates a game of Rummy
*
* @author Faye Lin
* @author Ann Soong
*/

public class Game{
	Deck strange, hand;
	Die die;

	//Initializes a new Game with specified number of ranks.
	public Game(int ranks){
		strange = new Deck(ranks);
		hand = new Deck();
		die = new Die();
	}

	//Plays the game
	public void play(){

		// The game starts off with the player being dealt 7 cards from a shuffled Deck
		strange.shuffle();
		hand = strange.deal(7);
		boolean playing = true;
		int intRound = 0;
		System.out.println("Here is your new deck printed in two ways:");
		hand.print();

		while(playing){ // While the player still has cards, the game will continue

			intRound++;

			die.roll();
			int intRolled = die.getValue();

			if(strange.size() == 0){ // If the stange deck is empty, only one can be rolled
				intRolled = 1;
			}
			else if(intRolled > strange.size()){
				intRolled = strange.size();
			}

			System.out.println("Rolling the die!\nThe die has value " + intRolled);

			if(intRolled == 1){ // If the player rolls one, they can discard a card from their hand
				Card cDiscard;
				boolean boolExist = false;
				while(!boolExist){
					System.out.println("Discard any card of your choosing.\nWhich card would you like to discard?");
					cDiscard = Utils.readCard();
					boolExist = hand.remove(cDiscard);

					if(!boolExist){ // If the card does not exist, the game will ask the player to provide a valid card
						System.out.println("No such card in your hand. Try again.");
					}
					else{ // If the card is successfully discarded, the player gets reported about hte status of their hand
						System.out.println("Here is your new deck printed in two ways:");
						hand.print();
					}
				}

			}
			else{ // If the value on the dice is anything but one (2-6), the player gets dealt more cards
				System.out.println("Adding (up to) " + intRolled + " cards to your hand.");
				hand.addAll(strange.deal(intRolled));

				boolean boolAns = true;

				System.out.println("Here is your new deck printed in two ways:");
				hand.print();

				while(boolAns){ // If the player answers that they still have more combinations to discard, the round will continue

					boolAns = Utils.readYesOrNo("Do you have a sequences of three or more cards of the same suit or two or more of a kind? ");

					if(boolAns){
						Deck dDiscard = Utils.readCards("Which 3+ sequence or 2+ of a kind would you like to discard?");

						if(dDiscard.size() < 2){ // Warning if there is not enough cards provided
							System.out.println("Invalid input. Discardable set needs to have at least 2 cards.\nInvalid sequence. Discardable sequence needs to have at least 3 cards.");
						}
						else if((dDiscard.isKind() || dDiscard.isSeq()) && hand.containsAll(dDiscard)){
							// If the combination is valid, the player gets reported about hte status of their hand
							hand.removeAll(dDiscard);

							System.out.println("Here is your new deck printed in two ways:");
							hand.print();

							if(hand.size() == 0){ // If the player's hand is empty, the game realizes that they win
								boolAns = false;
							}
						}
						else if(!(hand.containsAll(dDiscard))){
							// If there is an invalid card given, the game will pinpoint and report the first invalid card
							boolean boolCaught = false;
							int i = 0;
							while(!boolCaught && i < dDiscard.size()){
								if(!(hand.contains(dDiscard.get(i)))){
									boolCaught = true;
									System.out.println(dDiscard.get(i) + " not in your hand. Invalid input");
								}
								i++;
							}
						}
						else{ // If the combination is invalid, the player is informed
							System.out.println("Invalid sequence or set.");
						}
					}
				}
			}

			// The game ends when the player discarded all of the cards in their hand, regardless if the empty deck is still filled
			if(hand.size() == 0){
				playing = false;
			}

		}
		System.out.println("Congratulations, you completed the game in " + intRound + " rounds");
		// The game ends with the player being reported how many rounds they played
	}
}
