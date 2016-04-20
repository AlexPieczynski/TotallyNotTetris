//class to represent a position on a grid

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