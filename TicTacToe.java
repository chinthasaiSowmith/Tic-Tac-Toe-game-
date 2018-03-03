import java.awt.*;   //The Abstract Window Toolkit (AWT) supports Graphical User Interface (GUI) programming
import java.awt.event.*;        //Event handling part of awt package
import javax.swing.*;       //Swing is the primary Java GUI widget toolkit.Swing provides several advanced components such as tabbed panel, scroll panes, trees, tables, and lists.
public class TicTacToe extends JFrame implements ActionListener {
    JPanel main = new JPanel();    //Panels are the areas where all gui components get placed on.
    JPanel grid = new JPanel();
    JPanel info = new JPanel();
    JPanel menu = new JPanel();

    JLabel Mode = new JLabel();    //Labels are the gui components where messages can be displayed
    JLabel Turn = new JLabel();
    JLabel inst;
    JLabel abt;
    JLabel img1 = new JLabel();
    JLabel img2 = new JLabel();
    JLabel img3 = new JLabel();
    JLabel img4 = new JLabel();

    JButton pvp = new JButton("Player vs Player");        //Buttons are the gui components which lead to a functional program
    JButton cvp = new JButton("Player vs CPU");
    JButton Continue = new JButton("Continue");
    JButton names = new JButton("Set Player Names");
    JButton again = new JButton("Try Again");
    JButton quit = new JButton("Quit");
    JButton[] Empty = new JButton[9];

    JMenuItem play = new JMenuItem("Play");                       //MenuItems are similar to buttons 
    JMenuItem instructions = new JMenuItem("Instructions");
    JMenuItem about = new JMenuItem("About");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem back = new JMenuItem("Back");
    JMenuItem back1 = new JMenuItem("Back");      //The back menu items for all the different labels
    JMenuItem back2 = new JMenuItem("Back");

    JTextArea txt = new JTextArea();
    JTextArea status = new JTextArea(); //TextAreas are the components where messages can be typed

    final int[][] winCombo = new int[][] {
            {0,1,2},                {0,3,6},             {0,4,8},
            {3,4,5},                {1,4,7},             {2,4,6},
            {6,7,8},                {2,5,8}
                //Horizontal Wins        Vertical Wins        Diagonal Wins
        };
    int option,turn = 1,player1Won = 0,player2Won = 0,draw = 0,wonNumber1 = 1,wonNumber2 = 1,wonNumber3 = 1;
    boolean inGame = false,CPUGame = false,win = false;
    String temp,temp1,message,Player1 = "Player 1",Player2 = "Player 2",tempPlayer2 = "Player 2",o = "O",x = "X";
    public TicTacToe() {
        this.setTitle("Tic-Tac-Toe");//Title
        this.setSize(1032,540);//Size of the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Closes on exitting
        home();
        grid.setLayout(null);
        grid.setBounds(0,0,600,500);
        grid.setBackground(Color.black);
        for(int i=0;i<9;i++) {
            Empty[i] = new JButton();
            if(i<3)
                Empty[i].setBounds(200*i,0,200,166);
            else if(i>=3 && i<6)
                Empty[i].setBounds((i-3)*200,166,200,166);
            else
                Empty[i].setBounds((i-6)*200,332,200,166);
            Empty[i].setBackground(Color.black);
            Empty[i].setFont(new Font("Times New Roman", Font.PLAIN, 46));
            Empty[i].addActionListener(this);
            grid.add(Empty[i]);      // Playing Field is Compelte
        }
        menu.setLayout(null);
        menu.setBounds(601,0,432,500);
        menu.setBackground(Color.black);
        quit.setBounds(260,400,130,30);
        quit.setBackground(Color.black);
        quit.setForeground(Color.white);
        quit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        again.setBounds(40,400,120,30);
        again.setBackground(Color.black);
        again.setForeground(Color.white);
        again.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        again.setEnabled(false);
        Mode.setBounds(30,30,200,30);
        Mode.setForeground(Color.white);
        Mode.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        Turn.setForeground(Color.white);
        Turn.setBounds(30,70,200,30);
        Turn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        quit.addActionListener(this);
        again.addActionListener(this);
        menu.add(Mode);
        menu.add(Turn);
        menu.add(quit);
        menu.add(again);//Displays the grid and menu

        this.setVisible(true);
    }

