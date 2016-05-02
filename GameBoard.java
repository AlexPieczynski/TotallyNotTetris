
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.GridLayout;


public class GameBoard extends JPanel {
    private final int rows = 20;
    private final int cols = 10;
    private final int winWidth = 375;
    private final int winHeight = 750;
    private int cellSize;    
    private Timer timer;
    private static int count;
    private TetrisBlock[][] gameSpace;    
    
    GameBoard() {             
        count = 0;
        cellSize = winWidth * winHeight / (rows * cols);
        gameSpace = new TetrisBlock[rows][cols];  //Create 2d Container Array
        
        //Initialize timer
        timer = new Timer(400, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Do timer action
                System.out.println(count);
                count++;
            }
        });
        
        //Build blank board
        GridLayout layout = new GridLayout(rows, cols);
        this.setLayout(layout);
        
        resetSpace();
    }
    
    
    public void resetSpace(){
        int i = 0;
        int j = 0;
        //Using regular nested loops, not sure how to do the other method for 2d...
         for(i = 0; i<rows; i++){
            for(j = 0; j<cols; j++){
                gameSpace[i][j] = new TetrisBlock();
                gameSpace[i][j].setBlock(0);               
                gameSpace[i][j].setFocusable(false);
                add(gameSpace[i][j]);
            }
        }
    }
    
    //Method to get the array representing the Tetris grid logic.
    public TetrisBlock[][] getGameSpace(){
      return gameSpace;
    }
}
