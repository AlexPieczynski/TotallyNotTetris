//class used to represent the current state of the Tetris game
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class GameState
{
  private int score;
  private PieceFactory p;
  private int linesCleared;
  private int level;
  private Tetromino currentPiece;
  private Tetromino nextPiece;
  private Timer timer;
  public int[][] grid = new int[20][10];
  private GameGUI gui;
  private OnDeckGrid onDeck;
  public TimeHandler timeHandler;
  
  public GameState()
  {
    p = new PieceFactory();
    score = 0;
    linesCleared = 0;
    level = 1;
    currentPiece = p.getPiece(PieceType.getRandom());
    nextPiece = p.getPiece(PieceType.getRandom());
    timeHandler = new TimeHandler();
    timer = new Timer(800, timeHandler);
  }
  
  public void startTimer() {
    gui = GameGUI.getInstance();
    gui.setScoreLabel("0");
    onDeck = gui.getOnDeck();
    currentPiece.drawPiece();
    //TODO: DRAW IN ON_DECK
    timer.start();
  }
  
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
  
  // called when a piece reaches the bottom of it's fall
  public void pieceDropped()
  {
    score += 10;
    
    // update grid
    for (Pair p : currentPiece.getPositions())
      grid[p.getY()][p.getX()] = 1;
    
    // check for lines that need to be cleared
    int c = clearLines();
    
    // update score if lines were cleared
    if (c > 0)
      linesCleared(c);
    
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
    //TODO: MAKE GUI DISPLAY NEW PIECE ON DECK
    
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
    for (i=0; i < grid.length && count < 4; i++)
    {
      for (j=0; j < grid[0].length; j++)
      {
        if (grid[i][j] == 0)
          break;
      }
      
      if (j == grid[0].length) {//line should be cleared
        count++;
        // implement gravity
        for (j=i; j < grid.length-1; j++) {
          for (int k=0; k < grid[0].length; k++)
            grid[j][k] = grid[j+1][k];
        }
        i--;
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
      timer.setDelay((int)((50 - (level*2)) / 60.0) * 1000);
    }    
  }
  
  
  //function that gets called when the Timer fires
  public class TimeHandler implements ActionListener{
    public void actionPerformed( ActionEvent event )
    {
      // move piece down one,
      //   unless piece can't move down
      System.out.println("TIMER FIRED");
      if (currentPiece.moveDown() == false){
        pieceDropped();
      }
    }
  }
  
  
  // called when the game has been lost
  // ends play
  public void gameLost()
  {
    timer.stop();
    JOptionPane.showMessageDialog(null, "Game Over.\nPlease Try Again.",
                                  "GAME OVER", JOptionPane.PLAIN_MESSAGE);
    
  }
  
  
  // update GUI with grid
  public void updateGUI()
  {
    TetrisBlock[][] guiGrid = gui.getBoard().getGameSpace();
    for (int i=0; i < grid.length; i++) {
      for (int j=0; j < grid[i].length; j++) {
        guiGrid[i][j].setBlock(grid[i][j]);
      }
    }
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
  
  
  
  // TEST CODE
  public static void main(String[] args)
  {
//    GameState gs = new GameState();
//    int[][] grid = gs.grid;  
//    
//    // fill grid, except top row
//    for (int i=0; i < grid.length-1; i++) {
//      for (int j=0; j < grid[0].length; j++) {
//        grid[i][j] = 1;
//      }
//    }
//    
//    gs.printGrid();
//    gs.clearLines();
//    gs.printGrid();
//    
//    // fill grid, different pattern
//    for (int i=0; i < grid.length-1; i++) {
//      for (int j=0; j < grid[0].length; j++) {
//        grid[i][j] = 0;
//      }
//    }
//    for (int i=0; i < grid.length-1; i++) {
//      for (int j=0; j < 5; j++) {
//        grid[i][j] = 1;
//      }
//    }
//    
//    gs.printGrid();
//    gs.clearLines();
//    gs.printGrid();
    
    GameGUI gui = GameGUI.getInstance();
  }
}