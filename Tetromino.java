//A parent class that each tetromino block can inherit from. 
import java.util.*;
import javax.swing.*;
import java.awt.*;

public abstract class Tetromino extends JComponent
{
  //An array of ints to hold the "shapes" and possible "shapes" of each piece.
  //Used for collision detection.
  
  protected int[][][] possibleShapes;
  protected int[][] shape; //Values to be set by child classes.
  
  protected int oStatus = 0; //Used to cycle through OrientationType enum.
  
  protected PieceType type; //only gets set in child classes
  protected OrientationType orientation;
  protected static final int BLOCK_SIZE = 20;
  
  protected int xCord = 200;
  protected int yCord = 0;
  
  //*****FIX ME!!!****
  //Temp variable so class will compile. To be used in GUI later to see if the board is full.
  boolean full = false;
  
  public Tetromino(PieceType type)
  { 
    this.type = type; //Set the type of piece.
    orientation = OrientationType.NORMAL; //Set default orientation.
    this.shape = shape;
  }
  
  //Method to change the orientation  of a piece.
  public void setOrientation(OrientationType orientation){
    this.orientation = orientation;
  }
  
  //the method called each time the timer hits to drop the piece down one
  //returns false when it hits the ground and can't move down any longer
  public void moveDown()
  {
    yCord--;
  }
  
  //called from keyListener for left arrow key
  //move the piece to the left one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveLeft()
  {
   xCord--;
  }
  //Return the current X and Y values.
  public int getXCord(){
    return xCord;
  }
  
  public int getYCord(){
    return yCord;
  }
  
  //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveRight()
  {
    xCord++;
  }
 
//**Methods for rotating the blocks left and right.
  public Tetromino rotateLeft() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return this;
    }
    return this;
  }
  
  public Tetromino rotateRight() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return this;
    }
    return this;
  }
  
  public int[][] getShape(){//Return the array that holds the shape coordinates.
    return shape;
  }
  
  public void updateLocation(){
    this.setLayout(null);
    setLocation(100,0);
  }
  
  public void setShape(int[][] shape){ //Set the shape array and dimensions of the Tetromino
    this.shape = shape;
    
    Dimension d = new Dimension(getNumCols()*BLOCK_SIZE, getNumRows()*BLOCK_SIZE);
    this.setSize(d);
    this.setPreferredSize(d);
    this.setOpaque(false);
  }
  
  public void drawBlock(Graphics g, int row, int col){ //Method to draw a block of a tetromino.
     if (shape[row][col] != 0)
       g.fillRect(xCord+BLOCK_SIZE*col+1,yCord+BLOCK_SIZE*row+1,BLOCK_SIZE-2,BLOCK_SIZE-2);
  }
   
   public void paintComponent(Graphics g) {//Method to draw each Tetromino
     super.paintComponent(g);
      for (int row = 0; row < shape.length; row++) {
        for (int col = 0; col < shape[row].length; col++){
          drawBlock(g, row,col);
        }
      }
   }
  
   public int getNumRows(){
     return shape.length;
   }
   
   public int getNumCols(){
     return shape[0].length;
   }
   
   public void rotate(){
     
     if(this.type == PieceType.O){ //If square piece, do nothing.
       return;
     }
     else //For all others, cycle through list of possible values.
       this.oStatus = (this.oStatus+1)%4; 
     }
     
     setShape(possibleShapes[oStatus]); //Update the new shape. 
     OrientationType[] values = OrientationType.values(); //Update new orientation.
     setOrientation(values[oStatus]);
   }
   
}