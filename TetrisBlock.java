import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TetrisBlock extends JComponent{
 
  private boolean occupied = false;
  private int xCord;
  private int yCord;
  protected static final int BLOCK_SIZE = 20;
  
  public TetrisBlock(int xCord, int yCord)
  {
    this.xCord = xCord;
    this.yCord = yCord;
    this.setOpaque(false);
    
  }
  
  public void drawBlock(Graphics g, int row, int col){ //Method to draw a block of a tetromino WITHOUT color.
    
       g.fillRect(xCord, yCord, BLOCK_SIZE, BLOCK_SIZE);
  }
   
   public void paintComponent(Graphics g) {//Method to draw each each block WITH color!
     super.paintComponent(g);
     drawBlock(g, this.xCord, this.yCord);
   }
  
  //Return the current X and Y values.
  public int getXCord(){
    return xCord;
  }
  
  public int getYCord(){
    return yCord;
  }
  
}