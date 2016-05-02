import javax.swing.*;
import java.util.*;


public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    GameBoard gb = gui.getBoard();
    PieceFactory p = new PieceFactory();
    
    Tetromino t = p.getPiece(PieceType.L);
    
    t.drawPiece();
    t.rotateRight();
    
  }
}