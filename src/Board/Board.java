package Board;

import java.util.ArrayList;
import java.util.TreeMap;

import Piece.Bishop;
import Piece.King;
import Piece.Knight;
import Piece.Pawn;
import Piece.Piece;
import Piece.Queen;
import Piece.Rook;

public class Board {
	
	Cell[][] cellBoard;
	ArrayList<Piece> whiteCaptured;
	ArrayList<Piece> blackCaptured;
	
	public Board() {
		//TODO: check to see if theres a reverse method for 2d arrays
		//that may be able to flip the board
		cellBoard = new Cell[8][8];
		whiteCaptured = new ArrayList<Piece>();
		blackCaptured = new ArrayList<Piece>();
		setUp();
		
	}
	
	public void setUp() {
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				cellBoard[i][j] = new Cell(i,j,this);
				cellBoard[i][j].placePiece(new Piece(true));
			}
		}
		
		//Setting pawns
		for(int q=0;q<8;q++) {
			cellBoard[1][q].placePiece(new Pawn(true,true));
			cellBoard[6][q].placePiece(new Pawn(true,false));
		}
		
		//Setting White side
		cellBoard[0][0].placePiece(new Rook(true,true));
		cellBoard[0][1].placePiece(new Knight(true,true));
		cellBoard[0][2].placePiece(new Bishop(true,true));
		cellBoard[0][3].placePiece(new Queen(true,true));
		cellBoard[0][4].placePiece(new King(true,true));
		cellBoard[0][5].placePiece(new Bishop(true,true));
		cellBoard[0][6].placePiece(new Knight(true,true));
		cellBoard[0][7].placePiece(new Rook(true,true));
		
		//Setting Black side
		cellBoard[7][0].placePiece(new Rook(true,false));
		cellBoard[7][1].placePiece(new Knight(true,false));
		cellBoard[7][2].placePiece(new Bishop(true,false));
		cellBoard[7][3].placePiece(new Queen(true,false));
		cellBoard[7][4].placePiece(new King(true,false));
		cellBoard[7][5].placePiece(new Bishop(true,false));
		cellBoard[7][6].placePiece(new Knight(true,false));
		cellBoard[7][7].placePiece(new Rook(true,false));
		
		whiteCaptured = new ArrayList<Piece>();
		blackCaptured = new ArrayList<Piece>();
	}
	
	public void setUpAsWhite() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				cellBoard[i][j] = new Cell(i,j,this);
				cellBoard[i][j].placePiece(new Piece(true));
			}
		}
		
		//Setting pawns
		for(int q=0;q<8;q++) {
			cellBoard[1][q].placePiece(new Pawn(true,false));
			cellBoard[6][q].placePiece(new Pawn(true,true));
		}
		
		//Setting Black side
		cellBoard[0][0].placePiece(new Rook(true,false));
		cellBoard[0][1].placePiece(new Knight(true,false));
		cellBoard[0][2].placePiece(new Bishop(true,false));
		cellBoard[0][3].placePiece(new King(true,false));
		cellBoard[0][4].placePiece(new Queen(true,false));
		cellBoard[0][5].placePiece(new Bishop(true,false));
		cellBoard[0][6].placePiece(new Knight(true,false));
		cellBoard[0][7].placePiece(new Rook(true,false));
		
		//Setting White side
		cellBoard[7][0].placePiece(new Rook(true,true));
		cellBoard[7][1].placePiece(new Knight(true,true));
		cellBoard[7][2].placePiece(new Bishop(true,true));
		cellBoard[7][3].placePiece(new King(true,true));
		cellBoard[7][4].placePiece(new Queen(true,true));
		cellBoard[7][5].placePiece(new Bishop(true,true));
		cellBoard[7][6].placePiece(new Knight(true,true));
		cellBoard[7][7].placePiece(new Rook(true,true));
			
		whiteCaptured = new ArrayList<Piece>();
		blackCaptured = new ArrayList<Piece>();
	}
	
	/**
	 * Moves a piece
	 *
	 * @param rowfrom the row the piece is on
	 * @param columnfrom the column the piece is on
	 * @param rowto the row you want the piece to go to
	 * @param columnto the column you want the piece to go to
	 */
	public boolean movePiece(int rowfrom, int columnfrom, int rowto, int columnto) {

		if(cellBoard[rowfrom][columnfrom].getPiece().canMove(rowto,columnto)) {
			
			if(!cellBoard[rowto][columnto].isEmpty()) {
				if(cellBoard[rowto][columnto].getPiece().getColor()==true) {
					whiteCaptured.add(cellBoard[rowto][columnto].getPiece());
				} else if(cellBoard[rowto][columnto].getPiece().getColor()==false) {
					blackCaptured.add(cellBoard[rowto][columnto].getPiece());
				}
			}
			
			cellBoard[rowfrom][columnfrom].getPiece().move();
			cellBoard[rowto][columnto].placePiece(cellBoard[rowfrom][columnfrom].removePiece());
			
			epCheck(rowto, columnto);
			return true;
		} else {
			System.out.println("Something went wrong. " + cellBoard[rowfrom][columnfrom]);
			System.out.println("canMove is " + cellBoard[rowfrom][columnfrom].getPiece().canMove(rowto,columnto));
		}
		 
		return false;
		
	}
	
	private void epCheck(int row, int col) {
		Piece p = cellBoard[row][col].getPiece();
		if(p instanceof Pawn) {
			if(((Pawn) p).isEnpassante()) {
				try {
					if(cellBoard[row][col-1].getPiece() instanceof Pawn) {
						((Pawn) cellBoard[row][col-1].getPiece()).changeEP();
						System.out.println("Left!!!");
					}
					if(cellBoard[row][col+1].getPiece() instanceof Pawn) {
						((Pawn) cellBoard[row][col+1].getPiece()).changeEP();
						System.out.println("Right!!!");
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Too far!!!");
				}
			}
		}
		
	}
	
	public Cell getLocation(int row, int column) {
		return cellBoard[row][column];	
	}
	
	public King getWhiteKing() {
		King k = new King(false, true);
		try {
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(cellBoard[i][j].getPiece() instanceof King) {
						if(cellBoard[i][j].getPiece().getColor()==true) {
							k = (King) cellBoard[i][j].getPiece();
							return k;
						}
					}
					
				}
			}
		} catch(NullPointerException e) {
			k = new King(false, true);
		}
		return k;
	}
	
	public King getBlackKing() {
		King k = new King(false, false);
		try {
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(cellBoard[i][j].getPiece() instanceof King) {
						if(cellBoard[i][j].getPiece().getColor()==false) {
							k = (King) cellBoard[i][j].getPiece();
							return k;
						}
					}
					
				}
			}
		} catch(NullPointerException e) {
			k = new King(false, false);
		}
		return k;
	}
	
	@Override
	public String toString() {
		String list = "";
		for(int i=0;i<8;i++) {
			list+="|";
			for(int j=0;j<8;j++) {
				list += (cellBoard[i][j].getPiece().getPiece() + " | ");
			}
			list+="\n";
		}
		return list;
	}
	
	public String getColumn(int column) {
		String list = "";
		
		for(int i=0;i<8;i++) {
			list+="\n" + cellBoard[i][column];
		}
		
		return list;
	}
	
	public String getRow(int row) {
		String list = "";
		
		for(int i=0;i<8;i++) {
			list+="\n" + cellBoard[row][i];
		}
		
		return list;
	}
	
	public String columnRow() {
		String list = "";
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				list+="(" + i + "," + j + ")|";
			}
			list+="\n";
		}
		
		return list;
	}
	
	public boolean canCheck(int rowfrom, int colfrom) {
		
		for(int rows=0;rows<8;rows++) {
			for(int col=0;col<8;col++) {
				if(getLocation(rowfrom, colfrom).getPiece().canMove(rows, col) 
						&& getLocation(rows, col).getPiece() instanceof King
						&& !getLocation(rowfrom, colfrom).getPiece().equals(getLocation(rows, col).getPiece())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public ArrayList<Piece> getWhiteCaptured() {
		return whiteCaptured;
	}
	
	public ArrayList<Piece> getBlackCaptured() {
		return blackCaptured;
	}
	
	
	//------------------------All stuff for an AI below
	
	public TreeMap<Piece, Integer[]> getPossibleMoves() {
		ArrayList<Piece> pm = new ArrayList<Piece>();
		TreeMap<Piece, Integer[]> possibleMoves = new TreeMap<Piece, Integer[]>();
		ArrayList<Piece> currentPieces = new ArrayList<Piece>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				//if(!getLocation(i, j).isEmpty()) {
					currentPieces.add(getLocation(i, j).getPiece());
				//}
			}
		}
		
		for(Piece p : currentPieces) {
			for(int row=0;row<8;row++) {
				for(int col=0;col<8;col++) {
					if(p.canMove(row, col)) {
						pm.add(p);
						Integer[] q = new Integer[2];
						q[0] = row;
						q[1] = col;
						possibleMoves.put(p, q);
					}
				}
			}
		}
		
		System.out.println(possibleMoves.keySet() + "\t" + possibleMoves.entrySet());
		
		return possibleMoves;
	}
	
	
	
}
