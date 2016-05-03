//A very simple class to represent a single block on the 2D Tetris grid.
//This class is used as a building block for the grid and for each Tetromino.
import javax.swing.*;

public class TetrisBlock extends JLabel{
    private int color; //corresponds to a color
    // Pre-load images (assuming they are in directory) so that we don't have to
    // keep accessing from the hard disk.
    private final ImageIcon[] icons = {new ImageIcon("button_white.gif"), new ImageIcon("button_yellow.gif"), 
                                    new ImageIcon("button_blue.gif"), new ImageIcon("button_green.gif"), 
                                    new ImageIcon("button_orange.gif"),new ImageIcon("button_pink.gif"),
                                    new ImageIcon("button_purple.gif"),new ImageIcon("button_red.gif")};
 
  public TetrisBlock(){
      // 0 corresponds to no color or blank
      // hence, can be used in logic.
      // i.e. if (color == 0) space available;
      color = 0;   //Set initial color to blank.
  }
  
  public TetrisBlock(int newColor){ //The color of each block needs to be specified upon creation.
    color = newColor;               //Helps to ensure each type of Tetromino is the same color throughout.
  }
  
  public void setBlock(int i){ //Method to change the color of a block. Use when moving/rotating Tetrominos.
      color = i;
      this.setIcon(icons[this.color]);
  }
  
  public int getColor(){ //Method to get the current color of the block object.
    return color;
  }
  
}