# TotallyNotTetris
A game written in Java that does not even remotely resemble Tetris®.
Written by Alex Pieczynski, Alberto Hernandez, and Benjamin Carver
  for CS 342 at the University of Illinois at Chicago, Spring 2016


Design Patterns

Singleton

The main GUI class will have a single instance which other functions will need to have access to. For example, functions will need to be able to change the score label in the Main GUI when a line is cleared. Instead of passing a reference to the Main GUI to the many functions that will need access to it, we can simply call a getInstance() function from within the method that must change the GUI.

code sample from GUI:
...

    private static GameGUI INSTANCE = null;    //Create instance holder
    private GameGUI(){
        // Construct the instance
        initComponents();
    }
    
    public static void main(String args[]) {
      // Call to create the one and only instance of of the GUI 
      getInstance();
    }
  
    public static GameGUI getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameGUI();
        }
        return INSTANCE;
    }
...

Now, when another class in the program needs to access THE instance of the GUI, we can call the method getInstance;
This works because the only time an instance of the GUI can be created is when the INSTANCE variable is null at the time
of program compilation.


Factory Pattern 

We will create a class called PieceFactory, which returns an object of type Tetromino (which is the class that all the Tetrominos will be based on). This factory will take in a randomly generated integer (we can use an enum for clarity here) that corresponds to each of the 7 different pieces. It returns a Tetromino piece, useful for deciding the next Tetris piece that will begin falling when the current one drops.

Code example:
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
...      

This switch statement handles all the shape cases that we need. Each one calls its own constructor as shown below:

...

    //Child classes of Tetromino
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
            
    this.setShape(possibleShapes[0].clone());
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
    this.setShape(possibleShapes[0].clone());
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

...

This method allows us to utilize the factory to compile these different shapes for us when we need them. In other words, the factory acts like a constructor for the tetrominos.
