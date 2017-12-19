package ChessGUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Stats extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stats frame = new Stats();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean whiteCastle = false;
	public static boolean blackCastle = false;
	
	public static int[] stat;
	public static int moveTotal;
	public static int pawnMoves;
	public static int whitePawns;
	public static int blackPawns;
	public static int kings;
	public static int whiteKing;
	public static int blackKing;
	public static int queens;
	public static int whiteQueen;
	public static int blackQueen;
	public static int bishops;
	public static int whiteBishop;
	public static int blackBishop;
	public static int knights;
	public static int whiteKnight;
	public static int blackKnight;
	public static int rooks;
	public static int whiteRook;
	public static int blackRook;
	public static int castles;
	public static int piecesTaken;
	public static int whiteWins;
	public static int blackWins;
	public static int totalNumberOfGames;
	public static int promotions;
	public static JLabel moves;
	public static JLabel lblPawn;
	public static JLabel lblWhitepawnmoves;
	public static JLabel lblBlackpawnmoves;
	public static JLabel lblKingmoves;
	public static JLabel lblWhitekingmoves;
	public static JLabel lblBlackkingmoves;
	public static JLabel lblQueenmoves;
	public static JLabel lblWhitequeenmoves;
	public static JLabel lblBlackqueenmoves;
	public static JLabel lblBishopmoves;
	public static JLabel lblWhitebishopmoves;
	public static JLabel lblBlackbishopmoves;
	public static JLabel lblKnightmoves;
	public static JLabel lblWhiteknightmoves;
	public static JLabel lblBlackknightmoves;
	public static JLabel lblRookmoves;
	public static JLabel lblWhiterookmoves;
	public static JLabel lblBlackrookmoves;
	public static JLabel lblCastlemoves;
	public static JLabel lblPiecestaken;
	public static JLabel lblWhitewins;
	public static JLabel lblBlackwins;
	public static JLabel lblGames;
	public static JLabel lblPromotions;
	private JLabel lblNumberOfKing;
	private JLabel lblNumberOfWhite;
	private JLabel lblNumberOfBlack;
	private JLabel lblNumberOfQueen;
	private JLabel lblNumberOfBishop;
	private JLabel lblNumberOfKnight;
	private JLabel lblNumberOfRook;
	private JLabel lblNumberOfCastles;
	private JLabel lblNumberOfWhite_1;
	private JLabel lblNumberOfBlack_1;
	private JLabel lblNumberOfGames;
	private JLabel lblNumberOfPieces;
	private JLabel lblNumberOfWhite_2;
	private JLabel lblNumberOfWhite_3;
	private JLabel lblNumberOfBlack_2;
	private JLabel lblNumberOfBlack_3;
	private JLabel lblNumberOfWhite_4;
	private JLabel lblNumberOfBlack_4;
	private JLabel lblNumberOfWhite_5;
	private JLabel lblNumberOfBlack_5;
	private JLabel lblNumberOfWhite_6;
	private JLabel lblNumberOfBlack_6;
	private JLabel lblNumberOfPromotions;
	
	/**
	 * Create the frame.
	 */
	public Stats() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		setResizable(false);
		
		stat = new int[25];
		try {
			getText();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			 moveTotal = 0;
			 pawnMoves = 0;
			 whitePawns = 0;
			 blackPawns = 0;
			 kings = 0;
			 whiteKing = 0;
			 blackKing = 0;
			 queens = 0;
			 whiteQueen = 0;
			 blackQueen = 0;
			 bishops = 0;
			 whiteBishop = 0;
			 blackBishop = 0;
			 knights = 0;
			 whiteKnight = 0;
			 blackKnight = 0;
			 rooks = 0;
			 whiteRook = 0;
			 blackRook = 0;
			 castles = 0;
			 piecesTaken = 0;
			 whiteWins = 0;
			 blackWins = 0;
			 totalNumberOfGames = 0;
			 promotions = 0;
			 System.out.println("Something went wrong looking for the file.");
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//adding 1 to the stats
		totalNumberOfGames++;
		
		JLabel lblNumberOfTotal = new JLabel("Number of Total Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfTotal, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfTotal, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfTotal);
		
		moves = new JLabel(moveTotal + "");
		sl_contentPane.putConstraint(SpringLayout.WEST, moves, 6, SpringLayout.EAST, lblNumberOfTotal);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, moves, 0, SpringLayout.SOUTH, lblNumberOfTotal);
		contentPane.add(moves);
		
		JLabel lblNumberOfPawn = new JLabel("Number of Pawn Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfPawn, 6, SpringLayout.SOUTH, lblNumberOfTotal);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfPawn, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfPawn);
		
		lblPawn = new JLabel(pawnMoves + "");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPawn, 6, SpringLayout.SOUTH, moves);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPawn, 0, SpringLayout.WEST, moves);
		contentPane.add(lblPawn);
		
		lblNumberOfKing = new JLabel("Number of King Moves:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfKing, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfKing);
		
		lblNumberOfWhite = new JLabel("Number of White Pawns:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfWhite, 6, SpringLayout.SOUTH, lblNumberOfPawn);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite);
		
		lblNumberOfBlack = new JLabel("Number of Black Pawns:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfKing, 6, SpringLayout.SOUTH, lblNumberOfBlack);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack, 6, SpringLayout.SOUTH, lblNumberOfWhite);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfBlack);
		
		lblNumberOfQueen = new JLabel("Number of Queen Moves:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfQueen, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfQueen);
		
		lblNumberOfBishop = new JLabel("Number of Bishop Moves:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBishop, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBishop);
		
		lblNumberOfKnight = new JLabel("Number of Knight Moves:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfKnight, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfKnight);
		
		lblNumberOfRook = new JLabel("Number of Rook Moves:");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumberOfRook, 0, SpringLayout.EAST, lblNumberOfTotal);
		contentPane.add(lblNumberOfRook);
		
		lblNumberOfCastles = new JLabel("Number of Castles:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfCastles, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfCastles);
		
		lblNumberOfWhite_1 = new JLabel("Number of White Wins:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_1, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_1);
		
		lblNumberOfBlack_1 = new JLabel("Number of Black Wins:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfWhite_1, -6, SpringLayout.NORTH, lblNumberOfBlack_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_1, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBlack_1);
		
		lblNumberOfGames = new JLabel("Number of Games:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfBlack_1, -6, SpringLayout.NORTH, lblNumberOfGames);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfGames, 0, SpringLayout.WEST, lblNumberOfTotal);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfGames, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblNumberOfGames);
		
		lblNumberOfPieces = new JLabel("Number of Pieces Taken:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfPieces, 467, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfCastles, -6, SpringLayout.NORTH, lblNumberOfPieces);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfPieces, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfPieces);
		
		lblNumberOfWhite_2 = new JLabel("Number of White Queen Moves:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfQueen, -6, SpringLayout.NORTH, lblNumberOfWhite_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_2, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_2);
		
		lblNumberOfWhite_3 = new JLabel("Number of White King Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfWhite_3, 6, SpringLayout.SOUTH, lblNumberOfKing);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_3, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_3);
		
		lblNumberOfBlack_2 = new JLabel("Number of Black King Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack_2, 6, SpringLayout.SOUTH, lblNumberOfWhite_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_2, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfBlack_2);
		
		lblNumberOfBlack_3 = new JLabel("Number of Black Queen Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBishop, 6, SpringLayout.SOUTH, lblNumberOfBlack_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack_3, 208, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfWhite_2, -6, SpringLayout.NORTH, lblNumberOfBlack_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_3, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBlack_3);
		
		lblNumberOfWhite_4 = new JLabel("Number of White Bishop Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfWhite_4, 6, SpringLayout.SOUTH, lblNumberOfBishop);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_4, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_4);
		
		lblNumberOfBlack_4 = new JLabel("Number of Black Bishop Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfKnight, 6, SpringLayout.SOUTH, lblNumberOfBlack_4);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack_4, 6, SpringLayout.SOUTH, lblNumberOfWhite_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_4, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBlack_4);
		
		lblNumberOfWhite_5 = new JLabel("Number of White Knight Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfWhite_5, 6, SpringLayout.SOUTH, lblNumberOfKnight);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_5, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_5);
		
		lblNumberOfBlack_5 = new JLabel("Number of Black Knight Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfRook, 3, SpringLayout.SOUTH, lblNumberOfBlack_5);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack_5, 6, SpringLayout.SOUTH, lblNumberOfWhite_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_5, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBlack_5);
		
		lblNumberOfWhite_6 = new JLabel("Number of White Rook Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfWhite_6, 6, SpringLayout.SOUTH, lblNumberOfRook);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfWhite_6, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfWhite_6);
		
		lblNumberOfBlack_6 = new JLabel("Number of Black Rook Moves:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfBlack_6, 6, SpringLayout.SOUTH, lblNumberOfWhite_6);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfBlack_6, 0, SpringLayout.WEST, lblNumberOfTotal);
		contentPane.add(lblNumberOfBlack_6);
		
		lblWhitepawnmoves = new JLabel(whitePawns + "");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWhitepawnmoves, 6, SpringLayout.SOUTH, lblPawn);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhitepawnmoves, 0, SpringLayout.WEST, moves);
		contentPane.add(lblWhitepawnmoves);
		
		lblBlackpawnmoves = new JLabel(blackPawns + "");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackpawnmoves, 6, SpringLayout.SOUTH, lblNumberOfWhite);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackpawnmoves, 0, SpringLayout.WEST, moves);
		contentPane.add(lblBlackpawnmoves);
		
		lblKingmoves = new JLabel(kings+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblKingmoves, 0, SpringLayout.WEST, moves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblKingmoves, 0, SpringLayout.SOUTH, lblNumberOfKing);
		contentPane.add(lblKingmoves);
		
		lblWhitekingmoves = new JLabel(whiteKing+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhitekingmoves, 6, SpringLayout.EAST, lblNumberOfWhite_3);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhitekingmoves, 0, SpringLayout.SOUTH, lblNumberOfWhite_3);
		contentPane.add(lblWhitekingmoves);
		
		lblBlackkingmoves = new JLabel(blackKing+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackkingmoves, 6, SpringLayout.EAST, lblNumberOfBlack_2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBlackkingmoves, 0, SpringLayout.SOUTH, lblNumberOfBlack_2);
		contentPane.add(lblBlackkingmoves);
		
		lblQueenmoves = new JLabel(queens+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblQueenmoves, 0, SpringLayout.NORTH, lblNumberOfQueen);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblQueenmoves, -10, SpringLayout.EAST, lblBlackkingmoves);
		contentPane.add(lblQueenmoves);
		
		lblWhitequeenmoves = new JLabel(whiteQueen+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhitequeenmoves, 6, SpringLayout.EAST, lblNumberOfWhite_2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhitequeenmoves, 0, SpringLayout.SOUTH, lblNumberOfWhite_2);
		contentPane.add(lblWhitequeenmoves);
		
		lblBlackqueenmoves = new JLabel(blackQueen+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackqueenmoves, 6, SpringLayout.SOUTH, lblNumberOfWhite_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackqueenmoves, 6, SpringLayout.EAST, lblNumberOfBlack_3);
		contentPane.add(lblBlackqueenmoves);
		
		lblBishopmoves = new JLabel(bishops+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBishopmoves, 0, SpringLayout.NORTH, lblNumberOfBishop);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBishopmoves, 0, SpringLayout.WEST, lblWhitekingmoves);
		contentPane.add(lblBishopmoves);
		
		lblWhitebishopmoves = new JLabel(whiteBishop+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhitebishopmoves, 6, SpringLayout.EAST, lblNumberOfWhite_4);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhitebishopmoves, 0, SpringLayout.SOUTH, lblNumberOfWhite_4);
		contentPane.add(lblWhitebishopmoves);
		
		lblBlackbishopmoves = new JLabel(blackBishop+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackbishopmoves, 6, SpringLayout.EAST, lblNumberOfBlack_4);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBlackbishopmoves, 0, SpringLayout.SOUTH, lblNumberOfBlack_4);
		contentPane.add(lblBlackbishopmoves);
		
		lblKnightmoves = new JLabel(knights+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblKnightmoves, 0, SpringLayout.WEST, lblWhitekingmoves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblKnightmoves, 0, SpringLayout.SOUTH, lblNumberOfKnight);
		contentPane.add(lblKnightmoves);
		
		lblWhiteknightmoves = new JLabel(whiteKnight+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWhiteknightmoves, 0, SpringLayout.NORTH, lblNumberOfWhite_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhiteknightmoves, 6, SpringLayout.EAST, lblNumberOfWhite_5);
		contentPane.add(lblWhiteknightmoves);
		
		lblBlackknightmoves = new JLabel(blackKnight+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackknightmoves, 0, SpringLayout.NORTH, lblNumberOfBlack_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackknightmoves, 6, SpringLayout.EAST, lblNumberOfBlack_5);
		contentPane.add(lblBlackknightmoves);
		
		lblRookmoves = new JLabel(rooks+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRookmoves, 0, SpringLayout.WEST, lblWhitekingmoves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRookmoves, 0, SpringLayout.SOUTH, lblNumberOfRook);
		contentPane.add(lblRookmoves);
		
		lblWhiterookmoves = new JLabel(whiteRook+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhiterookmoves, 6, SpringLayout.EAST, lblNumberOfWhite_6);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhiterookmoves, 0, SpringLayout.SOUTH, lblNumberOfWhite_6);
		contentPane.add(lblWhiterookmoves);
		
		lblBlackrookmoves = new JLabel(blackRook+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackrookmoves, 0, SpringLayout.NORTH, lblNumberOfBlack_6);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackrookmoves, 6, SpringLayout.EAST, lblNumberOfBlack_6);
		contentPane.add(lblBlackrookmoves);
		
		lblCastlemoves = new JLabel(castles+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCastlemoves, 0, SpringLayout.WEST, lblWhitekingmoves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblCastlemoves, 0, SpringLayout.SOUTH, lblNumberOfCastles);
		contentPane.add(lblCastlemoves);
		
		lblPiecestaken = new JLabel(piecesTaken+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPiecestaken, 0, SpringLayout.WEST, lblWhitekingmoves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPiecestaken, 0, SpringLayout.SOUTH, lblNumberOfPieces);
		contentPane.add(lblPiecestaken);
		
		lblWhitewins = new JLabel(whiteWins+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWhitewins, 6, SpringLayout.SOUTH, lblPiecestaken);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhitewins, 35, SpringLayout.EAST, lblNumberOfWhite_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhitewins, 22, SpringLayout.SOUTH, lblPiecestaken);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWhitewins, 126, SpringLayout.EAST, lblNumberOfWhite_1);
		contentPane.add(lblWhitewins);
		
		lblBlackwins = new JLabel(blackWins+"");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackwins, 0, SpringLayout.NORTH, lblNumberOfBlack_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackwins, 0, SpringLayout.WEST, lblWhitekingmoves);
		contentPane.add(lblBlackwins);
		
		lblGames = new JLabel(totalNumberOfGames+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblGames, 0, SpringLayout.WEST, lblWhitekingmoves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblGames, 0, SpringLayout.SOUTH, lblNumberOfGames);
		contentPane.add(lblGames);
		
		lblNumberOfPromotions = new JLabel("Number of Promotions:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfPromotions, 0, SpringLayout.WEST, lblNumberOfTotal);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNumberOfPromotions, -6, SpringLayout.NORTH, lblNumberOfCastles);
		contentPane.add(lblNumberOfPromotions);
		
		lblPromotions = new JLabel(promotions+"");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPromotions, 0, SpringLayout.WEST, moves);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPromotions, -6, SpringLayout.NORTH, lblCastlemoves);
		contentPane.add(lblPromotions);
		
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
					saveToFile();
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
		
		//sets the jpanel to the top right of the screen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        int y = 10;
        setLocation(x, y);
        
        setUndecorated(true);
		
		
	}
	
	public static void getText() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/chessStats.txt"))) {
		    String line;
		    int i=0;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	stat[i] = Integer.valueOf(line.trim() + "");
		    	i++;
		    }
		    
		    moveTotal = stat[0];
			pawnMoves = stat[1];
			whitePawns = stat[2];
			blackPawns = stat[3];
			kings = stat[4];
			whiteKing = stat[5];
			blackKing = stat[6];
			queens = stat[7];
			whiteQueen = stat[8];
			blackQueen = stat[9];
			bishops = stat[10];
			whiteBishop = stat[11];
			blackBishop = stat[12];
			knights = stat[13];
			whiteKnight = stat[14];
			blackKnight = stat[15];
			rooks = stat[16];
			whiteRook = stat[17];
			blackRook = stat[18];
			castles = stat[19];
			piecesTaken = stat[20];
			whiteWins = stat[21];
			blackWins = stat[22];
			totalNumberOfGames = stat[23];
			promotions = stat[24];
		    
		}
		
		
	}
	
	public static void saveToFile() throws IOException {
		
		stat[0] = moveTotal;
		stat[1] = pawnMoves;
		stat[2] = whitePawns;
		stat[3] = blackPawns;
		stat[4] = kings;
		stat[5] = whiteKing;
		stat[6] = blackKing;
		stat[7] = queens;
		stat[8] = whiteQueen;
		stat[9] = blackQueen;
		stat[10] = bishops;
		stat[11] = whiteBishop;
		stat[12] = blackBishop;
		stat[13] = knights;
		stat[14] = whiteKnight;
		stat[15] = blackKnight;
		stat[16] = rooks;
		stat[17] = whiteRook;
		stat[18] = blackRook;
		stat[19] = castles;
		stat[20] = piecesTaken;
		stat[21] = whiteWins;
		stat[22] = blackWins;
		stat[23] = totalNumberOfGames;
		stat[24] = promotions;
		
		File fout = new File(System.getProperty("user.home") + "/chessStats.txt");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (int i = 0; i < 25; i++) {
			bw.write(stat[i] + "\n");
		}
	 
		bw.close();
	}
}
