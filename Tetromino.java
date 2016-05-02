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
  protected final int COL_MIN = 0;
  protected final int COL_MAX= 10; //Max witdth of the grid in Tetris blocks.
  
  protected final int ROW_PIVOT = 1; // [Row 1][Column 2] in each block will be it's pivot point.
  protected final int COL_PIVOT = 2; //Might not need to use these
  
  protected int oStatus = 0; //Used to cycle through OrientationType enum.
  
  protected PieceType type; //only gets set in child classes
  protected OrientationType orientation;
  protected int blockColor;
  
  protected int xCord = 3; //Offset the starting positions. 
  protected int yCord  = 0; //Makes sure each new piece is centered.
  
  public Tetromino(PieceType type){
    
    this.type = type; //Set the type of piece.
    orientation = OrientationType.NORMAL; //Set default orientation.
    //Set other data members.
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
  public boolean moveDown(){

    if(canMoveDown()){
      this.erasePiece();
      yCord++;
      this.drawPiece();
      
      return true;
    }
     
    return false; //If got here, not able to move down.    
  }
  
  //called from keyListener for left arrow key
  //move the piece to the left one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveLeft(){
    
    if(canMoveLeft()){
      this.erasePiece();
      xCord--;
      this.drawPiece();
    }
  }
  
   //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveRight(){

    if(canMoveRight()) {
      this.erasePiece();
      xCord++;
      this.drawPiece();
    }
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
  
  public OrientationType getOrientation(){ //Helper method to return the current orientation of the piece.
    return orientation;
  }
  
  public Tetromino clonePiece(){ //Helper method to clone a Tetromino
    
    Tetromino temp = new PieceFactory().getPiece(type);
    temp.setXCord(this.xCord);
    temp.setYCord(this.yCord);
    
    while(temp.getOrientation() != this.orientation ) //Rotate until temp piece is oriented like old piece.
      temp.rotate();
    
    return temp;
  }
 
//**Methods for rotating the blocks left and right.
  public void rotateLeft() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }
    if(canRotateLeft()){
     rotate(); //The default rotation is left so simply do one rotation.
     this.drawPiece();
    }
  }
  
  public void rotateRight() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }//For all other blocks rotate 3 times (3 left rotations == 1 right rotation).
    if(canRotateRight()){
      this.erasePiece();
      for(int i = 0; i < 3; i++){ rotate();}
      this.drawPiece();
    }
  }
  
  public int[][] getShape(){//Return the array that holds the shape coordinates.
    return shape;
  }
  
  public void setShape(int[][] shape){ //Set the shape array and dimensions of the Tetromino
    this.shape = shape.clone();
  }
  //-----Start of methods to check for valid  moves.
  
  public boolean canRotateLeft(){ //Helper method to see if the Tetromino is allowed to rotate.
    
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    temp.rotate(); //First try a left rotation.
    int [][] tempShape = temp.getShape();
    this.erasePiece();
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(tempShape[row][col] != 0 && blocks[row + yCord][col + xCord].getColor() != 0){
        //Not allowed to rotate, so return false. //Print statement for testing purposes.
          System.out.println("Error trying to rotate left: not a valid move!");
          this.drawPiece();
          return false;
        }
      }
    }//Allowed to rotate. Return true.
    return true;
  }
  
  public boolean canRotateRight(){ //Helper method to see if the Tetromino is allowed to rotate.
    
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    for(int i = 0; i < 3; i++){ temp.rotate();} //First try a right rotation.
    int [][] tempShape = temp.getShape();
    this.erasePiece();
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(tempShape[row][col] != 0 && blocks[row + yCord][col + xCord].getColor() != 0){
          ////Not allowed to rotate, so return false. Print statement for testing purposes.
          System.out.println("Error trying to rotate right: not a valid move!");
          this.drawPiece();
          return false;
        }
      }
    }
    //Allowed to rotate. Return true.
    return true;
  }
  
  public boolean canMoveLeft(){
    
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    int [][] tempShape = temp.getShape();
    
    int leftSide = leftMostCol();
    
    if(leftSide + xCord - 1 < 0){ //Already at left limit of screen.
        return false;}
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        
        if(tempShape[row][col] != 0 && blocks[row + yCord][col + xCord - 1].getColor() != 0){
          return false;
        }
      }
    }
    //Allowed to move left. Return true.
    return true;
  }
  
  public boolean canMoveRight(){
    
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    int [][] tempShape = temp.getShape();
    
    int rightSide = rightMostCol();
    
    if(rightSide + xCord + 1 > 9){ //Already at right limit of screen.
        return false;}
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        
        if(tempShape[row][col] != 0 && blocks[row + yCord][col + xCord + 1].getColor() != 0){
          return false;
        }
      }
    }
    //Allowed to move left. Return true.
    return true;
  }
  
  public boolean canMoveDown(){
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    int [][] tempShape = temp.getShape();
    
    int bottom = lowestRow();
    
    if(bottom + yCord + 1 > 19){ //Already at bottom of grid.
      return false;}
    
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        
        if(tempShape[row][col] != 0 && blocks[row + yCord + 1][col + xCord].getColor() != 0){
          return false;
        }
      }
    }
    //Allowed to move down. Return true.
    return true;
  }
  
  public int lowestRow(){  //Return the index of the first non-zero row in the shape array.
    int lowestRow = 0;
    
    for (int row = 0; row < 4; row++)
      for (int col = 0; col < 4; col++)
       if(shape[row][col] != 0){
         if (row > lowestRow)
           lowestRow = row;}
      
    return lowestRow;
  }
  
  public int highestRow(){  //Return the index of the first non-zero row in the shape array.
    int highestRow = 3;
    
    for (int col = 0; col < 4; col++)
      for (int row = 0; row < 4; row++)
       if(shape[row][col] != 0){
         if (row < highestRow)
           highestRow = row;}
    
    return highestRow;
  }
  
  public int leftMostCol(){ //Return index of leftmost non-zero column in shape array.
    int leftMostCol = 3;
    
    for (int row = 0; row < 4; row++)
      for (int col = 0; col < 4; col++)
      if(shape[row][col] != 0){
         if (col < leftMostCol)
           leftMostCol = col;}
        
    return leftMostCol;
  }
  
  public int rightMostCol(){ //Return index of rightmost non-zero column in shape array.
    int rightMostCol = 0;
    
    for (int row = 0; row < 4; row++)
      for (int col = 0; col < 4; col++)
       if(shape[row][col] != 0){
         if (col > rightMostCol)
           rightMostCol = col;}
    
    return rightMostCol;
  }
  
  //-------------------End of methods to check for valid moves-------------------
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
        if(shape[row][col] != 0) {
          blocks[row+yCord][col+xCord].setBlock(0);  //Set color back to white.
        }
      }
    }
  }
   
   private void rotate(){ //Helper method to rotate the piece. Performs 1 left rotation.
     
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