
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GameGUI extends JFrame implements KeyListener {
    
    public GameGUI(){
        initComponents();
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI().setVisible(true);
            }
        });
    }
    
    //----------------Methods------------------//
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        //Not used
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        //Works for arrow keys and letter keys
        System.out.println("I heard your key pressed");
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        //Not used
    }

    
    private void initComponents() {
        //Set Frame
        setSize(375, 770);
        setTitle("Notetris");
        //Set topPanel
        topPanel = new JPanel();
        topPanel.setSize(375, 50);
        add(topPanel);
        
        //Set startButton for top panel
        startButton = new JButton();
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                board.start();
            }
        });
        startButton.setFocusable(false);    //Don't distract from keyListener
        topPanel.add(startButton);
        
        //Set GameBoard - Extends JPanel
        board = new GameBoard();
        board.addKeyListener(this);
        board.setFocusable(true);
        add(board);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    //------------Variables-------------//
    
    private JPanel topPanel;
    private GameBoard board;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitMitem;
    private JMenuItem aboutMitem;
    private JButton startButton;
}
