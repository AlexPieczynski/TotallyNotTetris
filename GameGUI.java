
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
        setSize(450, 770);
        setTitle("Notetris");
        
        //Set container
        container = new JPanel();
        container.setLayout(new BorderLayout());
        
        //Set topPanel
        topPanel = new JPanel();
        container.add(topPanel, BorderLayout.PAGE_START);
        
        //Set startButton for top panel
        startButton = new JButton();
        startButton.setText("Start!");
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                board.start();
            }
        });
        startButton.setFocusable(false);    //Don't distract from keyListener
        topPanel.add(startButton);
        
        //Set GameBoard - Extends JPanel
        //Gameboard class constructor takes care of initializing sizes
        board = new GameBoard();
        board.addKeyListener(this);
        board.setFocusable(true);
        container.add(board, BorderLayout.CENTER);
        
        //Set control panel for control buttons
        controlPanel = new JPanel();
        //Set control buttons and add listeners
        controlPanel.setLayout(new FlowLayout());
        
        //Set move left button for control panel
        moveLBtn = new JButton();
        moveLBtn.setText("Left");
        moveLBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //THE METHOD WE WANT
            }
        });
        moveLBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(moveLBtn);
        
        //Set move right for control panel
        moveRBtn = new JButton();
        moveRBtn.setText("Right");
        moveRBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //THE METHOD WE WANT
            }
        });
        moveRBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(moveRBtn);
        
        //Set rotate left for control panel
        rotateLBtn = new JButton();
        rotateLBtn.setText("Rotate L");
        rotateLBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //THE METHOD WE WANT
            }
        });
        rotateLBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(rotateLBtn);
        
        //Set rotate right for control panel
        rotateRBtn = new JButton();
        rotateRBtn.setText("Rotate R");
        rotateRBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //THE METHOD WE WANT
            }
        });
        rotateRBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(rotateRBtn);
        
        //Set drop button for control panel
        dropBtn = new JButton();
        dropBtn.setText("Drop");
        dropBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //THE METHOD WE WANT
            }
        });
        dropBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(dropBtn);
        
        container.add(controlPanel, BorderLayout.PAGE_END);
        
        add(container);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //------------Variables-------------//
    
    private JPanel container;
    private JPanel topPanel;
    private JPanel controlPanel;
    private GameBoard board;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitMitem;
    private JMenuItem aboutMitem;
    private JButton startButton;
    private JButton moveLBtn;
    private JButton moveRBtn;
    private JButton rotateLBtn;
    private JButton rotateRBtn;
    private JButton dropBtn;
}
