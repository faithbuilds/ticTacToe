//libraries we need for creating our game
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.*;
import javax.swing.*; 

public class TicTacToe {
    //we start by creating our game window  
    int boardWidth = 600;
    int boardHeight = 650; //50px is allocated for the text panel on top. this is the panel that is going to say it is X's turn or O's turn. it will alos display the winner of the game.

    //let's create our window
    JFrame frame = new JFrame("Tic-Tac-Toe"); 
    //let's add some panels
    //panels for our labels/text
    JLabel textJLabel = new JLabel();
    JPanel textJPanel = new JPanel();
    //creating the board
    JPanel boardJPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; //we're going to use this to the array to keep track of all the buttons on the board, and it's 3 x 3 so we can keep track of the placement of each button. we're going to use this array to check whether there's a winner or not.
    String playerX = "X"; 
    String playerO = "O"; 
    String currentPlayer = playerX; 

    //let's check for winner's 
    boolean gameOver = false; 

    //to check for a tie
    int turns = 0; 

    //let's add a constructor
    TicTacToe() {
        //this is basically settings for our window
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textJLabel.setBackground(Color.pink); //this sets the background colour for label to dark grey
        textJLabel.setForeground(Color.white); //this sets the colour of our font to white 
        textJLabel.setFont(new Font("Arial", Font.BOLD,  50));
        textJLabel.setHorizontalAlignment(JLabel.CENTER); //this centers our text instead of it starting on the left side
        textJLabel.setText("Tic-Tac-Toe"); //our default text
        textJLabel.setOpaque(true);
        
        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textJLabel);
        frame.add(textJPanel, BorderLayout.NORTH); 

        boardJPanel.setLayout(new GridLayout(3, 3));
        boardJPanel.setBackground(Color.pink);
        frame.add(boardJPanel); 

        //let's add some buttons, we're going to use for loops. r stands for rows
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton(); 
                board[r][c] = tile; 
                boardJPanel.add(tile);

                //let's add some properties to style our buttons
                tile.setBackground(Color.pink);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                //adding an action listener for each tile
                tile.addActionListener(new ActionListener() {
                    //within here we're going to create a new function
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        //when we click on a button the first thing we need to do is check if it's empty then we can either place a X or O. 
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer); //whenever i click the button it'll be whatever current player is set to, which is "X" right now
                            turns++; 
                            //calling a function called check winner
                            checkWinner();
                            //within here we're going to check the 2D array for winning conditions. if there's a winner, we can end the game here.
                             if (!gameOver) {
                            //let's make it that it alternates
                            currentPlayer = currentPlayer == playerX ? playerO : playerX; 
                            textJLabel.setText(currentPlayer + "'s turn."); //this will let us know who's turn it is
                             }
                            
                        }
                        
                    }
                });

            }
        }


    }
//here i'm going to define the funtion 
    void checkWinner () {
        //the first winning condition we're going to check is horizontal - to check horizontally we need to go through each row of our 2D array and check to see if we have either 3 X's or 3 O's 
        for (int r = 0; r < 3; r++){
        
        if (board[r][0].getText() == "") continue; 
        
        if (board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i <3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return; 
                //here we just checked if the text onthe first tile equals the text on the second tile, and if the text on the second tile equals the test on the third tile
            }
        }

        //let's check for vertical wins
        for (int c = 0; c < 3; c++){
        
        if (board[0][c].getText() == "") continue;

        if (board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()) {
            for (int i=0; i<3; i++) {
                setWinner(board[i][c]);
            }
             gameOver = true; 
             return;
            }
        }

        //let's check for diagonal wins
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            for (int i=0; i<3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
            }

        //let's check for anti-diagonal wins
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true; 
                return;
            }

        //let's check if it turns out equal - a tie 
        if (turns == 9) {
           //if we have a tie i will change all the colour of the tiles 
            for (int r=0; r<3; r++) {
                for (int c=0; c<3; c++) {
                    setTie(board[r][c]);

                }
            }
            gameOver = true;
            
        }
    }
    //defining a setWinner function
    void setWinner (JButton tile) {
        tile.setForeground(Color.black);
        tile.setBackground(Color.pink);
        textJLabel.setText(currentPlayer + " is the winner!");
    }

    //defining setTie
    void setTie (JButton tile) {
        tile.setForeground(Color.pink);
        tile.setBackground(Color.black);
        textJLabel.setText("Tie!");
    }
}

