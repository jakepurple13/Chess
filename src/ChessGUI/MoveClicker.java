package ChessGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Board.Board;
import Piece.*;

public class MoveClicker implements ActionListener {
	
	boolean first = true;
	int row;
	int column;
	int rowto;
	int columnto;
	Board b;
	GUI g;
	King white;
	King black;
	Moved moved;

	
	public MoveClicker(int row, int column, Board b, GUI g, Moved moved) {
		this.row = row;
		this.column = column;
		this.b = b;
		this.g = g;
		white = b.getWhiteKing();
		black = b.getBlackKing();
		this.moved = moved;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean pieceCheck = false;
		boolean boardCheck = false;
		if(g.place) {
			if(g.turn==b.getLocation(row, column).getPiece().getColor()) {
				g.place = !g.place;
				g.row = row;
				g.column = column;
				System.out.println("Got the piece");
				g.from = (JButton) e.getSource();
				g.from.setBorderPainted(true);
			}
		} else {
			if(b.movePiece(g.row, g.column, row, column)) {
				statUpdater();
				g.turn = !g.turn;
			}
			
			g.place = !g.place;
			moved.moved(g.row, g.column, row, column);
			System.out.println("Placed the piece");
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					//pawn promotion
					if(i==0 || i==7) {
						if(b.getLocation(i, j).getPiece().isPromotional()) {
							Stats.promotions++;
							Stats.lblPromotions.setText(Stats.promotions+"");
							Object[] options1 = {"Knight", //0
		                						"Bishop", //1
												"Rook",	  //2
												"Queen"}; //3
							int promotion;
							promotion = JOptionPane.showOptionDialog(g,
								    "Choose your piece",
								    "You got a promotion!",
								    JOptionPane.YES_NO_CANCEL_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								    null,
								    options1,
								    options1[3]);
								if(promotion==0) {
									b.getLocation(i, j).placePiece(new Knight(true,b.getLocation(i, j).getPiece().getColor()));
								} else if(promotion==1) {
									b.getLocation(i, j).placePiece(new Bishop(true,b.getLocation(i, j).getPiece().getColor()));
								} else if(promotion==2) {
									b.getLocation(i, j).placePiece(new Rook(true,b.getLocation(i, j).getPiece().getColor()));
								} else if(promotion==3) {
									b.getLocation(i, j).placePiece(new Queen(true,b.getLocation(i, j).getPiece().getColor()));
								}
						}
					}
					g.boarded[i][j].setText(b.getLocation(i, j).getPiece().getPiece());
					//System.out.println(i + "," + j + " is " + b.getLocation(i, j).getPiece().getClass());
				}
			}
			g.from.setBorderPainted(false);
			g.from = null;
			
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					boardCheck = b.canCheck(i, j);
					if(boardCheck) {
						break;
					}
				}
				if(boardCheck) {
					break;
				}
			}
			
			pieceCheck = b.getLocation(row, column).canCheck();
			System.out.println("Is he in check: " + pieceCheck + " | " + boardCheck);
			
			if(pieceCheck && boardCheck) {
				if(g.turn) {
					g.setTitle("White is in check!");
				} else if(!g.turn) {
					g.setTitle("Black is in check!");
				}
			} else if(g.turn) {
				g.setTitle("White player's turn");
			} else if(!g.turn) {
				g.setTitle("Black player's turn");
			}
			
		}
		
		
		
		white = b.getWhiteKing();
		black = b.getBlackKing();
		
		Object[] options = {"Quit", //0
                			"No",   //1
							"Yes"}; //2
		int choice = -1;
		if(!white.exists()) {
			System.out.println("Black wins!");
			Stats.blackWins++;
			Stats.lblBlackwins.setText(Stats.blackWins+"");
			choice = JOptionPane.showOptionDialog(g,
			    "Would you like to play again?",
			    "Black wins!",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]);
		} else if(!black.exists()) {
			System.out.println("White wins!");
			Stats.whiteWins++;
			Stats.lblWhitewins.setText(Stats.whiteWins+"");
			choice = JOptionPane.showOptionDialog(g,
			    "Would you like to play again?",
			    "White wins!",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]);
		}
		
		if(choice==0) { 	   //quit
			System.exit(-1);
		} else if(choice==1) { //no
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					g.boarded[i][j].setEnabled(false);
				}
			}
			white = new King(true, true);
			black = new King(true, false);
		} else if(choice==2) { //yes
			b.setUp();
			Captured.white = "";
			Captured.black = "";
			Captured.label_1.setText(Captured.white);
			Captured.label_2.setText(Captured.black);
			Stats.totalNumberOfGames++;
			Stats.lblGames.setText(Stats.totalNumberOfGames+"");
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					g.boarded[i][j].setText(b.getLocation(i, j).getPiece().getPiece());
				}
			}
		}
	}
	
	
	public void statUpdater() {
		//all stats are updated
		Stats.moveTotal++;
		Stats.moves.setText(Stats.moveTotal+"");
		Piece p = b.getLocation(row, column).getPiece();
		if(p instanceof Pawn) {
			Stats.pawnMoves++;
			Stats.lblPawn.setText(Stats.pawnMoves+"");
			if(p.getColor()==true) {
				Stats.whitePawns++;
				Stats.lblWhitepawnmoves.setText(Stats.whitePawns+"");
			} else if(p.getColor()==false) {
				Stats.blackPawns++;
				Stats.lblBlackpawnmoves.setText(Stats.blackPawns+"");
			}
		}
		if(p instanceof King) {
			Stats.kings++;
			Stats.lblKingmoves.setText(Stats.kings+"");
			if(p.getColor()==true) {
				Stats.whiteKing++;
				Stats.lblWhitekingmoves.setText(Stats.whiteKing+"");
			} else if(p.getColor()==false) {
				Stats.blackKing++;
				Stats.lblBlackkingmoves.setText(Stats.blackKing+"");
			}
			
			//castling stat update
			if(white.hasCastled() && !Stats.whiteCastle) {
				Stats.whiteCastle = true;
				Stats.castles++;
				Stats.lblCastlemoves.setText(Stats.castles+"");
			}
			if(black.hasCastled() && !Stats.blackCastle) {
				Stats.blackCastle = true;
				Stats.castles++;
				Stats.lblCastlemoves.setText(Stats.castles+"");
			}
			
		}
		if(p instanceof Queen) {
			Stats.queens++;
			Stats.lblQueenmoves.setText(Stats.queens+"");
			if(p.getColor()==true) {
				Stats.whiteQueen++;
				Stats.lblWhitequeenmoves.setText(Stats.whiteQueen+"");
			} else if(p.getColor()==false) {
				Stats.blackQueen++;
				Stats.lblBlackqueenmoves.setText(Stats.blackQueen+"");
			}
		}
		if(p instanceof Bishop) {
			Stats.bishops++;
			Stats.lblBishopmoves.setText(Stats.bishops+"");
			if(p.getColor()==true) {
				Stats.whiteBishop++;
				Stats.lblWhitebishopmoves.setText(Stats.whiteBishop+"");
			} else if(p.getColor()==false) {
				Stats.blackBishop++;
				Stats.lblBlackbishopmoves.setText(Stats.blackBishop+"");
			}
		}
		if(p instanceof Knight) {
			Stats.knights++;
			Stats.lblKnightmoves.setText(Stats.knights+"");
			if(p.getColor()==true) {
				Stats.whiteKnight++;
				Stats.lblWhiteknightmoves.setText(Stats.whiteKnight+"");
			} else if(p.getColor()==false) {
				Stats.blackKnight++;
				Stats.lblBlackknightmoves.setText(Stats.blackKnight+"");
			}
		}
		if(p instanceof Rook) {
			Stats.rooks++;
			Stats.lblRookmoves.setText(Stats.rooks+"");
			if(p.getColor()==true) {
				Stats.whiteRook++;
				Stats.lblWhiterookmoves.setText(Stats.whiteRook+"");
			} else if(p.getColor()==false) {
				Stats.blackRook++;
				Stats.lblBlackrookmoves.setText(Stats.blackRook+"");
			}
		}
				
		Captured.update(b.getWhiteCaptured(), b.getBlackCaptured());	

	}

	public interface Moved {
		void moved(int rowfrom, int columnfrom, int rowto, int columnto);
	}

}
