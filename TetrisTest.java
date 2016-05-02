import javax.swing.*;
import java.util.*;


public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    GameBoard gb = gui.getBoard();
    PieceFactory p = new PieceFactory();
    
    Tetromino t1 = p.getPiece(PieceType.L);
    Tetromino t2 = p.getPiece(PieceType.Z);
     
    t1.drawPiece();
    t2.drawOnDeck();
    
  }
}