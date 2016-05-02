import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    PieceFactory p = new PieceFactory();
    Tetromino t1, t2;
    boolean b;
    
    t1 = p.getPiece(PieceType.L);
    t1.drawPiece();
    t1.moveDown();
    t1.moveDown();
    t1.moveDown();
    
    t2 = p.getPiece(PieceType.J);
    t2.drawPiece();
    t2.moveDown();
    t2.moveDown();
    t2.rotateRight();
    
    t1.rotateLeft();
  }
}