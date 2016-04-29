public class PieceFactory{
 
  public Tetromino getPiece(PieceType piece){
    
    //Base object for a peice.
    Tetromino t;
    
    //Get an I (line) piece.
    switch(piece)
    {
      case I:
        t = new IPiece();
        return t;
        
      case T:
        t = new TPiece();
        return t;
        
      case O:
        t = new OPiece();
        return t;
        
      case L:
        t = new LPiece();
        return t;
        
      case J:
        t = new JPiece();
        return t;
      
      case S:
        t = new SPiece();
        return t;
        
      case Z:
        t = new ZPiece();
        return t;
      
      default:
        return null;
    }
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