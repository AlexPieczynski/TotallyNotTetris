//A parent class that each tetromino block can inherit from. 
import java.util.Vector;

public abstract class Tetromino
{
  
 //A vector to hold the locations of the 4 blocks in a each tetromino.
  private Vector<Pair> positions = new Vector<Pair>(4);
  private PieceType type; //only gets set in child classes
  
  private OrientationType orientation;
  private Pair pivot = null;//The pivot piece that each tetromino will rotate around.
                            //Null by default. Also null in the square tetrominos.
  
  
  //*****FIX ME!!!****
  //Temp variable so class will compile. To be used in GUI later to see if the board is full.
  boolean full = false;
  
  public Tetromino()
  {
    for (int i=0; i < positions.capacity(); i++) //set position to something invalid
      positions.set(i, new Pair(-1,-1));
    
    type = PieceType.NULL;        //set type to something invalid
    orientation = OrientationType.NORMAL; //Set default orientation.
  }
  
  //Method to set the piece in the child class.
  public void setPiece(PieceType type){
    this.type = type; 
  }
  
  //the method called each time the timer hits to drop the piece down one
  //returns false when it hits the ground and can't move down any longer
  public boolean moveDown()
  {
    //get a reference to GUI grid
    
    //check if block is clear to move down one space
    for (Pair p : positions){
      if (gridAt(p.getX(), p.getY() - 1) == full)
        return false;
    }
    
    //block is clear, move each coordinate down one
    for (Pair p : positions)
      p.setY( p.getY() - 1 );
    
    return true;
  }
  
  //called from keyListener for left arrow key
  //move the piece to the left one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public boolean moveLeft()
  {
    for (Pair p : positions){
      if (gridAt( p.getX()-1, p.getY()) == full)
        return false;
    }
    
    for (Pair p : positions)
      p.setX(p.getX() - 1);
    return true;
  }
  
  
  //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public boolean moveRight()
  {
    for (Pair p : positions){
      if (gridAt( p.getX()+1, p.getY()) == full)
        return false;
    }
    
    for (Pair p : positions)
      p.setX(p.getX() + 1);
    return true;
  }
  
  //********FIX ME!!!****
  //A temp method so the class will compile. To be implemented in the GUI later.
  public boolean gridAt(int x, int y){
    return true;
  }
  
  /*
   //**Methods for rotating the blocks left and right.
   //**Can be the same for all blocks. Will update code later.
  
  public boolean rotateLeft() {
  }
  
  public boolean rotateRight() {}
  
  
  //Code that will randomly assign this tetromino to be one of the 7 types of pieces.
  public void setPieceType(){} 
  
  */
  
}