    public void home() { //Home Page
        this.setLayout(null);
        ImageIcon image=new ImageIcon("images/TTT5.png");
        img1.setIcon(image);
        img1.setBounds(0,0,1032,500);
        this.add(img1);

        play.setBounds(748,67,60,30);
        play.setBackground(Color.black);
        play.setForeground(Color.white);
        play.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        play.addActionListener(this);
        img1.add(play);

        instructions.setBounds(576,225,75,30);
        instructions.setBackground(Color.black);
        instructions.setForeground(Color.white);
        instructions.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        instructions.addActionListener(this);
        img1.add(instructions);

        about.setBounds(740,380,60,30);
        about.setBackground(Color.black);
        about.setForeground(Color.white);
        about.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        about.addActionListener(this);
        img1.add(about);

        exit.setBounds(903,380,60,30);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        exit.addActionListener(this);

        Continue.setEnabled(false);

        img1.add(exit);
    }

    public void play() { //Play page
        this.setLayout(null);
        ImageIcon image=new ImageIcon("images/play.png");
        img2.setIcon(image);
        img2.setBounds(0,0,1032,500);
        Continue.setBounds(700,100,190,30);
        pvp.setBounds(700,200,190,30);
        cvp.setBounds(700,300,190,30);
        names.setBounds(700,400,190,30);
        Continue.setBackground(Color.black);
        Continue.setForeground(Color.cyan);
        Continue.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        pvp.setBackground(Color.black);
        pvp.setForeground(Color.cyan);
        pvp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        cvp.setBackground(Color.black);
        cvp.setForeground(Color.cyan);
        cvp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        names.setBackground(Color.black);
        names.setForeground(Color.cyan);
        names.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        back.setBounds(566,460,60,30);
        back.setBackground(Color.black);
        back.setForeground(Color.cyan);
        back.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        Continue.addActionListener(this);
        pvp.addActionListener(this);
        cvp.addActionListener(this);
        names.addActionListener(this);
        back.addActionListener(this);
        img2.add(Continue);
        img2.add(pvp);
        img2.add(cvp);
        img2.add(names);
        img2.add(back);
        this.add(img2);
    }

    public void instructions() { //Instructions page
        this.setLayout(null);
        ImageIcon image = new ImageIcon("images/instructions.png");
        img3.setIcon(image);
        img3.setBounds(0,0,1032,500);
        img3.add(back1);
        inst = new JLabel("Instructions:");   
        inst.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        inst.setForeground(Color.green);
        inst.setBounds(710,30,200,30);
        img3.add(inst); 
        message = "The objective is to get three in a row.\n\n" +
        "You play on a three by three game board.\n" +
        "Players alternate placing Xs and Os on the game board \n"+
        "until either opponent has three in a row (Horizontally,\n"+
        "Diagonally, or Vertically) or all nine squares are filled.\n\n"+
        "Player 1: X\n"+"Player 2: O";  
        txt.setText(message);
        txt.setEditable(false);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txt.setBackground(Color.black);
        txt.setForeground(Color.green);
        txt.setBounds(580,100,400,200);
        img3.add(txt);  
        back1.setBounds(566,460,60,30);
        back1.setBackground(Color.black);
        back1.setForeground(Color.green);
        back1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        back1.addActionListener(this);
        this.add(img3);
    }

    public void about() { //About page
        this.setLayout(null);
        ImageIcon image = new ImageIcon("images/about.png");
        img4.setIcon(image);
        img4.setBounds(0,0,1032,500);
        img4.add(back2);
        abt = new JLabel("About:");   
        abt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        abt.setForeground(Color.yellow);
        abt.setBounds(750,30,200,30);
        img4.add(abt);  
        message = "Title: Tic-Tac-Toe\n" +
        "Designed and Programmed by: Suharsh Nagasampagi\n";
        txt.setText(message);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        txt.setBackground(Color.black);
        txt.setForeground(Color.yellow);
        txt.setBounds(580,100,400,200);
        img4.add(txt);  
        back2.setBounds(566,460,60,30);
        back2.setBackground(Color.black);
        back2.setForeground(Color.yellow);
        back2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        back2.addActionListener(this);
        this.add(img4);
    }

