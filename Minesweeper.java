import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Minesweeper extends JFrame { 
    int difficulty;
    boolean[][] a;
    JPanel p = new JPanel();
    BombButton buttons[][];
    boolean over = false;
    int revealedSquares = 0;
    
    public Minesweeper(int d) { 
        super("Minesweeper");
        difficulty = d;
        a = makeArray(difficulty);
        buttons = new BombButton[a.length][a[0].length];
        if (difficulty == 1) {
            setSize(500,500); 
        } else if (difficulty == 2) {       
            setSize(700, 700);
        } else if (difficulty == 3) { 
            setSize(1500,750);
        }
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(a.length, a[0].length));
        for (int i = 0; i < a.length; i++) { 
            for (int j = 0; j < a[0].length; j++) { 
                if (a[i][j] == false) { 
                    buttons[i][j] = new BombButtonFalse();
                } else { 
                    buttons[i][j] = new BombButtonTrue();
                }
                p.add(buttons[i][j]);
            }
        }
        add(p);
        setVisible(true);
    }
    
    public void run() { 
        while(!over) {
            for (int i = 0; i < buttons.length; i++) { 
                for (int j = 0; j < buttons[0].length; j++) { 
                    if (buttons[i][j] != null && buttons[i][j].getDeleteState() == 1) { 
                        if (buttons[i][j].containsBomb() == true) {
                            p.remove(buttons[i][j]);
                            buttons[i][j] = null;
                            gameOver(0);
                        } else { 
                            int near = bombCount(i, j);
                            revealSquare(i, j);
                            if (near == 0) {
                                revealSquaresNear(i, j);
                            }
                        }
                    }
                }
            }
            if (revealedSquares == numClearSquares()) { 
                gameOver(1);
            }
        }
    }
    
    public void revealSquaresNear(int i, int j) {
       int xMin = -1, xMax = 1, yMin = -1, yMax = 1;
       if (j == 0) { 
            yMin = 0;
       } else if (j == buttons[0].length-1) {
            yMax = 0;
       }
       if (i == 0) { 
           xMin = 0;
       } else if (i == buttons.length-1) {
           xMax = 0;
       }
       for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) { 
                int near = 0;
                if (buttons[i+x][j+y] != null && buttons[i+x][j+y].containsBomb() == false) { 
                    near = bombCount(i+x, j+y);
                    revealSquare(i+x, j+y);
                    if (near > 0) {
                        break;
                    }
                    revealSquaresNear(i+x, j+y);
                }
            }
       }
    }
    
    public int bombCount(int i, int j) {
       int nearBombs = 0;
       int xMin = -1, xMax = 1, yMin = -1, yMax = 1;
       if (j == 0) { 
            yMin = 0;
       } else if (j == buttons[0].length-1) {
            yMax = 0;
       }
       if (i == 0) { 
           xMin = 0;
       } else if (i == buttons.length-1) {
           xMax = 0;
       }
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) { 
                if (buttons[i+x][j+y] != null && buttons[i+x][j+y].containsBomb() == true) { 
                    nearBombs++; 
                }
            }
       }
       return nearBombs;
    }
    
    public void revealSquare(int i, int j) {
      int nearBombs = bombCount(i, j);
       
       buttons[i][j].setDeleteState(2);
       
       if (buttons[i][j].containsBomb()) {
           buttons[i][j].setBombIcon(); 
        } else {
            buttons[i][j].setIcon(null);
        }
       if (nearBombs > 0) {
           buttons[i][j].setText("" + nearBombs);
       }
       buttons[i][j] = null;
       revealedSquares++;
       System.out.println(revealedSquares);
    }
    
    public boolean[][] makeArray(int d) { 
        boolean[][] a;
        int numMines = 0, dimX = 1, dimY = 1;
        
        if (d == 1) { 
            dimX = 9;
            dimY = 9;
            numMines = 10;
        } else if (d == 2) { 
            dimX = 16;
            dimY = 16;
            numMines = 40;
        } else if (d == 3) { 
            dimX = 16;
            dimY = 30;
            numMines = 99;
        }
        
        a = new boolean[dimX][dimY];
        
        for (int i = 0; i < numMines; i++) {
            int mineX = (int)(Math.random()*dimX);
            int mineY = (int)(Math.random()*dimY);
            
            a[mineX][mineY] = true;
        }
        
        return a;
    }
    
    public void gameOver(int condition) { 
        if (condition == 0) { 
            over = true;
            System.out.println("You lose - game over");
            return;
        } else {
            over = true;
            System.out.println("You win"); 
        }
    }
    
    public int numClearSquares() {
        int c = 0;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j] != null && buttons[i][j].containsBomb() == false) {
                    c++;
                }
            }
        }
        return c;
    }
}