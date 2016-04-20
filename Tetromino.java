import java.util.Vector;

public class Tetromino
{
  private Vector<Pair> positions = new Vector<Pair>(4);
  private PieceType type; //only gets set in child classes
  
  
  public Tetromino()
  {
    for (int i=0; i < positions.capacity(); i++) //set position to something invalid
      positions.set(i, new Pair(-1,-1));
    
    type = PieceType.NULL;        //set type to something invalid
  }
  
  //the method called each time the timer hits to drop the piece down one
  //returns false when it hits the ground and can't move down any longer
  public boolean fallOne()
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
  }
}