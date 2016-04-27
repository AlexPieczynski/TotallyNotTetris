//A parent class that each tetromino block can inherit from. 
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Tetromino extends JComponent
{
  //An array of ints to hold the "shapes" and possible "shapes" of each piece.
  //Used for collision detection.
  private int[][] shape; //Values to be set by child classes.
  
  private PieceType type; //only gets set in child classes
  private OrientationType orientation;
  private static final int BLOCK_SIZE = 20;
  
  private int xCord = 0;
  private int yCord = 0;
  
  //*****FIX ME!!!****
  //Temp variable so class will compile. To be used in GUI later to see if the board is full.
  boolean full = false;
  
  public Tetromino(PieceType type)
  { 
    this.type = type; //Set the type of piece.
    orientation = OrientationType.NORMAL; //Set default orientation.
        System.out.println("WE IN HERE!");
    
    Dimension d = new Dimension(getNumCols()*BLOCK_SIZE, getNumRows()*BLOCK_SIZE);
    this.setSize(d);
    this.setPreferredSize(d);
    this.setOpaque(false);
  }
  
  //Method to change the orientation  of a piece.
  public void setOrientation(OrientationType newOrientation){
    orientation = newOrientation;
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
  
  //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveRight()
  {
    xCord++;
  }
 
//**Methods for rotating the blocks left and right.
  public Tetromino rotateLeft() {
    if(this.type == PieceType.S){ //If square block, do nothing.
      return this;
    }
    return this;
  }
  
  public Tetromino rotateRight() {
    if(this.type == PieceType.S){ //If square block, do nothing.
      return this;
    }
    return this;
  }
  
  public int[][] getShape(){//Return the array that holds the shape coordinates.
    return shape;
  }
  
  public void setShape(int[][] shape){
    this.shape = shape;
  }
  
  public void drawBlock(Graphics g, int row, int col){ //Method to draw a block of a tetromino.
     if (shape[row][col] != 0)
       g.fillRect(BLOCK_SIZE*col+1,BLOCK_SIZE*row+1,BLOCK_SIZE-2,BLOCK_SIZE-2);
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
     System.out.println(Arrays.toString(shape));
     return shape[0].length;
   }
   
}