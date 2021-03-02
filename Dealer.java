import java.util.*;
/**
 * This class is used to model the Dealer of the Card Game
 * @author Ritvik Singh
 * @version 1.1
 */
public class Dealer {
    ArrayList<Card> cardsOfDealer = new ArrayList<Card>();
    /**
     * This method is used to add a Card object to the cardsOfDealer, at last index
     * @param card
     */
    public void addCard(Card card){
        if(card!=null){
            this.cardsOfDealer.add(card);
        }
    }
    /**
     * This overridden method si used to add a Card object to the cardsOfDealer,
     * at the specified index
     * @param card
     * @param index
     */
    public void addCard(int index, Card card){
        if(card!=null){
            this.cardsOfDealer.add(index,card);
        }
    }
    /**
     * This method is used to remove all the cards from the Dealer
     */
    public void removeAllCardsFromDealer(){
        cardsOfDealer = new ArrayList<Card>();
    }
    /**
     * This method returns the number of special cards(J,Q,or K) held by the dealer
     * @return numOfSpecialCards
     */
    public int getSpecialCardsOfDealer(){
        int numOfSpecialCarda = 0;
        for(int i=0;i<this.cardsOfDealer.size();i++){
            Card tempCard = this.cardsOfDealer.get(i);
            if(tempCard.isSpecialCard()){
                numOfSpecialCarda+=1;
            }
        }
        return numOfSpecialCarda;
    }
    /**
     * This method returns the remainder, when the sum of the ranks of all the
     * non-special cards held by the dealer, is divided by 10
     *
     * @return nonSpecialCardsSum%10
     */
    public int nonSpecialCardsMOD10(){
        int nonSpecialCardsSum=0;
        for(int i=0;i<this.cardsOfDealer.size();i++){
            Card tempCard = this.cardsOfDealer.get(i);
            if(!tempCard.isSpecialCard()){
                nonSpecialCardsSum+=tempCard.getRank();
            }
        }
        return nonSpecialCardsSum%10;
    }
}
