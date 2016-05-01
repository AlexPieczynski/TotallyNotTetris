import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    PieceFactory p = new PieceFactory();
    Tetromino t1, t2;
    
    t1 = p.getPiece( PieceType.Z);
    t1.drawPiece();
    
  }
}