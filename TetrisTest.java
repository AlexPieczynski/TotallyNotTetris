import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    PieceFactory p = new PieceFactory();
    Tetromino t1, t2;
    
    t1 = p.getPiece(PieceType.L);
    t1.drawPiece();
    
    t2 = t1.clonePiece();
    t2.setYCord(t2.getYCord() + 2);
    t2.drawPiece();
    t2.rotateLeft();
    t2.moveLeft();
    t2.moveLeft();  //Test to make sure pieces do not move out of bounds.
  }
}