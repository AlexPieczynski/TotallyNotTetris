//class used to represent the current state of the Tetris game
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class GameState
{
  private int score;
  private int linesCleared;
  private int level;
  private PieceFactory p;
  private Tetromino currentPiece;
  private Tetromino nextPiece;
  
  private Timer blockTimer;
  public TimeHandler blockTimeHandler;
  
  private Timer timeElapsed;
  private int time;
  
  private int[][] grid = new int[20][10];
  private GameGUI gui;
  private OnDeckGrid onDeck;
  
  
  // resets state of board
  public GameState()
  {
    score = 0;
    time = 0;
    linesCleared = 0;
    level = 1;
    
    blockTimeHandler = new TimeHandler();
    blockTimer = new Timer(800, blockTimeHandler);
    
    TimeElapsedHandler timeElapsedHandler = new TimeElapsedHandler();
    timeElapsed = new Timer(1000, timeElapsedHandler);
  }
  
  public void startTimer() {
    gui = GameGUI.getInstance();
    onDeck = gui.getOnDeck();
    p = new PieceFactory();
    gui.setScoreLabel("0");
    //TODO: CLEAR BOARD
    //TODO: CLEAR ON DECK
    
    currentPiece = p.getPiece(PieceType.getRandom());
    nextPiece = p.getPiece(PieceType.getRandom());
    currentPiece.drawPiece();
    nextPiece.drawOnDeck();    
    
    blockTimer.start();
    timeElapsed.start();
  }
  
  
  // called when a piece reaches the bottom of it's fall
  public void pieceDropped()
  {
    score += 10;
    
    // update grid
    for (Pair p : currentPiece.getPositions())
      grid[p.getY()][p.getX()] = currentPiece.blockColor;
    
    // check for lines that need to be cleared
    int c = clearLines();
    
    // update score and GUI if lines were cleared
    if (c > 0) {
       gui.getBoard().updateSpace(grid);
      linesCleared(c);
    }
    
    //update score in GUI
    gui.setScoreLabel("" + score);
    
    // place next on deck at top of board
    currentPiece = nextPiece;
    if (spotOpen())
      currentPiece.drawPiece();
    else
      gameLost();
    
    // set up next piece on deck
    nextPiece = p.getPiece(PieceType.getRandom());
    nextPiece.drawOnDeck();
    
    gui.setScoreLabel("" + score);
  }
  
  
  // returns true if there is a spot for the tetromino at
  //   the top of the board
  // false if the piece can't be place, game will then end
  public boolean spotOpen()
  {
    for (Pair p : currentPiece.getPositions())
    {
      if (grid[p.getY()][p.getX()] != 0)
        return false;
    }
    
    return true;
  }
  
  
  // checks if any lines need to be cleared
  // clears lines and implements naive gravity
  // returns the number of lines that were cleared
  public int clearLines()
  {
    int count = 0;
    int i,j;
    //loop through all rows, checking if they can be cleared
    for (i=grid.length-1; i >= 0 && count < 4; i--)
    {
      for (j=0; j < grid[0].length; j++)
      {
        if (grid[i][j] == 0)
          break;
      }
      
      if (j == grid[0].length) {//line should be cleared
        count++;
        // implement gravity
        for (j=i; j > 0; j--) {
          for (int k=0; k < grid[0].length; k++)
            grid[j][k] = grid[j-1][k];
        }
        i++;
      }
    }
    
    return count;
  }
  
  
  // update score after lines have been cleared
  // update level if we have cleared some multiple of 10 lines
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
      blockTimer.setDelay((int)((((50 - (level*2))/60.0))*1000));
    }    
  }
  
  
  //function that gets called when the Timer fires
  public class TimeHandler implements ActionListener{
    public void actionPerformed( ActionEvent event )
    {
      // move piece down one,
      //   unless piece can't move down
      if (currentPiece.moveDown() == false){
        pieceDropped();
      }
    }
  }
  
  // function that gets called to keep track of total elasped time
  public class TimeElapsedHandler implements ActionListener{
    public void actionPerformed( ActionEvent event )
    {
      time++;
      //TODO: UPDATE IN GUI
    }
  }
  
  // called when the game has been lost
  // ends play
  public void gameLost()
  {
    blockTimer.stop();
    timeElapsed.stop();
    JOptionPane.showMessageDialog(null, "Game Over.\nPlease Try Again.",
                                  "GAME OVER", JOptionPane.PLAIN_MESSAGE);
    
  }
  
  
  // prints ascii representation of grid for debugging
  public void printGrid()
  {
    System.out.println("-------------------");
    for (int i= grid.length-1 ; i >= 0; i--) {
      for (int j=0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("-------------------");
  }
  
  
  // ****** METHODS TO MOVE TETROMINOES ******
  public void moveLeft(){
      currentPiece.moveLeft();
  }
  
  
  public void moveRight(){
      currentPiece.moveRight();
  }
  
  
  public void rotateLeft(){
      currentPiece.rotateLeft();
  }
  
  
  public void rotateRight(){
      currentPiece.rotateRight();
  }
  // ****** END OF METHODS TO MOVE TETROMINOES ******
  
  
  
  // Run this to start the program
  public static void main(String[] args)
  {    
    GameGUI gui = GameGUI.getInstance();
  }
  
  
}