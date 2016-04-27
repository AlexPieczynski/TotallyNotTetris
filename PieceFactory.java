public class PieceFactory{
 
  public Tetromino getPiece(String piece){
    
    //Base object for a peice.
    Tetromino t;
    
    //Get an I (line) piece.
    if ( piece.equalsIgnoreCase("I") ){
      t = new IPiece();
      return t;
    }
    //Get a T piece.
     if ( piece.equalsIgnoreCase("T") ){
       t = new TPiece();
      return t;
    }
     //Get an O (square) piece.
     if ( piece.equalsIgnoreCase("O") ){
       t = new OPiece();
      return t;
    }
     //Get an L piece.
     if ( piece.equalsIgnoreCase("L") ){
      t = new LPiece();
      return t;
    }
     //Get a J piece.
     if ( piece.equalsIgnoreCase("J") ){
      t = new JPiece();
      return t;
    }
     //Get an S piece.
     if ( piece.equalsIgnoreCase("S") ){
      t = new SPiece();
      return t;
    }
    //Get a Z piece.
     if ( piece.equalsIgnoreCase("Z") ){
       t = new ZPiece();
      return t;
     }
     
     //If made it all the way here, did not specify valid type so return NULL.
     return null;
  }

  
//Child classes of Tetromino
public class IPiece extends Tetromino{
  public IPiece(){
    super(PieceType.I);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{{1,1,1,1}}; //Set array for I shape.
    this.setShape(shape);
  }
  
}

public class TPiece extends Tetromino{
  public TPiece(){
    super(PieceType.T);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {0,1},
      {1,1},
      {0,1}};
    this.setShape(shape);
  }
}

public class OPiece extends Tetromino{
  public OPiece(){
    super(PieceType.O);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {1,1},
      {1,1}}; //Set array for O shape.
    this.setShape(shape);
  }
}

public class LPiece extends Tetromino{
  public LPiece(){
    super(PieceType.L);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {1,0},
      {1,0},
      {1,1}}; //Set array for L shape.
    this.setShape(shape);
  }
}

public class JPiece extends Tetromino{
  public JPiece(){
    super(PieceType.J);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {0,1},
      {0,1},
      {1,1}}; //Set array for J shape.
    this.setShape(shape);
  }
}
//NOTE: S & Z pieces are rotated on creation to stay consistent with most Tetris implementations.
public class SPiece extends Tetromino{
  public SPiece(){
    super(PieceType.S);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {1,0},
      {1,1},
      {0,1}}; //Set array for S shape.
    this.setOrientation(OrientationType.LEFT_TILTED);
    this.setShape(shape);
  }
}

public class ZPiece extends Tetromino{
  public ZPiece(){
    super(PieceType.Z);
    int[][] shape; //Get reference to the shape array.
    shape = new int[][]{
      {0,1},
      {1,1},
      {1,0}}; //Set array for L shape.
    this.setOrientation(OrientationType.RIGHT_TILTED);
    this.setShape(shape);
  }
}
  
  
}