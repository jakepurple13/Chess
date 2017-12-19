package ChessGUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Board.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					s = new Stats();
					s.setVisible(true);
					c = new Captured();
					c.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int row = 0;
	int column = 0;
	JButton[][] boarded = new JButton[8][8];
	Board b = new Board();
	boolean place = true;
	boolean turn = false;
	int fontSize = 12;
	JButton from;
	static Stats s;
	static Captured c;
	Color whiteSide = Color.WHITE;
	Color blackSide = Color.GRAY;

    Client client;
    Server serv;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8, 1, 1));
		setTitle("White player's turn");
        setDefaultLookAndFeelDecorated(true);

        String game = "/game.png";

        /*try {
            File f = new File(game);
            System.out.println(f.getCanonicalPath());
            setIconImage(ImageIO.read(f));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        //setting the menus-----------------------------------------
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.setUp();
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						boarded[i][j].setText(b.getLocation(i, j).getPiece().getPiece());
					}
				}	
			}
		});
		newGame.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, 
		       KeyEvent.CTRL_MASK));
		
		JMenuItem newGameAsWhite = new JMenuItem("New Game as White");
		newGameAsWhite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.setUpAsWhite();
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						boarded[i][j].setText(b.getLocation(i, j).getPiece().getPiece());
					}
				}	
			}
		});
		
		JMenuItem newGameAsBlack = new JMenuItem("New Game as Black");
		newGameAsBlack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.setUp();
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						boarded[i][j].setText(b.getLocation(i, j).getPiece().getPiece());
					}
				}	
			}
		});
		
		JCheckBoxMenuItem showHideStats = new JCheckBoxMenuItem("Show/Hide Stats");
		showHideStats.setMnemonic(KeyEvent.VK_S);
		showHideStats.setSelected(true);
		showHideStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showHideStats.isSelected()) {
					s.setOpacity(1);
				} else if(!showHideStats.isSelected()) {
					s.setOpacity(0);
				}
			}
		});
		showHideStats.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, 
		       KeyEvent.CTRL_MASK));
		
		JCheckBoxMenuItem showHideCaptured = new JCheckBoxMenuItem("Show/Hide Captured");
		showHideCaptured.setMnemonic(KeyEvent.VK_C);
		showHideCaptured.setSelected(true);
		showHideCaptured.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showHideCaptured.isSelected()) {
					c.setOpacity(1);
				} else if(!showHideCaptured.isSelected()) {
					c.setOpacity(0);
				}
			}
		});
		
		showHideCaptured.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, 
		       KeyEvent.CTRL_MASK));
		
		JMenuItem colorChooserWhite = new JMenuItem("Choose White cell color");
		colorChooserWhite.setSelected(true);
		colorChooserWhite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color whiteColor = JColorChooser.showDialog(
		                GUI.this,
		                "Choose cell color for White",
		                whiteSide);
				
				whiteSide = whiteColor;
				
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)) {
							boarded[i][j].setBackground(blackSide);
						} else {
							boarded[i][j].setBackground(whiteColor);
						}
					}
				}
			}
		});
		
		JMenuItem colorChooserBlack = new JMenuItem("Choose Black cell color");
		colorChooserBlack.setSelected(true);
		colorChooserBlack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color blackColor = JColorChooser.showDialog(
		                GUI.this,
		                "Choose cell color for Black",
		                blackSide);
				
				blackSide = blackColor;
				
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)) {
							boarded[i][j].setBackground(blackSide);
						} else {
							boarded[i][j].setBackground(whiteSide);
						}
					}
				}
			}
		});
		
		
		JCheckBoxMenuItem startServer = new JCheckBoxMenuItem("Start Server");
		startServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //if(serv==null) {
                    try {
                        /*serv = new Server(new Server.ChessMove() {
                            @Override
                            public void move(int rowfrom, int columnfrom, int rowto, int columnto) {
                                b.movePiece(rowfrom, columnfrom, rowto, columnto);
                            }
                        });*/

                        //serv = new Server();
                        Server.startServer();

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                //}
			}
		});
		
		JCheckBoxMenuItem startClient = new JCheckBoxMenuItem("Start Client");
		startClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                if(client==null) {
                    client = new Client(0, new Client.Move() {
                        @Override
                        public void move(int rowfrom, int columnfrom, int rowto, int columnto) {
                            b.movePiece(rowfrom, columnfrom, rowto, columnto);
                        }
                    }, GUI.this);
                    Thread t = new Thread(client);
                    t.start();
                }
			}
		});
		
		
		options.add(newGame);
		options.add(newGameAsWhite);
		options.add(newGameAsBlack);
		options.addSeparator();
		options.add(colorChooserWhite);
		options.add(colorChooserBlack);
		options.addSeparator();
		options.add(showHideStats);
		options.add(showHideCaptured);
		options.addSeparator();
		options.add(startServer);
		options.add(startClient);
		menuBar.add(options);
		setJMenuBar(menuBar);
		//end of menu setting-----------------------------------------
		
		
		contentPane.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						boarded[i][j].setFont(boarded[i][j].getFont().deriveFont((float) boarded[i][j].getHeight()));
					}
				}	 
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		//creates the buttons
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				boarded[i][j] = new JButton(b.getLocation(i, j).getPiece().getPiece());
				boarded[i][j].addActionListener(new MoveClicker(i, j, b, this, new MoveClicker.Moved() {
                    @Override
                    public void moved(int rowfrom, int columnfrom, int rowto, int columnto) {
                        if(client!=null && place) {
                            client.makeMove(rowfrom, columnfrom, rowto, columnto);
                        }
                    }
                }));
				if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)) {
					boarded[i][j].setBackground(blackSide);
				} else {
					boarded[i][j].setBackground(whiteSide);
				}
				boarded[i][j].setFont(boarded[i][j].getFont().deriveFont((float) boarded[i][j].getHeight()-10));
				boarded[i][j].setOpaque(true);
				boarded[i][j].setBorderPainted(false);
				contentPane.add(boarded[i][j]);
			}
		}
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					Stats.saveToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		pack();
		
	}
	
	 
}
