import java.util.*;
/**
 * This class is used to represent a Deck of Cards in Java
 * @author Ritvik Singh
 * @version 1.1
 */
public class Deck {
    public ArrayList<Card> deck = new ArrayList<Card>();
    /**
     * This method is the constructor for Deck
     * It first empties the deck, then it adds 52 new Card objects to the Deck.
     */
    public Deck(){
        this.removeAllCardsFromDeck();
        for(int i=0;i<4;i++){
            for(int j=0;j<13;j++){
                deck.add(new Card(i+1,j+1));
            }
        }
    }
    /**
     * This method is used to add a Card object to the Deck, at the last index
     * @param card
     */
    public void addCard(Card card) {
        if (card != null) {
            this.deck.add(card);
        }
    }
    /**
     * This method is used to add a Card object to the Deck, at the specified index
     * @param index
     * @param card
     */
    public void addCard(int index,Card card) {
        if (card != null) {
            this.deck.add(index,card);
        }
    }
    /**
     * This method removes a card from the deck and then returns the card removed
     * @return
     */
    public Card removeCard(int index) {
        Card temp = this.deck.get(index);
        if(temp!=null){
            this.deck.remove(index);
            return temp;
        } else{
            return null;
        }
    }
    /**
     * This method is used to remove all the cards from the deck
     */
    public void removeAllCardsFromDeck(){
        this.deck = new ArrayList<Card>();
    }

    /**
     * This method is used to draw a card from the deck
     * @param i
     * @return this.deck.get(i) if the index is valid, null otherwise
     */
    public Card getCard(int i){
        if(i>=0 && i<this.deck.size()){
            return this.deck.get(i);
        } else{
            return null;
        }
    }
    /**
     * This method is used to set the Card object, at the specified index i, and return
     * that object
     * @param i
     * @param card
     * @return this.deck.set(i,card) if the index is valid, null otherwise
     */
    public Card setCard(int i, Card card){
        if (i >= 0 && i < this.deck.size()) {
            return this.deck.set(i, card);
        } else {
            return null;
        }
    }
    /**
     * This method is used to shuffle the deck of cards randomly.
     */
    public void shuffleDeck(){
        for(int i=0;i<this.deck.size();i++){
            int j=(int)(Math.random()*this.deck.size());
            if(i!=j){
                Card tempCard = getCard(i);
                setCard(i,getCard(j));
                setCard(j,tempCard);
            }
        }
    }
    /**
     * This method is used to add (numOfCard) cards from the top of the deck
     * to the player.
     * @param player
     * @param numOfCards
     */
    public void giveCardToPlayer(Player player,int numOfCards){
        for(int i=0;i<numOfCards;i++){
            player.addCard(this.removeCard(this.deck.size() - 1));
        }
    }
    /**
     *This method is used to add (numOfCard) cards from the top of the deck
     *to the specified index of the player.
     * @param player
     * @param numOfCards
     * @param index
     */
    public void giveCardToPlayer(Player player,int numOfCards,int index){
        for(int i=0;i<numOfCards;i++){
            player.addCard(index, this.removeCard(this.deck.size() - 1));
        }
    }
    /**
     * This method is used to add (numOfCard) cards from the top of the deck
     * to the dealer.
     * @param dealer
     * @param numOfCards
     */
    public void giveCardToDealer(Dealer dealer,int numOfCards){
        for(int i=0;i<numOfCards;i++){
            dealer.addCard(this.removeCard(this.deck.size() - 1));
        }
    }
    /**
     *This method is used to add (numOfCard) cards from the top of the deck
     *to the specified index of the dealer.
     * @param dealer
     * @param numOfCards
     * @param index
     */
    public void giveCardToDealer(Dealer dealer,int numOfCards,int index){
        for(int i=0;i<numOfCards;i++){
            dealer.addCard(index, this.removeCard(this.deck.size() - 1));
        }
    }

}
