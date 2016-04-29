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
  private Tetromino currentPiece; //reference to currently falling piece?
  private Tetromino nextPiece;
  private Timer timer;
  private int[][] grid = new int[20][10];
  
  public GameState()
  {
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
    //TODO: PUT NEW PIECE AT TOP
    currentPiece = nextPiece;
    nextPiece = p.getPiece(PieceType.getRandom());
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
      // drop falling piece down
    }
  }
}