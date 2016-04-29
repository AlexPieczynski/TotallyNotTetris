
import javax.swing.*;

public class TetrisBlock extends JLabel{
    int color; //corresponds to a color
    // Pre-load images (assuming they are in directory) so that we don't have to
    // keep accessing from the hard disk, instead of memory
    private final ImageIcon[] icons = {new ImageIcon("button_white.gif"), new ImageIcon("button_yellow.gif"), 
                                    new ImageIcon("button_blue.gif"), new ImageIcon("button_green.gif"), 
                                    new ImageIcon("button_orange.gif"),new ImageIcon("button_pink.gif"),
                                    new ImageIcon("button_purple.gif"),new ImageIcon("button_red.gif")};
 
  public TetrisBlock(){
      // 0 corresponds to no color AND blank
      // hence, can be used in logic...
      // i.e. if (color == 0) space available;
      color = 0;   
  }
  
  public void setBlock(int i){
      color = i;
      this.setIcon(icons[this.color]);
  }
  
}