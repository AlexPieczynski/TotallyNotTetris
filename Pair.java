//This class represents a coordinate on a 2-dimensional plane. 
//It is a very simple class that will be used to store the locations of the 4 blocks in each Tetromino.

public class Pair
{
  private int x;
  private int y;
  
  public Pair( int x, int y )
  {
    this.x = x;
    this.y = y;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public void setX( int x ){
    this.x = x;
  }
  
  public void setY( int y ){
    this.y = y;
  }
}