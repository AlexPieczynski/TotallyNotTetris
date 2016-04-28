import javax.swing.*;
public class TetrisTest extends JFrame{
  
  public static void main (String[] args){
    TetrisTest test = new TetrisTest();
    test.setVisible(true);
    
    PieceFactory p = new PieceFactory();
    Tetromino t = p.getPiece("S");
    t.rotate();
    test.setSize(500,500);
    
    test.add(t);
    t.repaint();
  }
}