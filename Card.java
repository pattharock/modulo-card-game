/**
 * This class is used to represent a Card in Java
 * @author Ritvik Singh
 * @version 1.1
 */
public class Card {
    public int suit;
    public int rank;
    /**
     * This method is the constructor for initialising a Card Object
     * @param suit
     * @param rank
     */
    public Card(int suit,int rank){
        this.suit = suit;
        this.rank = rank;
    }
    /**
     * This is the getter method for the instance variable suit
     * @return this.suit
     */
    public int getSuit() {
        return this.suit;
    }
    /**
     * This is the getter method for the instance variable rank
     * @return this.rank
     */
    public int getRank(){
        return this.rank;
    }
    /**
     * This method is used to check whether the Card object is a special card
     * @return true if the Rank i greater than or equal to 11, false otherwise
     */
    public boolean isSpecialCard(){
        return this.getRank() == 11 || this.getRank() == 12 || this.getRank() == 13;
    }
}
