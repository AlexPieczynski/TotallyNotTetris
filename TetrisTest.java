import javax.swing.*;
public class TetrisTest{
  
  public static void main (String[] args){
    
    GameGUI gui = GameGUI.getInstance();
    PieceFactory p = new PieceFactory();
    Tetromino t1, t2;
    
    t1 = p.getPiece( PieceType.S);
    
    t2 = p.getPiece(PieceType.Z);
    t2.setYCord(2);
    
    t1.rotateLeft();
    
    t1.drawPiece();
    t2.drawPiece();
    
  }
}