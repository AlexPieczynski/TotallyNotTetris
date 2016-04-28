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
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
      {{1,1,1,1}},   //Orientation 1.
        
      {{1},{1},{1},{1}}, //Orientation 2.
      
      {{1,1,1,1}},  //Orientation 1.
      
      {{1},{1},{1},{1}} //Orientation 2.
      }; //Set array for I shape.
            
    this.setShape(possibleShapes[0]);
  }
  
}

public class TPiece extends Tetromino{
  public TPiece(){
    
    super(PieceType.T);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,1}, //Orientation 1.
      {1,1},
      {0,1}},
     
     {{1,1,1}, //Orientation 2.
      {0,1,0}},
     
      {{1,0}, //Orientation 3.
       {1,1},
       {1,0}},
       
      {{0,1,0}, //Orientation 4.
       {1,1,1}}
        };
    this.setShape(possibleShapes[0]);
  }
}

public class OPiece extends Tetromino{
  public OPiece(){
    super(PieceType.O);
    //Create array of all possible shapes for this tetromino.
    int[][] shape = new int[][]{
      {1,1},
      {1,1}}; //Set array for O shape.
    this.setShape(shape);
  }
}

public class LPiece extends Tetromino{
  public LPiece(){
    super(PieceType.L);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{1,0}, //Orientation 1.
      {1,0},
      {1,1}},
     
     {{0,0,1}, //Orientation 2.
      {1,1,1}},
     
      {{1,1}, //Orientation 3.
       {0,1},
       {0,1}},
       
      {{1,1,1}, //Orientation 4.
       {1,0,0}}
        };
    this.setShape(possibleShapes[0]);
  }
}

public class JPiece extends Tetromino{
  public JPiece(){
    super(PieceType.J);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,1}, //Orientation 1.
      {0,1},
      {1,1}},
     
     {{1,1,1}, //Orientation 2.
      {0,0,1}},
     
      {{1,1}, //Orientation 3.
       {1,0},
       {1,0}},
       
      {{1,0,0}, //Orientation 4.
       {1,1,1}}
        };
    this.setShape(possibleShapes[0]);
  }
}
public class SPiece extends Tetromino{
  public SPiece(){
    super(PieceType.S);
     //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{1,0}, //Orientation 1.
      {1,1},
      {0,1}},
     
     {{0,1,1}, //Orientation 2.
      {1,1,0}},
     
     {{1,0}, //Orientation 3.
       {1,1},
       {0,1}},
       
     {{0,1,1}, //Orientation 4.
       {1,1,0}}
        };
    this.setShape(possibleShapes[0]);
  }
}

public class ZPiece extends Tetromino{
  public ZPiece(){
    super(PieceType.Z);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,1}, //Orientation 1.
      {1,1},
      {1,0}},
     
     {{1,1,0}, //Orientation 2.
      {0,1,1}},
     
     {{0,1}, //Orientation 3.
      {1,1},
      {1,0}},
       
     {{1,1,0}, //Orientation 4.
      {0,1,1}}
        };
    this.setShape(possibleShapes[0]);
  }
}

}