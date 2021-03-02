/**
 * This class is used to represent the CardGame, in NON-GUI format.
 */
public class Game {
    Deck deck = new Deck();
    Player player = new Player();
    Dealer dealer = new Dealer();
    static int numOfCardsChanged=0;
    /**
     * This method is used to start the Deck by shuffling all the cards in it
     */
    public void startDeck(){
        deck.shuffleDeck();
    }
    /**
     * This method is used to deal 3 cards to the Player and 3 cards to the Dealer
     */
    public void dealCards(){
        deck.giveCardToPlayer(player,3);
        deck.giveCardToDealer(dealer,3);
    }
    /**
     * This method is used to restart the Deck, Player, and Dealer, once a game
     * has been played
     */
    public void restartDeck(){
        player.removeAllCardsFromPlayer();
        dealer.removeAllCardsFromDealer();
        deck = new Deck();
    }
    /**
     * This method is used to change the 1st card of the player, and add back
     * the removed card to the deck randomly
     */
    public void changeCard1(){
        Card temp = player.cardsOfPlayer.remove(0);
        deck.giveCardToPlayer(player,1,0);
        numOfCardsChanged++;
    }
    /**
     * This method is used to change the 2nd card of the player, and add back
     * the removed card to the deck randomly
     */
    public void changeCard2(){
        Card temp = player.cardsOfPlayer.remove(1);
        deck.giveCardToPlayer(player,1,1);
        numOfCardsChanged++;
    }
    /**
     * This method is used to change the 2nd card of the player, and add back
     * the removed card to the deck randomly
     */
    public void changeCard3(){
        Card temp = player.cardsOfPlayer.remove(2);
        deck.giveCardToPlayer(player,1,2);
        numOfCardsChanged++;
    }
    /**
     * This method is used to determine the winner of the game, according to the
     * specified rules.
     * @return 1 if the Player wins and dealer loses, -1 if the dealer wins and player loses
     */
    public int determineWinner(){
        if(player.getSpecialCardsOfPlayer() > dealer.getSpecialCardsOfDealer()) {
            return 1;
        } else if (player.getSpecialCardsOfPlayer() < dealer.getSpecialCardsOfDealer()) {
            return -1;
        } else{
            if(player.nonSpecialCardsMOD10() > dealer.nonSpecialCardsMOD10()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
