/**
*
*This class Deck simulates a deck of poker cards.
*
* Template by: @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
*
* @author Faye Lin
* @author Ann Soong
*/

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    /**
    * A constructor for the class <code>Deck</code>. It creates the initial
    * <code>ArrayList</code> that will be used to store the cards of Otherwise
    * deck.
    */

    public Deck() {
        cards = new ArrayList<Card>();
    }

    /**
    * A constructor for the class <code>Deck</code>. It creates the initial
    * <code>ArrayList</code> that will be used to store the cards of Otherwise
    * deck. The parameter specifies the number of ranks for the cards. Finally,
    * it also initializes this deck to contain 4 x n cards, where n is the value
    * of the parameter.
    *
    * @param range the number of ranks for the cards
    */


    public Deck(int range) {
        //parameter specifies number of ranks for the cards and initializes the
        //deck to contain 4n cards, n=range.

        cards = new ArrayList<Card>();

        for (int suit = 0; suit < 4; suit++){
            for (int rank = 1; rank < range+1; rank++){

                Card card;
                card = new Card(suit, rank);

                cards.add(card);
            }
        }
    }


    public int size(){
    //returns the number of cards in this deck
        return cards.size();
    }

    public boolean hasCards(){
    //returns true if and only if this card has one or more cards
        return (cards.size() != 0);
    }

    public Card get(int pos){
    //returns the card at position pos in the deck
        Card target;
        target = cards.get(pos);
        return target;
    }

    public void add(Card card){
    //adds card card at the end of this deck
        cards.add(card);
    }

    public void addAll(Deck other){
    //appends all the cards from other at the end of this deck
        for(int i = 0; i<other.cards.size(); i++){
            this.cards.add(other.cards.get(i));
        }
    }

    public Card removeLast(){
    //removes and returns the last card of this deck
        int pos = cards.size()-1;
        Card card = cards.get(pos);
        cards.remove(card);
        return card;
    }

    public Card removeFirst(){
    //removes and returns the first card of this deck
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }

    public boolean remove(Card card){
    //removes the first occurence of the specified card from this deck;
    //returns true if and only if this deck contains the specified card
        if(this.cards.contains(card)){
            this.cards.remove(card);
            return true;
        }

        else{
            return false;
        }
    }

    public void removeAll(Deck other){
    //removes from this deck all of its cards that are contained in the deck
    //designated by the parameter other.
    //The cards are not removed from the deck designated by other.
        for(int i=0; i<other.cards.size(); i++){
            Card check = other.cards.get(i);
            if(this.cards.contains(check)){
                this.cards.remove(check);
            }
        }
    }

    public void shuffle(){
    //shuffles all the cards in deck.
      int intIndex;
      int intSize = cards.size();

      for(int i = 0; i < intSize; i++){
        intIndex = (int)(Math.random()*intSize);
        Card temp = removeFirst();
        cards.add(intIndex, temp);
      }
    }

    public Deck deal(int n){
    //removes a maximum of n cards from the end of this deck. The cards are returned in a new deck.
        Deck dealNew = new Deck();
        int sum = cards.size()-n;

        for(int i = (cards.size()-1); i >= sum; i--){
            Card copy = cards.get(i);
            cards.remove(i);
            dealNew.cards.add(copy);
        }

        return dealNew;
    }

    public boolean contains(Card card){
    //returns true if and only if this deck contains the specified card.
        return(this.cards.contains(card));
    }

    public boolean containsAll(Deck other){
    //returns true if and only if this deck contains all the cards in the specified deck.
        for(int i = 0; i<other.cards.size(); i++){
            Card check = other.cards.get(i);
            if(!this.cards.contains(check)) {
                return false;
            }
        }
        return true;
    }

    public boolean isKind(){
    //returns true if and only if this deck is a discardable kind.
    //Specifically, the method returns true if this deck has at least two cards and
    //all the cards have the same rank. Otherwise, the method returns false.
        boolean flag = true;
        if(cards.size()>=2){
            int j= 0;
            while(flag && j<cards.size()-1) {
                if(cards.get(j).getRank() != cards.get(j+1).getRank()) {
                    flag = false;
                }
                j++;
            }
        }
        else{
            flag = false;
        }
        return flag;
    }

    public boolean isSeq(){
    //returns true if and only if this deck is a discardable sequence. Specifically, the method returns true
    //if this deck has at least three cards, the cards all have the same suit, the cards form a sequence of consecutive ranks.
    //Otherwise, the method returns false

        sortBySuit();
        sortByRank();
        boolean flag = true;

        if(cards.size()>=3){

            //checks if the cards have the same suit
            int j= 0;
            while(flag && j<cards.size()-1) {
                if(cards.get(j).getSuit() != cards.get(j+1).getSuit()) {
                    flag = false;
                }
                j++;
            }

            //If cards have sane suit, checks if the cards have consecutive rank
            if(flag) {
                int i=0;
                while(flag && i<cards.size()-1) {
                    if(cards.get(i).getRank()+1 != cards.get(i+1).getRank()) {
                        flag =  false;
                    }
                    i++;
                }
            }
        }
        else{
            flag = false;
        }
        return flag;
    }

    public void sortBySuit(){
    //sorts the cards of this deck by suit
      helperRank();
      helperSuit();
    }

    private void helperSuit(){
      int intMin;
      Card temp;
      for(int j = 0; j < cards.size(); j++){
        intMin = j;
        for(int i = j+1; i < cards.size(); i++){
          if(cards.get(i).getSuit() < cards.get(intMin).getSuit()){
            intMin = i;
          }
        }
        temp = cards.get(intMin);
        cards.remove(intMin);
        cards.add(j, temp);
      }
    }

    public void sortByRank(){
    //sorts the cards of this deck by rank.
      helperSuit();
      helperRank();
    }

    private void helperRank(){
      int intMin;
      Card temp;
      for(int j = 0; j < cards.size(); j++){
        intMin = j;
        for(int i = j+1; i < cards.size(); i++){
          if(cards.get(i).getRank() < cards.get(intMin).getRank()){
            intMin = i;
          }
        }
        temp = cards.get(intMin);
        cards.remove(intMin);
        cards.add(j, temp);
      }
    }

    public void print(){
    //prints the content of this deck in two ways. First, the content is printed by suit. Next, the content is printed
    //by rank. Please note that this method has a side effect, the order of the cards is not preserved. Consequently, the method
    //should not be called on the main deck during a game!
        sortByRank();
        System.out.println(toString());
        sortBySuit();
        System.out.println(toString());
    }


    public String toString(){
    //returns a string representation that contains all the cards in this deck.
        String strCards;

        if(cards.size() == 0){ // Checks to see if the deck is empty.
            strCards = "";

        } else { // If the deck is not empty, the cards need to be all printed out.
            strCards = cards.get(0).toString();
            for(int i = 1; i < cards.size(); i++){
              strCards += "," + cards.get(i).toString();
            }
        }

        return "Deck [" + strCards + "]";
    }

}
