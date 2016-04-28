//class used to represent the current state of the Tetris game
import java.util.Timer;

public class GameState
{
  private int score;
  private int linesCleared;
  private int level;
  private Tetromino currentPiece; //reference to currently falling piece?
  private PieceType nextPiece;
  private Timer timer;
  
  public GameState()
  {
    score = 0;
    linesCleared = 0;
    level = 0;
    nextPiece = PieceType.NULL; //use PieceFactroy class
    //timer = new Timer(1000, new TimeHandler());
    //timer.start();
  }
  
  
  public void pieceDropped()
  {
    score += 10;
    nextPiece = PieceType.NULL; //use PieceFactory class
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
    
    // increment level every ten lines cleared
    int temp = linesCleared/10;
    linesCleared += n;
    if ( (linesCleared/10) > temp )
      level++;
  }
  
  
  //function that gets called when the Timer fires
//  private class TimeHandler implements ActionListener{
//    public void actionPerformed( ActionEvent event )
//    {
//      //make piece drop
//    }
//  }
}