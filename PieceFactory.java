//A class to implement our Factory design pattern for the Tetris pieces.
//Each call to getPiece will return a randomly generated piece.

public class PieceFactory{
  
  GameGUI gui = GameGUI.getInstance();
  
  //Constructor requires a PieceType enum to help avoid errors in using the method.
  public Tetromino getPiece(PieceType piece){
    
    //Base object for a peice.
    Tetromino t;

    
    switch(piece)
    {
      case I://Get an I (line) piece.
        t = new IPiece();
        return t;
        
      case T: //Get a T piece.
        t = new TPiece();
        return t;
         
      case O: //Get an O (square) piece.
        t = new OPiece();
        return t;
        
      case L: //Get an L piece.
        t = new LPiece();
        return t;
        
      case J: //Get a J piece. Mirror image of L piece.
        t = new JPiece();
        return t;
      
      case S:
        t = new SPiece();//Get an S piece.
        return t;
        
      case Z:
        t = new ZPiece(); //Get a Z piece. Mirror image of S piece.
        return t;
      
      default:
        return null; //If a valid enum type is not provided, simply return null value.
    }
  }

//Child classes of Tetromino
//Each class has an array of all of its possible orientations. 
//Also has a variable to represent its color. Each type of piece has a different color.
public class IPiece extends Tetromino{
  public IPiece(){
    super(PieceType.I);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,1,0}, //Orientation 1
      {0,0,1,0},
      {0,0,1,0},
      {0,0,1,0}},
        
     {{0,0,0,0}, //Orientation 2
      {0,0,0,0},
      {1,1,1,1},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 3
      {0,0,1,0},
      {0,0,1,0},
      {0,0,1,0}},
        
     {{0,0,0,0}, //Orientation 4
      {0,0,0,0},
      {1,1,1,1},
      {0,0,0,0}}
      }; //Set array for I shape.
            
    this.setShape(possibleShapes[0]);
    this.blockColor = 1;
  }
}

public class TPiece extends Tetromino{
  public TPiece(){
    
    super(PieceType.T);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,0,0}, //Orientation 1
      {0,1,1,1},
      {0,0,1,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 2
      {0,0,1,1},
      {0,0,1,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 3
      {0,1,1,1},
      {0,0,0,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 4
      {0,1,1,0},
      {0,0,1,0},
      {0,0,0,0}}
      }; //Set array for T shape.
    this.setShape(possibleShapes[0]);
    this.blockColor = 2;
  }
}

public class OPiece extends Tetromino{
  public OPiece(){
    super(PieceType.O);
    //Create array of all possible shapes for this tetromino.
    int[][] shape = new int[][]{
      {0,0,0,0}, //Orientation 1
      {0,1,1,0},
      {0,1,1,0},
      {0,0,0,0}
      }; //Set array for O shape.
    this.setShape(shape);
    this.blockColor = 3;
  }
}

public class LPiece extends Tetromino{
  public LPiece(){
    super(PieceType.L);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,0,0}, //Orientation 1
      {0,1,1,1},
      {0,1,0,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 2
      {0,0,1,0},
      {0,0,1,1},
      {0,0,0,0}},
        
     {{0,0,0,1}, //Orientation 3
      {0,1,1,1},
      {0,0,0,0},
      {0,0,0,0}},
        
     {{0,1,1,0}, //Orientation 4
      {0,0,1,0},
      {0,0,1,0},
      {0,0,0,0}}
      }; //Set array for L shape.
    this.setShape(possibleShapes[0]);
    this.blockColor = 4;
  }
}

public class JPiece extends Tetromino{
  public JPiece(){
    super(PieceType.J);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,0,0}, //Orientation 1
      {0,1,1,1},
      {0,0,0,1},
      {0,0,0,0}},
        
     {{0,0,1,1}, //Orientation 2
      {0,0,1,0},
      {0,0,1,0},
      {0,0,0,0}},
        
     {{0,1,0,0}, //Orientation 3
      {0,1,1,1},
      {0,0,0,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 4
      {0,0,1,0},
      {0,1,1,0},
      {0,0,0,0}}
      }; //Set array for J shape.
    this.setShape(possibleShapes[0]);
    this.blockColor = 5;
  }
}
public class SPiece extends Tetromino{
  public SPiece(){
    super(PieceType.S);
     //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,0,0}, //Orientation 1
      {0,0,1,1},
      {0,1,1,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 2
      {0,0,1,1},
      {0,0,0,1},
      {0,0,0,0}},
        
     {{0,0,0,0}, //Orientation 3
      {0,0,1,1},
      {0,1,1,0},
      {0,0,0,0}},
        
     {{0,0,1,0}, //Orientation 4
      {0,0,1,1},
      {0,0,0,1},
      {0,0,0,0}}
      }; //Set array for S shape.
    this.setShape(possibleShapes[0]);
    this.blockColor = 6;
  }
}

public class ZPiece extends Tetromino{
  public ZPiece(){
    super(PieceType.Z);
    //Create array of all possible shapes for this tetromino.
    possibleShapes = new int[][][]{
     {{0,0,0,0}, //Orientation 1
      {0,1,1,0},
      {0,0,1,1},
      {0,0,0,0}},
        
     {{0,0,0,1}, //Orientation 2
      {0,0,1,1},
      {0,0,1,0},
      {0,0,0,0}},
        
     {{0,0,0,0}, //Orientation 3
      {0,1,1,0},
      {0,0,1,1},
      {0,0,0,0}},
        
     {{0,0,0,1}, //Orientation 4
      {0,0,1,1},
      {0,0,1,0},
      {0,0,0,0}}
      }; //Set array for Z shape.
    this.setShape(possibleShapes[0]);
    this.blockColor = 7;
  }
}//End of child classes of Tetromino. 

} //End of PieceFactory class.