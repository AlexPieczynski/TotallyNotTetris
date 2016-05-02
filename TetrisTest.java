import javax.swing.*;
import java.util.*;


public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    PieceFactory p = new PieceFactory();
    Tetromino t1, t2;
    boolean b;
    
    t1 = p.getPiece(PieceType.L);
    t1.drawPiece();
    t1.moveDown();
    t1.rotateRight();
    
    Vector<Pair> positions = t1.getPositions();
    int x;
    int y;
    
    for(int i = 0; i < positions.size(); i++){
      x = positions.elementAt(i).getX();
      y = positions.elementAt(i).getY();
      
       System.out.println("x: " + x + " y: " + y);
    }
  }
}