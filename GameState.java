//class used to represent the current state of the Tetris game
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameState
{
  private int score;
  private PieceFactory p;
  private int linesCleared;
  private int level;
  private Tetromino currentPiece;
  private Tetromino nextPiece;
  private Timer timer;
  private int[][] grid = new int[20][10];
  private GameGUI gui;
  
  public GameState()
  {
    gui = GameGUI.getInstance();
    p = new PieceFactory();
    score = 0;
    linesCleared = 0;
    level = 1;
    currentPiece = p.getPiece(PieceType.getRandom());
    nextPiece = p.getPiece(PieceType.getRandom());
    timer = new Timer(800, new TimeHandler());
    timer.start();
  }
  
  
  public void pieceDropped()
  {
    score += 10;
    
    int c = clearLines();
    if (c > 0)
      linesCleared(c);
    
    currentPiece = nextPiece;
    //TODO: PUT NEW PIECE AT TOP
    nextPiece = p.getPiece(PieceType.getRandom());
    //TODO: MAKE GUI DISPLAY NEW PIECE ON DECK
  }
  
  
  // returns the number of lines that were cleared
  public int clearLines()
  {
    int count = 0;
    int i,j;
    //loop through all rows, checking if they can be cleared
    for (i=0; i < grid.length && count <= 4; i++)
    {
      for (j=0; j < grid[0].length; j++)
      {
        if (grid[i][j] == 0)
          break;
      }
      
      if (j == grid[0].length) {//line should be cleared
        count++;
        //TODO: BRING EVERY LINE ABOVE DOWN ONE ROW (GRAVITY)
        //TODO: UPDATE GUI
      }
    }    
    return count;
  }
  
  
  public void linesCleared(int n)
  {    
    //update score based on n
    switch (n)
    {
      case 1:
        score += 40*level;
        break;
      case 2:
        score += 100*level;
        break;
      case 3:
        score += 300*level;
        break;
      case 4:
        score += 1200*level;
        break;
      default: //error, called with invalid n
        score = -1;
        System.out.println("ERROR: linesCleared called with param " + n);
        break;
    }
    
    // increment level every ten lines cleared, timer fires faster
    int temp = linesCleared/10;
    linesCleared += n;
    if ( (linesCleared/10) > temp ){
      level++;
      timer.setDelay((int)((50 - (level*2)) / 60.0) * 1000);
    }    
  }
  
  
  //function that gets called when the Timer fires
  private class TimeHandler implements ActionListener{
    public void actionPerformed( ActionEvent event )
    {
      if (currentPiece.moveDown() == false){
        pieceDropped();
      }
    }
  }
}