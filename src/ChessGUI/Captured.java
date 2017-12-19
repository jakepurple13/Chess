package ChessGUI;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import Piece.Piece;

@SuppressWarnings("serial")
public class Captured extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Captured frame = new Captured();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	static String white = "";
	static String black = "";
	static JLabel label_1;
	static JLabel label_2;
	static int fullSize = 0;
	
	public Captured() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblWhiteCaptured = new JLabel("White Captured:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWhiteCaptured, 10, SpringLayout.NORTH, contentPane);
		contentPane.add(lblWhiteCaptured);
		
		label_1 = new JLabel(white);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_1, 6, SpringLayout.NORTH, lblWhiteCaptured);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_1, 6, SpringLayout.EAST, lblWhiteCaptured);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label_1, 74, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, label_1, 428, SpringLayout.WEST, contentPane);
		contentPane.add(label_1);
		
		JLabel lblBlackCaptured = new JLabel("Black Captured:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBlackCaptured, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblBlackCaptured, -499, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhiteCaptured, 0, SpringLayout.WEST, lblBlackCaptured);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWhiteCaptured, 0, SpringLayout.NORTH, lblBlackCaptured);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWhiteCaptured, 0, SpringLayout.EAST, lblBlackCaptured);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBlackCaptured, 81, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBlackCaptured, 157, SpringLayout.NORTH, contentPane);
		contentPane.add(lblBlackCaptured);
		
		label_2 = new JLabel(black);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_2, 6, SpringLayout.EAST, lblBlackCaptured);
		sl_contentPane.putConstraint(SpringLayout.EAST, label_2, 428, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_2, 81, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label_2, 157, SpringLayout.NORTH, contentPane);
		contentPane.add(label_2);
		
		label_1.setFont(label_1.getFont().deriveFont((float) 20));
		label_2.setFont(label_2.getFont().deriveFont((float) 20));
		
		//sets the jpanel to the top right of the screen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = 10;
        int y = (int) rect.getMaxX() - getWidth() - 100;
        setLocation(x, y);
        
        setUndecorated(true);
		
	}
	
	
	public static void update(ArrayList<Piece> whitePiece, ArrayList<Piece> blackPiece) {
		white="";
		black="";
		
		for(int i=0;i<whitePiece.size();i++) {
			white+=whitePiece.get(i).getPiece();
		}
		for(int i=0;i<blackPiece.size();i++) {
			black+=blackPiece.get(i).getPiece();
		}
		
		label_1.setText(white);
		label_2.setText(black);
		//increases the Pieces Taken stat
		int size = whitePiece.size() + blackPiece.size();
		if(fullSize!=size) {
			Stats.piecesTaken++;
			Stats.lblPiecestaken.setText(Stats.piecesTaken+"");
			fullSize = size;
		}
		
	}
	

}