    public void names() {
        //IF the player wants to set names 
        boolean tempIsValid = false;
        temp = getInput("Enter player 1 name:", "");
        /*if(temp == null)
        {
            /*Do Nothing
        }*/
        if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(Player2)) {
            option = Message("Player 1 name matches Player 2's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid=true;
        } 
        else if(temp != null)
            tempIsValid = true;
        if(tempIsValid) {
            Player1 = temp;
            tempIsValid = false;
        }
        temp1 = getInput("Enter player 2 name:","");
       /* if(temp1 == null)        
        {
            /*Do Nothing
        }*/
        if(temp1.equals(""))
            showMessage("Invalid Name!");
        else if(temp1.equals(Player1)) {
            option = Message("Player 2 name matches Player 1's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } 
        else if(temp1 != null) {
            tempIsValid = true;
        }
        if(tempIsValid) {
            Player2 = temp1;
            tempPlayer2 = temp1;
            tempIsValid = false;
        }
    }

    public void game() { 
        checkTurn();
        checkWinStatus();
        grid.requestFocus();
        this.add(menu);
        this.add(grid);
    }

    public void newGame() {   
	//  Sets all the game required variables to default
        //      and then shows the playing field.
        //      (Basically: Starts a new PvP Game)
        for(int i=0;i<9;i++) {
            Empty[i].setText("");
            Empty[i].setBackground(Color.black);
            Empty[i].setEnabled(true);
        }
        turn = 1;
        win = false;
        game();
    }

    public void checkWin() {  
	//Checks if there are 3 symbols in a row vertically, diagonally, or horizontally.
        //Then shows a message and disables buttons. If the game is over then it asks
        //If you want to play again.
        for(int i=0;i<8;i++) {
            if(!Empty[winCombo[i][0]].getText().equals("") && Empty[winCombo[i][0]].getText().equals(Empty[winCombo[i][1]].getText()) && Empty[winCombo[i][1]].getText().equals(Empty[winCombo[i][2]].getText())) {
                /**
                The way this checks if someone wins is:
                First: It checks if the Empty[x] is not equal to an empty string- x being the array number inside the multi-dementional array 
                winCombo[checks inside each of the 7 sets][the first number]

                Second: It checks if Empty[x] is equal to Empty[y]- x being winCombo[each set][the first number]
                y being winCombo[each set the same as x][the second number] (So basically checks if the first and
                second number in each set are equal to each other)

                Third: It checks if Empty[y] is equal to Empty[z]- y being the same y as last time and z being
                winCombo[each set as y][the third number]
                Conclusion:     So basically it checks if it is equal to the Empty,which is equal to each set of numbers
                 */
                win = true;
                wonNumber1 = winCombo[i][0];
                wonNumber2 = winCombo[i][1];
                wonNumber3 = winCombo[i][2];
                Empty[wonNumber1].setBackground(Color.green);
                Empty[wonNumber2].setBackground(Color.green);
                Empty[wonNumber3].setBackground(Color.green);
                break;
            }
        }
        if(win == true || (win == false && turn>9)) {
            if(win == true) {
                if(Empty[wonNumber1].getText().equals("X")) {
                    message = Player1+" has won";
                    player1Won++;
                } 
		        else {
                    message = Player2+" has won";
                    player2Won++;
                }
            }      
            else if(win == false && turn>9) {
                message="Cat's Game\nBetter luck next time.";
                draw++;
            }
            showMessage(message);
            for(int i=0;i<9;i++) {
                Empty[i].setEnabled(false);//The value of the buttons cannot be changed
            }
            again.setEnabled(true);//The button becomes enabled to try again
            checkWinStatus();//Tallies the number of wins/draws
        } 
        else
            checkTurn();
    }

    public void AI() {     //Main CPU programming
        int computerButton;
        if(turn <= 9) {
            turn++;
            computerButton = CPU.doMove(  //Calls the CPU class and checks the conditions and accordingly makes a move 
                Empty[0], Empty[1], Empty[2],
                Empty[3], Empty[4], Empty[5],
                Empty[6], Empty[7], Empty[8]);
            if(computerButton == 9)   //If none of the conditions satisfy, cpu makes a random move
                Random();
            else {
                Empty[computerButton].setText(o);
                Empty[computerButton].setBackground(Color.black);
                Empty[computerButton].setEnabled(false);
            }
            checkWin();
        }
    }

    public void Random() {
        int random;
        if(turn <= 9) {
            random = 0;
            while(random == 0)     
                random = (int)(Math.random()*10);     //CPU cooses a random position
            if(random<9 && CPU.doRandomMove(Empty[random]) == true) {
                Empty[random].setText(o);
                Empty[random].setBackground(Color.black);
                Empty[random].setEnabled(false);
            } 
            else
                Random();
        }
    }

    public void checkTurn() {
        String whoseTurn;
        if(turn % 2 == 1)   
            whoseTurn = Player1 + "[X]";      
        else   
            whoseTurn = Player2 + "[O]";
        Turn.setText("Turn: " + whoseTurn);
	Turn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    }

    public void checkWinStatus() {
        message = Player1+": "+player1Won+"\n\n"+Player2+": "+player2Won+"\n\n"+"Cat's Game"+":  "+draw;
        status.setText(message);
        status.setEditable(false);
        status.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        status.setBackground(Color.black);
        status.setForeground(Color.white);
        status.setBounds(30,150,150,200);
        menu.add(status);   
    }

    public int Message(String msg, String tle, int op) { //Pop-Up messages
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }

    public String getInput(String msg, String setText) {
        return JOptionPane.showInputDialog(null, msg, setText);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void actionPerformed(ActionEvent click) { 
     //When a button is pressed, this method handles all the events that take place after the button has been pressed
        Object source = click.getSource();
        for(int i=0;i<9;i++) {
            if(source==Empty[i]&&turn<10) {
                if(turn%2==1)
                    Empty[i].setText(x);
                else
                    Empty[i].setText(o);
                Empty[i].setBackground(Color.black);
                Empty[i].setEnabled(false);
                grid.requestFocus();
                turn++;
                checkWin();
                if(CPUGame&&win == false)
                    AI();
            }
        }
        if(source == play) {
            this.remove(img1);
            repaint();  //Refreshes the page
            play();
        }
        if(source == instructions) {
            this.remove(img1);
            repaint();
            instructions();
        }
        if(source == about) {
            this.remove(img1);
            repaint();
            about();
        }
        if(source == back) {
            this.remove(img2);
            repaint();
            home();
        }
        if(source == back1) {
            this.remove(img3);
            repaint();
            home();
        }
        if(source == back2) {
            this.remove(img4);
            repaint();
            home();
        }
        if(source == Continue) {  //Continuing from the last played game
            this.remove(img2);
            repaint();
            checkTurn();
            game();
        }
        if(source == pvp || source == cvp) {
            if(inGame==true) {     //If the player already had started playing a game and wants to start a new one
                option=Message("If you start a new game," + "your current game will be lost..." + "\n" + "Are you sure you want to continue?","Quit Game?",JOptionPane.YES_NO_OPTION
                );
                if(option==JOptionPane.YES_OPTION) {
                    inGame=false;
                    again.setEnabled(false);
                }
            }
            else {
                this.remove(img2);
                Continue.setEnabled(true);
                if(source == pvp) {  // Player vs Player Game
                    Player2 = tempPlayer2;
                    player1Won = 0;
                    player2Won = 0;
                    draw = 0;
                    Mode.setText("Player vs Player");
                    CPUGame = false;        
                    newGame();
                } 
		        else { // Player vs CPU Game
                    Player2 = "Computer";
                    player1Won = 0;
                    player2Won = 0;
                    draw = 0;
                    Mode.setText("Player vs CPU");
                    CPUGame = true;
                    newGame();
                }
            }
        repaint();
        }
        if(source == names)
            names(); //Asks user(s) for names
        if(source == quit) {
             //If the player wants to stop playing
            this.remove(grid);
            this.remove(menu);
            repaint();
            Continue.setEnabled(true);
            inGame = true;
            play();
        }
        if(source == again) {     //If the player wants to play again after the game has been played
            this.remove(menu);
            this.remove(grid);
            repaint();
            newGame();
            again.setEnabled(false);
        }
        if(source == exit) {       //If the player wants to exit the game
            option = Message("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }

    public static void main(String args[]) {
        new TicTacToe(); //Calls the constructor
    }
}
