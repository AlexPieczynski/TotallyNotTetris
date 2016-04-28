import javax.swing.*;
public class TetrisTest extends JFrame{
  
  public static void main (String[] args){
    TetrisTest test = new TetrisTest();
    test.setVisible(true);
    
    TetrisBlock block = new TetrisBlock(0,0);
    
    /*
    PieceFactory p = new PieceFactory();
    Tetromino t = p.getPiece("L");
    //t.rotate();
    */
    test.setSize(500,500);
    
    test.add(block);
    block.repaint();
  }
}