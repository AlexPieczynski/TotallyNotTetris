//This class uses the Singleton design pattern. It contains the GUI that displays the 2D Board.
//Also contains all of the ActionListeners used to read a player's keyboard commands.
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Z;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameGUI extends JFrame implements KeyListener {
    
    private static GameGUI INSTANCE = null;    //Create instance holder
    
    private GameGUI(){
        // Construct the instance
        initComponents();
    }
    
    
    public static void main(String args[]) {
        // Call to create the one and only instance of of the GUI 
        getInstance();
    }
    
    //----------------Methods------------------//
    
    public OnDeckGrid getOnDeck(){
        return this.onDeck;
    }
    
    public static GameGUI getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameGUI();
        }
        return INSTANCE;
    }
    
    public GameBoard getBoard(){
      return board;
    }
    
    public void start(){
        // start GameState's timer
        game.startTimer();        
    }
           
            
    @Override
    public void keyTyped(KeyEvent e) {
        //Not used
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        //Works for arrow keys and letter keys
        
        int key = e.getKeyCode();
        
        switch(key){
            case VK_LEFT://to move left
                game.moveLeft();
                break;
            case VK_RIGHT:
                game.moveRight();
                break;
            case VK_Z:
                game.rotateLeft();
                break;    
            case VK_X:
                game.rotateRight();
                break;    
            case VK_SPACE:
                game.blockTimeHandler.actionPerformed(null);
                break;   
            default:    //Nevermind this
                break;
        }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        //Not used
    }

    public void setLevelLabel(String s){
        levelLabel.setText(s);
    }
    
    public void setLinesClearedLabel(String s){
        linesClearedLabel.setText(s);
    }
    
    public void setScoreLabel(String s){
        scoreLabel.setText(s);
    }
    
    public void setTimeLabel(String s){
        timeLabel.setText(s);
    }
    
    private void initComponents() {
        //Set Frame
        setSize(450, 770);
        setTitle("Notetris");
        
        //Menu item setup
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        
        exitMitem = new JMenuItem("Exit");
        exitMitem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        aboutMitem = new JMenuItem("About");
        aboutMitem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, about,"About",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        helpMitem = new JMenuItem("Help");
        helpMitem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, help,"Help",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        fileMenu.add(exitMitem);
        helpMenu.add(aboutMitem);
        helpMenu.add(helpMitem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
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
                start();
            }
        });
        startButton.setFocusable(false);    //Don't distract from keyListener
        topPanel.add(startButton);
        
        //Add score label
        textLabel = new JLabel("Score:");
        topPanel.add(textLabel);
        scoreLabel = new JLabel("0");
        topPanel.add(scoreLabel);
        
        //Add time label
        textLabel2 = new JLabel("Time:");
        topPanel.add(textLabel2);
        timeLabel = new JLabel("0");
        topPanel.add(timeLabel);
        
        textLabel3 = new JLabel("Level:");
        levelLabel = new JLabel(" ");
        topPanel.add(textLabel3);
        topPanel.add(levelLabel);
        
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
                game.moveLeft();
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
                game.moveRight();
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
                game.rotateLeft();
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
                game.rotateRight();
            }
        });
        rotateRBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(rotateRBtn);
        
        // init GameState object
        game = new GameState();
        
        //Set drop button for control panel
        dropBtn = new JButton();
        dropBtn.setText("Drop");
        dropBtn.addActionListener(game.blockTimeHandler);
        dropBtn.setFocusable(false);    //Don't distract from keyListener
        controlPanel.add(dropBtn);
        //Add control panel
        container.add(controlPanel, BorderLayout.PAGE_END);
        
        //Make filler space
        Dimension d1 = new Dimension(200, 300);
        Dimension d2 = new Dimension(10, 600);
        LFillPanel = new JPanel();
        LFillPanel.setPreferredSize(d2);
        container.add(LFillPanel, BorderLayout.WEST);
        RFillPanel = new JPanel();
        //RFillPanel.setLayout(new BoxLayout(RFillPanel, BoxLayout.Y_AXIS));
        RFillPanel.setPreferredSize(d1);
        
        //Set lines cleared lables
        
        textLabel4 = new JLabel("Lines Cleared:");
        linesClearedLabel = new JLabel("0");
        RFillPanel.add(textLabel4);
        RFillPanel.add(linesClearedLabel);
        
        //Add on deck grid to side panel
        onDeck = new OnDeckGrid();
        RFillPanel.add(onDeck);
        
      
        
        container.add(RFillPanel, BorderLayout.EAST);
        
        add(container);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    
    //------------Variables-------------//
    
    private JPanel container;
    private JPanel topPanel;
    private JPanel controlPanel;
    private JPanel LFillPanel;
    private JPanel RFillPanel;
    private GameBoard board;
    private OnDeckGrid onDeck;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitMitem;
    private JMenuItem aboutMitem;
    private JMenuItem helpMitem;
    private JButton startButton;
    private JLabel textLabel;
    private JLabel scoreLabel;
    private JLabel textLabel2;
    private JLabel timeLabel;
    private JLabel textLabel3;
    private JLabel levelLabel;
    private JLabel textLabel4;
    private JLabel linesClearedLabel;
    private JButton moveLBtn;
    private JButton moveRBtn;
    private JButton rotateLBtn;
    private JButton rotateRBtn;
    private JButton dropBtn;
    private GameState game;
    private final String help = (""
            + "Press the start button to get the game started!"
            + "\nUse arrow keys to move pieces left or right. Z and X will rotate,and"
            + "\nspacebar will 'drop' the current piece. Otherwise, click the buttons to"
            + "\ndo your bidding...");
    private final String about = (""
            + "Welcome to NoTetris, a deceptivley familiar gaming experience"
            + "\nbrought to you by Ben Carver, Alex Pieczynski and Al Hernandez"
            + "\nfor CS 342 project 5.");
}
