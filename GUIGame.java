import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class is used to implement the GUI for the Card Game
 */
public class GUIGame {
    static int moneyInAccount = 100;
    Game game;

    JFrame frame;

    JMenuBar menuBar;
    JMenu control;
    JMenuItem quit;
    JMenu help;
    JMenuItem instruction;

    JLabel label_Image1;
    JLabel label_Image2;
    JLabel label_Image3;
    JLabel label_Image4;
    JLabel label_Image5;
    JLabel label_Image6;

    JButton btn_rpcard1;
    JButton btn_rpcard2;
    JButton btn_rpcard3;
    JButton btn_start;
    JButton btn_result;

    JLabel label_bet;
    JLabel label_info;
    JLabel label_money;

    JTextField txt_inputbet;

    ImageIcon Image1;
    ImageIcon Image2;
    ImageIcon Image3;
    ImageIcon Image4;
    ImageIcon Image5;
    ImageIcon Image6;

    JPanel MainPanel;
    JPanel DealerPanel;
    JPanel PlayerPanel;
    JPanel RpCardBtnPanel;
    JPanel ButtonPanel;
    JPanel InfoPanel;

    public static void main(String[] args) {
        GUIGame gui = new GUIGame();
        gui.startGUI();
    }

    public void startGUI() {
        game = new Game();
        frame = new JFrame();

        menuBar = new JMenuBar();
        control = new JMenu("Control");
        quit = new JMenuItem("Exit");
        help = new JMenu("Help");
        instruction = new JMenuItem("Instruction");


        label_Image1 = new JLabel();
        label_Image2 = new JLabel();
        label_Image3 = new JLabel();
        label_Image4 = new JLabel();
        label_Image5 = new JLabel();
        label_Image6 = new JLabel();

        btn_rpcard1 = new JButton("Replace Card 1");
        btn_rpcard2 = new JButton("Replace Card 2");
        btn_rpcard3 = new JButton("Replace Card 3");
        btn_start = new JButton("Start");
        btn_result = new JButton("Result");

        label_info = new JLabel();
        label_bet = new JLabel();
        label_money = new JLabel();

        label_bet.setText("Bet: $");
        label_info.setText("Please place your bet! ");
        label_money.setText("Amount of money you have: $"+moneyInAccount);

        txt_inputbet = new JTextField(10);

        Image1 = new ImageIcon("card_back.gif");
        Image2 = new ImageIcon("card_back.gif");
        Image3 = new ImageIcon("card_back.gif");
        Image4 = new ImageIcon("card_back.gif");
        Image5 = new ImageIcon("card_back.gif");
        Image6 = new ImageIcon("card_back.gif");

        label_Image1.setIcon(Image1);
        label_Image2.setIcon(Image2);
        label_Image3.setIcon(Image3);
        label_Image4.setIcon(Image4);
        label_Image5.setIcon(Image5);
        label_Image6.setIcon(Image6);

        MainPanel = new JPanel();
        DealerPanel = new JPanel();
        PlayerPanel = new JPanel();
        RpCardBtnPanel = new JPanel();
        ButtonPanel = new JPanel();
        InfoPanel = new JPanel();

        DealerPanel.add(label_Image1);
        DealerPanel.add(label_Image2);
        DealerPanel.add(label_Image3);

        PlayerPanel.add(label_Image4);
        PlayerPanel.add(label_Image5);
        PlayerPanel.add(label_Image6);

        RpCardBtnPanel.add(btn_rpcard1);
        RpCardBtnPanel.add(btn_rpcard2);
        RpCardBtnPanel.add(btn_rpcard3);

        ButtonPanel.add(label_bet);
        ButtonPanel.add(txt_inputbet);
        ButtonPanel.add(btn_start);
        ButtonPanel.add(btn_result);

        InfoPanel.add(label_info);
        InfoPanel.add(label_money);

        MainPanel.setLayout(new GridLayout(5, 1));
        MainPanel.add(DealerPanel);
        MainPanel.add(PlayerPanel);
        MainPanel.add(RpCardBtnPanel);
        MainPanel.add(ButtonPanel);
        MainPanel.add(InfoPanel);

        DealerPanel.setBackground(Color.GREEN);
        PlayerPanel.setBackground(Color.GREEN);
        RpCardBtnPanel.setBackground(Color.GREEN);

        btn_result.setEnabled(false);
        btn_rpcard1.setEnabled(false);
        btn_rpcard2.setEnabled(false);
        btn_rpcard3.setEnabled(false);

        btn_start.addActionListener(new StartListener());
        btn_rpcard1.addActionListener(new Replace1Listener());
        btn_rpcard2.addActionListener(new Replace2Listener());
        btn_rpcard3.addActionListener(new Replace3Listener());
        btn_result.addActionListener(new ResultListener());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(MainPanel);
        frame.setTitle("A Simple Card Game");
        frame.setSize(400, 700);

        control.add(quit);
        menuBar.add(control);
        menuBar.add(help);
        help.add(instruction);
        frame.setJMenuBar(menuBar);
        quit.addActionListener(new ControlListener());
        instruction.addActionListener(new HelpListener());

        frame.setVisible(true);
    }
    /**
     * This method is used to check whether the input provided to the field is valid
     * or invalid
     * @param field
     * @return true, if the input is invalid, false if the input is valid
     */
    public boolean checkInvalidInput(JTextField field){
        if(field.getText().equals("")) {
            return true;
        } else {
            if(Double.parseDouble(field.getText())!=Math.floor(Double.parseDouble(field.getText()))){
                return true;
            } else if(Integer.parseInt(field.getText()) <= 0){
                return true;
            }else if(Integer.parseInt(field.getText()) > moneyInAccount){
                return true;
            }
            return false;
        }
    }
    /**
     * This method returns a string which represents the appropriate message incase
     * the input in the field is invalid.
     * @param field
     * @return A string representing the message to be sent if the input is invalid,
     * "" if the input is valid.
     */
    public String invalidInputMessage(JTextField field) {
        if(field.getText().equals("")) {
            return "Please Enter Bet";
        } else {
            if(Double.parseDouble(field.getText())!=Math.floor(Double.parseDouble(field.getText()))){
                return "Bet CANNOT be Decimal";
            } else if(Integer.parseInt(field.getText()) <= 0){
                return "Bet CANNOT be less than or equal to 0";
            } else if(Integer.parseInt(field.getText()) > moneyInAccount){
                return "Bet CANNOT be greater than the Total money";
            }
            return "";
        }
    }
    /**
     * This method returns the integer value of the bet placed by the player
     * @param field
     * @return Integer.parseInt(field.getText()) if the input is valid, -1 if the
     * input is invalid.
     */
    public int returnBetAmt(JTextField field){
        if(!checkInvalidInput(field)){
            return Integer.parseInt(field.getText());
        } else{
            return -1;
        }
    }
    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the Start button, and it implements the ActionListener interface,
     * thereby providing the implementation of it's methods for Event Handling
     */
    class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if(moneyInAccount<=0){
                label_money.setText("You have no more money!");
                label_info.setText("Please start a new Game");
                JOptionPane.showMessageDialog(frame,"Game Over!\nYou have no money.\nPlease Start a new Game");
                System.exit(0);
            }
            else{
                if(!checkInvalidInput(txt_inputbet)){
                    game.restartDeck();
                    game.startDeck();
                    game.dealCards();

                    label_info.setText("Your current bet is: $"+returnBetAmt(txt_inputbet)+" ");
                    label_money.setText("Amount of Money you have: $"+moneyInAccount);

                    btn_start.setEnabled(false);
                    btn_result.setEnabled(true);
                    btn_rpcard1.setEnabled(true);
                    btn_rpcard2.setEnabled(true);
                    btn_rpcard3.setEnabled(true);

                    Image1 = new ImageIcon("card_back.gif");
                    Image2 = new ImageIcon("card_back.gif");
                    Image3 = new ImageIcon("card_back.gif");
                    Image4 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(0).getSuit() + game.player.cardsOfPlayer.get(0).getRank() + ".gif");
                    Image5 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(1).getSuit() + game.player.cardsOfPlayer.get(1).getRank() + ".gif");
                    Image6 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(2).getSuit() + game.player.cardsOfPlayer.get(2).getRank() + ".gif");

                    label_Image1.setIcon(Image1);
                    label_Image2.setIcon(Image2);
                    label_Image3.setIcon(Image3);
                    label_Image4.setIcon(Image4);
                    label_Image5.setIcon(Image5);
                    label_Image6.setIcon(Image6);
                }else {
                    if(!invalidInputMessage(txt_inputbet).equals("")){
                        label_info.setText("Please place your bet! ");
                        label_money.setText("Amount of Money you have: $"+moneyInAccount);
                        JOptionPane.showMessageDialog(frame,invalidInputMessage(txt_inputbet));
                    }
                }
            }
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "Replace Card 1" button, and it implements the ActionListener interface,
     * thereby providing the implementation of it's methods for Event Handling
     */
    class Replace1Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(game.numOfCardsChanged < 2){
                game.changeCard1();
                if(game.numOfCardsChanged >= 2){
                    btn_rpcard1.setEnabled(false);
                    btn_rpcard2.setEnabled(false);
                    btn_rpcard3.setEnabled(false);
                }
                btn_rpcard1.setEnabled(false);
                Image1 = new ImageIcon("card_back.gif");
                Image2 = new ImageIcon("card_back.gif");
                Image3 = new ImageIcon("card_back.gif");
                Image4 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(0).getSuit() + game.player.cardsOfPlayer.get(0).getRank() + ".gif");
                Image5 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(1).getSuit() + game.player.cardsOfPlayer.get(1).getRank() + ".gif");
                Image6 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(2).getSuit() + game.player.cardsOfPlayer.get(2).getRank() + ".gif");

                label_Image1.setIcon(Image1);
                label_Image2.setIcon(Image2);
                label_Image3.setIcon(Image3);
                label_Image4.setIcon(Image4);
                label_Image5.setIcon(Image5);
                label_Image6.setIcon(Image6);
            } else {
                btn_rpcard1.setEnabled(false);
                btn_rpcard2.setEnabled(false);
                btn_rpcard3.setEnabled(false);
            }
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "Replace Card 2" button, and it implements the ActionListener interface,
     * thereby providing the implementation of it's methods for Event Handling
     */
    class Replace2Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (game.numOfCardsChanged < 2) {
                game.changeCard2();
                if (game.numOfCardsChanged >= 2) {
                    btn_rpcard1.setEnabled(false);
                    btn_rpcard2.setEnabled(false);
                    btn_rpcard3.setEnabled(false);
                }
                btn_rpcard2.setEnabled(false);

                Image1 = new ImageIcon("card_back.gif");
                Image2 = new ImageIcon("card_back.gif");
                Image3 = new ImageIcon("card_back.gif");
                Image4 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(0).getSuit() + game.player.cardsOfPlayer.get(0).getRank() + ".gif");
                Image5 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(1).getSuit() + game.player.cardsOfPlayer.get(1).getRank() + ".gif");
                Image6 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(2).getSuit() + game.player.cardsOfPlayer.get(2).getRank() + ".gif");

                label_Image1.setIcon(Image1);
                label_Image2.setIcon(Image2);
                label_Image3.setIcon(Image3);
                label_Image4.setIcon(Image4);
                label_Image5.setIcon(Image5);
                label_Image6.setIcon(Image6);
            } else {
                btn_rpcard1.setEnabled(false);
                btn_rpcard2.setEnabled(false);
                btn_rpcard3.setEnabled(false);
            }
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "Replace Card 3" button, and it implements the ActionListener interface,
     * thereby providing the implementation of it's methods for Event Handling
     */
    class Replace3Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(game.numOfCardsChanged < 2){
                game.changeCard3();
                if(game.numOfCardsChanged >= 2){
                    btn_rpcard1.setEnabled(false);
                    btn_rpcard2.setEnabled(false);
                    btn_rpcard3.setEnabled(false);
                }
                btn_rpcard3.setEnabled(false);
                Image1 = new ImageIcon("card_back.gif");
                Image2 = new ImageIcon("card_back.gif");
                Image3 = new ImageIcon("card_back.gif");
                Image4 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(0).getSuit() + game.player.cardsOfPlayer.get(0).getRank() + ".gif");
                Image5 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(1).getSuit() + game.player.cardsOfPlayer.get(1).getRank() + ".gif");
                Image6 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(2).getSuit() + game.player.cardsOfPlayer.get(2).getRank() + ".gif");

                label_Image1.setIcon(Image1);
                label_Image2.setIcon(Image2);
                label_Image3.setIcon(Image3);
                label_Image4.setIcon(Image4);
                label_Image5.setIcon(Image5);
                label_Image6.setIcon(Image6);
            } else {
                btn_rpcard1.setEnabled(false);
                btn_rpcard2.setEnabled(false);
                btn_rpcard3.setEnabled(false);
            }
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "Result" button, and it implements the ActionListener interface,
     * thereby providing the implementation of it's methods for Event Handling
     */
    public class ResultListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int betMoney = returnBetAmt(txt_inputbet);
            Image1 = new ImageIcon("card_" + game.dealer.cardsOfDealer.get(0).getSuit() + game.dealer.cardsOfDealer.get(0).getRank() + ".gif");
            Image2 = new ImageIcon("card_" + game.dealer.cardsOfDealer.get(1).getSuit() + game.dealer.cardsOfDealer.get(1).getRank() + ".gif");
            Image3 = new ImageIcon("card_" + game.dealer.cardsOfDealer.get(2).getSuit() + game.dealer.cardsOfDealer.get(2).getRank() + ".gif");
            Image4 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(0).getSuit() + game.player.cardsOfPlayer.get(0).getRank() + ".gif");
            Image5 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(1).getSuit() + game.player.cardsOfPlayer.get(1).getRank() + ".gif");
            Image6 = new ImageIcon("card_" + game.player.cardsOfPlayer.get(2).getSuit() + game.player.cardsOfPlayer.get(2).getRank() + ".gif");

            label_Image1.setIcon(Image1);
            label_Image2.setIcon(Image2);
            label_Image3.setIcon(Image3);
            label_Image4.setIcon(Image4);
            label_Image5.setIcon(Image5);
            label_Image6.setIcon(Image6);

            if(game.determineWinner()==1 && betMoney != -1){
                moneyInAccount+=betMoney;
                JOptionPane.showMessageDialog(frame,"Congratulation! You win this round");
            } else if(game.determineWinner()==-1 && betMoney != -1){
                moneyInAccount-=betMoney;
                JOptionPane.showMessageDialog(frame,"Sorry, you have lost this round.");
            }

           if(moneyInAccount<=0){
               btn_start.setEnabled(false);


           } else{
               btn_start.setEnabled(true);
           }
            btn_result.setEnabled(false);
            btn_rpcard1.setEnabled(false);
            btn_rpcard2.setEnabled(false);
            btn_rpcard3.setEnabled(false);


            label_info.setText("Your current bet is: $"+returnBetAmt(txt_inputbet));
            label_money.setText("Amount of Money you have: $"+moneyInAccount);

            if(moneyInAccount<=0){
                label_money.setText("You have no more money!");
                label_info.setText("Please start a new Game");
                JOptionPane.showMessageDialog(frame,"Game Over!\nYou have no money.\nPlease Start a new Game");
                System.exit(0);
            }

            Image1 = new ImageIcon("card_back.gif");
            Image2 = new ImageIcon("card_back.gif");
            Image3 = new ImageIcon("card_back.gif");
            Image4 = new ImageIcon("card_back.gif");
            Image5 = new ImageIcon("card_back.gif");
            Image6 = new ImageIcon("card_back.gif");

            label_Image1.setIcon(Image1);
            label_Image2.setIcon(Image2);
            label_Image3.setIcon(Image3);
            label_Image4.setIcon(Image4);
            label_Image5.setIcon(Image5);
            label_Image6.setIcon(Image6);
            game.numOfCardsChanged=0;
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "EXIT" JMenuItem in the "Control" JMenu, and it implements the
     * ActionListener interface,thereby providing the implementation of it's methods
     * for Event Handling
     */
    public class ControlListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
    }

    /**
     * This class is an inner class of the GUIGame class. It is the listener class
     * of the "Instructions" JMenuItem in the "Help" JMenu, and it implements the
     * ActionListener interface,thereby providing the implementation of it's methods
     * for Event Handling
     */
    public class HelpListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JOptionPane.showMessageDialog(frame,"J, Q, K are regarded as special cards.\nRule 1: The one with more special cards wins.\nRule 2: If both have the same number of special cards, add the face values of the other\n card(s) and take the remainder after dividing the sum by 10. The one with a bigger\nremainder wins. (Note: Ace = 1).\nRule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.");
        }
    }
}
