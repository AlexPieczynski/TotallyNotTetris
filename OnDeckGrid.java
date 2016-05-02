import java.awt.GridLayout;

import javax.swing.JPanel;

public class OnDeckGrid extends JPanel {
	private TetrisBlock[][] onDeckSpace;

	public OnDeckGrid(){
		onDeckSpace = new TetrisBlock[4][4];
		
		//Build blank board
        GridLayout layout = new GridLayout(4, 4);
        this.setLayout(layout);
        
        resetSpace();
	}
	
	public void resetSpace(){
	    int i = 0;
	    int j = 0;
	    //Using regular nested loops, not sure how to do the other method for 2d...
	     for(i = 0; i<4; i++){
	        for(j = 0; j<4; j++){
	            onDeckSpace[i][j] = new TetrisBlock();
	            onDeckSpace[i][j].setBlock(0);               
	            onDeckSpace[i][j].setFocusable(false);
	            add(onDeckSpace[i][j]);
	        }
	    }
	     
	}
}


