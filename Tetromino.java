//A parent class that each tetromino block can inherit from. 
import java.util.*;
import javax.swing.*;
import java.awt.*;

public abstract class Tetromino
{
  //An array of ints to hold the "shapes" and possible "shapes" of each piece.
  //Used for collision detection.
  
  protected int[][][] possibleShapes;
  protected int[][] shape; //Values to be set by child classes.
  
  protected GameGUI gui;
  protected GameBoard gb;
  protected TetrisBlock[][] blocks;
  
  protected final int ROW_MAX = 20; //Max height of the grid in Tetris blocks.
  protected final int COL_MAX= 10; //Max witdth of the grid in Tetris blocks.
  protected final int ROW_PIVOT = 1; // [Row 1][Column 2] in each block will be it's pivot point.
  protected final int COL_PIVOT = 2;
  
  protected int oStatus = 0; //Used to cycle through OrientationType enum.
  
  protected PieceType type; //only gets set in child classes
  protected OrientationType orientation;
  protected int blockColor;
  protected static final int BLOCK_SIZE = 20;
  
  protected int xCord = 3; //Offset the starting position by 3. Makes sure each new piece is centered. 
  protected int yCord = 0;
  
  public Tetromino(PieceType type)
  { 
    this.type = type; //Set the type of piece.
    orientation = OrientationType.NORMAL; //Set default orientation.
    this.shape = shape; //Set other data members.
    this.gui = GameGUI.getInstance();
    this.gb = gui.getBoard();
    this.blocks = gb.getGameSpace();
  }
  
  //Method to change the orientation  of a piece.
  public void setOrientation(OrientationType orientation){
    this.orientation = orientation;
  }
  
  //the method called each time the timer hits to drop the piece down one
  //returns false when it hits the ground and can't move down any longer
  public boolean moveDown()
  {
    yCord--;
    return true;
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
  //Methods to set the X and Y coordinates. Used for testing purposes.
  public void setXCord(int x){
    xCord = x;
  }
  
  public void setYCord(int y){
    yCord = y;
  }
  
  //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveRight()
  {
    xCord++;
  }
 
//**Methods for rotating the blocks left and right.
  public void rotateLeft() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }
    //if(canRotateLeft())
     rotate(); //The default rotation is left so simply do one rotation.
  }
  
  public void rotateRight() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }//For all other blocks rotate 3 times (3 left rotations == 1 right rotation).
    //if(canRotateRight())
      for(int i = 0; i < 3; i++){ rotate();}
  }
  
  public int[][] getShape(){//Return the array that holds the shape coordinates.
    return shape;
  }
  
  public void setShape(int[][] shape){ //Set the shape array and dimensions of the Tetromino
    this.shape = shape;
  }
  
  /*
  public boolean canRotateLeft(){ //Helper method to see if the Tetromino is allowed to rotate.
    
    rotate(); //First try a left rotation.
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(shape[row][col] != 0 && blocks[row + yCord][col + xCord].getColor() != 0){
          rotateRight();  //Not allowed to rotate. Restore to previous state and return false.
          //Print statement for testing purposes.
          System.out.println("Error trying to rotate right: not a valid move!");
          return false;
        }
      }
    }
    rotateRight(); //Allowed to rotate. Restore to previous state and return true.
    return true;
  }
  
    public boolean canRotateRight(){ //Helper method to see if the Tetromino is allowed to rotate.
    
    for(int i = 0; i < 3; i++){ rotate();} //First try a right rotation.
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(shape[row][col] != 0 && blocks[row + yCord][col + xCord].getColor() != 0){
          rotateLeft();  //Not allowed to rotate. Restore to previous state and return false.
          //Print statement for testing purposes.
          System.out.print("Error trying to rotate left: not a valid move!");
          return false;
        }
      }
    }
    rotateLeft(); //Allowed to rotate. Restore to previous state and return true.
    return true;
  }*/
  
  public void drawPiece() {//Method to draw each Tetromino
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(shape[row][col] != 0){
          blocks[row + yCord][col + xCord].setBlock(blockColor);
        }
      }
    }
  }
  
  public void erasePiece(){ //A helper method for removing each piece.
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(blocks[row + yCord][col + xCord].getColor() == blockColor) {
          blocks[row+yCord][col+xCord].setBlock(0);  //Set color back to white.
        }
      }
    }
  }
   
   public void rotate(){ //Helper method to rotate the piece. Performs 1 left rotation.
     
     if(this.type == PieceType.O){ //If square piece, do nothing.
       return;
     }
     else //For all others, cycle through list of possible values.
       this.oStatus = (this.oStatus+1)%4; 
     
     setShape(possibleShapes[oStatus]); //Update the new shape. 
     OrientationType[] values = OrientationType.values(); //Update new orientation.
     setOrientation(values[oStatus]);
   }
   
}