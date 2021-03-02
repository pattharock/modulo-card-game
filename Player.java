import java.util.*;
/**
 * This class is used to represent the Player in the Card Game.
 * @author Ritvik Singh
 * @version 1.1
 */
public class Player {
    ArrayList<Card> cardsOfPlayer = new ArrayList<Card>();
    /**
     * This method is used to add a Card object to the cardsOfPlayer, at last index
     * @param card
     */
    public void addCard(Card card){
        if(card!=null){
            this.cardsOfPlayer.add(card);
        }
    }
    /**
     * This overridden method is used to add a Card object to the cardsOfPlayer,
     * at the specified index
     * @param card
     * @param index
     */
    public void addCard(int index, Card card){
        if(card!=null){
            this.cardsOfPlayer.add(index,card);
        }
    }
    /**
     * This method is used to remove all the cards from the Player
     */
    public void removeAllCardsFromPlayer(){
        cardsOfPlayer = new ArrayList<Card>();
    }
    /**
     * This method returns the number of special cards(J,Q,or K) held by the player
     * @return numOfSpecialCards
     */
    public int getSpecialCardsOfPlayer(){
        int numOfSpecialCarda = 0;
        for(int i=0;i<this.cardsOfPlayer.size();i++){
            Card tempCard = this.cardsOfPlayer.get(i);
            if(tempCard.isSpecialCard()){
                numOfSpecialCarda+=1;
            }
        }
        return numOfSpecialCarda;
    }
    /**
     * This method returns the remainder, when the sum of the ranks of all the
     * non-special cards held by the player, is divided by 10
     *
     * @return nonSpecialCardsSum%10
     */
    public int nonSpecialCardsMOD10(){
        int nonSpecialCardsSum=0;
        for(int i=0;i<this.cardsOfPlayer.size();i++){
            Card tempCard = this.cardsOfPlayer.get(i);
            if(!tempCard.isSpecialCard()){
                nonSpecialCardsSum+=tempCard.getRank();
            }
        }
        return nonSpecialCardsSum%10;
    }
}
