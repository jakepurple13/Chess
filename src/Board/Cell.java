package Board;

import Piece.King;
import Piece.Piece;

public class Cell {
	
	Piece p;
	Board b;
	String location;
	int row;
	int column;
	
	public Cell(int row, int column, Board b) {
		location = "row: " + row + " column: " + column;
		this.row = row;
		this.column = column;
		p = new Piece(true);
		this.b = b;
		this.p.setBoard(this.b);
		this.p.setCell(this);
	}
	
	public Cell(int row, int column, Piece p, Board b) {
		location = "row: " + row + " column: " + column;
		this.p = p;
		this.b = b;
		this.p.setBoard(this.b);
		this.p.setCell(this);
	}
	
	public Cell() {
		//location = "row: " + row + " column: " + column;
	}
	
	public void placePiece(Piece p) {
		this.p = p;
		this.p.setBoard(this.b);
		this.p.setCell(this);
	}
	
	public Piece getPiece() {
		return p;
	}
	
	public Piece removePiece() {
		Piece placeholder = p;
		p = new Piece(true);
		this.p.setCell(this);
		this.p.setBoard(this.b);
		return placeholder;
	}
	
	public boolean isEmpty() {
		if(p.getPiece().equals("\u2616") || p.getPiece().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return p + " on " + location;
	}

	public boolean canCheck() {
		
		for(int rows=0;rows<8;rows++) {
			for(int col=0;col<8;col++) {
				if(getPiece().canMove(rows, col) 
						&& b.getLocation(rows, col).getPiece() instanceof King
						&& !getPiece().equals(b.getLocation(rows, col).getPiece())) {
					//list+="(T)|";
					return true;
				}
				
			}
		}
		
		return false;
	}
	

}
