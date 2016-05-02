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
  private Vector<Pair> positions = new Vector<Pair>(4);
  
  protected GameGUI gui;
  protected GameBoard gb;
  protected TetrisBlock[][] onDeckBlocks;
  protected TetrisBlock[][] blocks;
  protected static int[][] logicGrid =  new int[20][10];
  
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
    this.onDeckBlocks = gui.getOnDeck().getOnDeckSpace();
  }
  
  //Method to get coordinates of each Tetromino block.
  public Vector<Pair> getPositions(){
    
    return positions;
  }
  
  public int[][] getLogicGrid(){
    return logicGrid;
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
      this.eraseFromLogic();
      
      yCord++;
      
      this.updatePositions();
      this.drawPiece();
      this.drawToLogic();
      
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
      this.eraseFromLogic();
      
      xCord--;
      
      this.updatePositions();
      this.drawPiece();
      this.drawToLogic();
    }
  }
  
   //called from keyListener for right arrow key
  //move the piece to the right one spot on the grid, if possible
  //returns true if the piece moved, false otherwise
  public void moveRight(){

    if(canMoveRight()) {
      this.erasePiece();
      this.eraseFromLogic();
      
      xCord++;
      
      this.updatePositions();
      this.drawPiece();
      this.drawToLogic();
    }
  }
  
  //**Methods for rotating the blocks left and right.
  public void rotateLeft() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }
    if(canRotateLeft()){
      
     this.erasePiece();
     this.eraseFromLogic();
     
     rotate(); //The default rotation is left so simply do one rotation.
     
     this.updatePositions();
     this.drawPiece();
     this.drawToLogic();
    }
  }
  
  public void rotateRight() {
    if(this.type == PieceType.O){ //If square block, do nothing.
      return;
    }//For all other blocks rotate 3 times (3 left rotations == 1 right rotation).
    if(canRotateRight()){
      
      this.erasePiece();
      this.eraseFromLogic();
      
      for(int i = 0; i < 3; i++){ rotate();}
      
      this.updatePositions();
      this.drawPiece();
      this.drawToLogic();
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
    
    updatePositions();
    
    while(temp.getOrientation() != this.orientation ) //Rotate until temp piece is oriented like old piece.
      temp.rotate();
    
    return temp;
  }
  
  public void setLogicGrid(){
    
    for(int i = 0; i < 20; i++)
      for(int j = 0; j < 10; j++)
       logicGrid[i][j] = blocks[i][j].getColor();
  }
  
  public int[][] getShape(){//Return the array that holds the shape coordinates.
    return shape;
  }
  
  public void setShape(int[][] newShape){ //Set the shape array and dimensions of the Tetromino
    
    this.shape = new int[4][4];
    positions.clear();//Make sure positions array is empty each time we set new shape.
    
    // set up positions array
    for (int row = 0; row < 4; row++){
      for (int col = 0; col < 4; col++){
        if(newShape[row][col] != 0){
          shape[row][col] = 1;       //Setup the new shape array and its positions Vector.
          positions.add(new Pair(col+xCord, row+yCord));
        }
      }
    }
  }
  
  public void updatePositions(){ //Method for updating the positions Vector.
    
    positions.clear(); //First clear the Vector to help avoid errors.
    
    for (int row = 0; row < 4; row++)
      for (int col = 0; col < 4; col++)
      if(shape[row][col] != 0){
        positions.add(new Pair(col+xCord, row+yCord));
    }
      
  }
  //-----Start of methods to check for valid  moves.
  
  public boolean canRotateLeft(){ //Helper method to see if the Tetromino is allowed to rotate.
    
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    this.eraseFromLogic(); //Remove from the grid logic to avoid unwanted collisions.
    
    temp.rotate(); //Try a left rotation.
    temp.updatePositions();
    
    for (Pair p : temp.getPositions()) { //Check for collisions at one left rotation.
      
      if(temp.getLogicGrid()[p.getY()][p.getX()] != 0){
          //Not allowed to rotate, so return false. Print statement for testing purposes.
        System.out.println("Error trying to rotate left: not a valid move!");
        this.drawToLogic();
        return false;
      }
    }
    //Allowed to rotate. Return true.
    return true;
  }
  
  public boolean canRotateRight(){ //Helper method to see if the Tetromino is allowed to rotate.
  
    Tetromino temp = this.clonePiece(); //Clone the piece to avoid modifying original piece.
    this.eraseFromLogic();
    
    for(int i = 0; i < 3; i++){ temp.rotate();} //Try a right rotation.
    temp.updatePositions();
    
    
    for (Pair p : temp.getPositions()) { //Check for collisions at one right rotation.
      
       if(temp.getLogicGrid()[p.getY()][p.getX()] != 0){
          //Not allowed to rotate, so return false. Print statement for testing purposes.
          System.out.println("Error trying to rotate right: not a valid move!");
          this.drawToLogic();
          return false;
       }
    }
    //Allowed to rotate. Return true.
    return true;
  }
  
  public boolean canMoveLeft(){ //Check to see if we are allowed to move left.
    
    int leftSide = leftMostCol();
    
    if(leftSide + xCord - 1 < 0){ //Already at left limit of screen.
        return false;}
    
    this.eraseFromLogic(); //Remove the piece from logic grid to avoid accidental collision detection
    
    for (Pair p : this.getPositions()){
      
      if(logicGrid[p.getY()][p.getX() - 1] != 0) { //Check fro collisions at one space to right.
        this.drawToLogic(); //Found a collision so return false.
        return false;
      }
    }
    //Allowed to move left. Return true.
    return true;
  }
  
  public boolean canMoveRight(){  //Check to see if we are allowed to move right. 
    
    int rightSide = rightMostCol();
    
    if(rightSide + xCord + 1 > 9){ //Already at right limit of screen.
        return false;}
    
    this.eraseFromLogic(); //Remove the piece from logic grid to avoid accidental collision detection
    
    for (Pair p : this.getPositions()){
      
      if(logicGrid[p.getY()][p.getX() + 1] != 0) { //Check fro collisions at one space to right.
        this.drawToLogic(); //Found a collision so return false.
        return false;
      }
    }
    //Allowed to move right. Return true.
    return true;
  }
  
  public boolean canMoveDown(){
    
    int bottom = lowestRow();
    
    if(bottom + yCord + 1 > 19){ //Already at bottom of grid so return.
      return false;
    }
    
    this.eraseFromLogic(); //Remove the piece from logic grid to avoid accidental collision detection
    
    
    for (Pair p : this.getPositions()){
      
      if(logicGrid[p.getY() + 1][p.getX()] != 0) { //Check for collisions at one space down.
        
        this.drawToLogic(); //Found a collision so return false. Redraw the old position before exiting.
        System.out.println("Error: cannot move down further");
        return false;
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
    
    for (Pair p : this.getPositions()){
      blocks[p.getY()][p.getX()].setBlock(blockColor);
    }
    setLogicGrid();
    drawToLogic(); //Copy to the logic array.
  }
  
  public void drawOnDeck(){ //Draw a Tetromino to "On Deck" space.
    for(int i = 0; i <4; i++){
      for(int j = 0; j < 4; j++){
        if(shape[i][j] != 0)
          onDeckBlocks[i][j].setBlock(blockColor);
        else
          onDeckBlocks[i][j].setBlock(0);
      }
    }
  }
  
  public void erasePiece(){ //A helper method for removing each piece from grid.
    
    for (Pair p : this.getPositions()){
      blocks[p.getY()][p.getX()].setBlock(0);
    }
  }
  
  public void drawToLogic(){
    for (Pair p : this.getPositions()){   //Update the logic grid. Used for collision detection.
      logicGrid[p.getY()][p.getX()] = blockColor;
    }
  }
  
  public void eraseFromLogic(){
    for (Pair p : this.getPositions()){
      logicGrid[p.getY()][p.getX()] = 0;
    }
  }
  
  public void printLogicGrid(){ //Used for testing.
    System.out.println(" - - - - - - - - - - ");
     for(int i = 0; i < 20; i++){
      for(int j = 0; j < 10; j++){
        System.out.print(" " + logicGrid[i][j]);
      }
      System.out.println();
    }
     System.out.println(" - - - - - - - - - - ");
  }
  
  public void printPositions(){ //Helper method for testing.
    if(getPositions().size() == 0){
      System.out.println("Positions is empty.");}
    else{
     for (Pair p : this.getPositions()){   //Print current positions.
      System.out.print(" x: "+ p.getX() +" y: " + p.getY());
     }
     System.out.println();
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