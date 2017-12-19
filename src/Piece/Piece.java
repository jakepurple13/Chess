package Piece;
import Board.Board;
import Board.Cell;

public class Piece implements Comparable<Piece> {
	
	boolean exist;
	boolean color;
	Board b;
	String unicodePiece = "";//"\u2616"; //a shogun piece. Kind of here for the console version.
	Cell c;
	boolean hasMoved = false;
	
	public Piece(boolean exist, boolean color) {
		
		this.exist = exist;
		this.color = color;
		
	}
	
	public Piece(boolean color) {
		
		exist = true;
		this.color = color;
		
	}
	
	public Piece(boolean color, Cell c) {
		
		exist = true;
		this.color = color;
		this.c = c;
		
	}
	
	public void setCell(Cell c) {
		this.c = c;
	}
	
	public void setBoard(Board b) {
		this.b = b;
	}
	
	public void move() {
		//all pieces will have their own move method
		hasMoved = true;
	}
	
	public boolean canMove(int rowto, int columnto) {
		//System.out.println(c);
		//System.out.println(b.getLocation(rowto, columnto));
		return false;
	}
	
	public boolean exists() {
		return exist;
	}
	
	public void captured() {
		exist = false;
	}
	
	public String getPiece() {
		return unicodePiece;
	}
	
	@Override
	public String toString() {
		return "I am this piece " + unicodePiece;
	}

	@Override
	public int compareTo(Piece o) {
		// TODO Auto-generated method stub
		if(color==o.color) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public boolean rowEmpty(int rowto) {
		int offset;
		
		if(c.getRow() != rowto) {
			if(c.getRow() < rowto) {
				offset = 1;
			} else {
				offset = -1;
			}
			
			for(int x = c.getRow() + offset; x != rowto; x += offset) { 
				//Go from currentRow to newRow, and check every space
				if(!b.getLocation(x, c.getColumn()).isEmpty()) {
					//System.out.println("1 " + x);
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	
	public boolean columnEmpty(int columnto) {
		int offset;
		
		if(c.getColumn() != columnto) {
			if(c.getColumn() < columnto) {
				offset = 1;
			} else {
				offset = -1;
			}
			
			for(int x = c.getColumn() + offset; x != columnto; x += offset) {
				//Go from currentCol to newCol, and check every space
				if(!b.getLocation(c.getRow(), columnto).isEmpty()) {
					//System.out.println("2");
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	public boolean lineEmpty(int rowto, int columnto) {
		int offset;
		
		if(c.getRow() != rowto) {
			if(c.getRow() < rowto) {
				offset = 1;
			} else {
				offset = -1;
			}
			
			for(int x = c.getRow() + offset; x != rowto; x += offset) {
				//Go from currentRow to newRow, and check every space
				if(!b.getLocation(x, c.getColumn()).isEmpty()) {
					return false;
				}
			}
		}
		
		
		if(c.getColumn() != columnto) {
			if(c.getColumn() < columnto) {
				offset = 1;
			} else {
				offset = -1;
			}
			
			for(int x = c.getColumn() + offset; x != columnto; x += offset) {
				//Go from currentCol to newCol, and check every space
				if(!b.getLocation(c.getRow(), x).isEmpty()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean diagnolEmpty(int rowto, int columnto) {
		
		if(c.getRow() == rowto || c.getColumn() == columnto) {
			//Did not move diagonally
			return false;
		}
		
		if(Math.abs(rowto - c.getRow()) != Math.abs(columnto - c.getColumn())) {
			return false;
		}
		
		int rowOffset, colOffset;
		
		if(c.getRow() < rowto) {
			rowOffset = 1;
		} else {
			rowOffset = -1;
		}
		
		if(c.getColumn() < columnto) {
			colOffset = 1;
		} else {
			colOffset = -1;
		}
		
		int y = c.getColumn() + colOffset;
		
		for(int x = c.getRow() + rowOffset; x != rowto; x += rowOffset) {
			
			if(!b.getLocation(x, y).isEmpty()) {
				return false;
			}
			
			y += colOffset;
		}
		return true;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public boolean equals(Piece o) {
		// TODO Auto-generated method stub
		if(color==o.color) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getColor() {
		return color;
	}
	
	public boolean isPromotional() {
		if(this instanceof Pawn) {
			if(b.getLocation(0, c.getColumn()).getPiece() instanceof Pawn) {
				return true;
			} else if(b.getLocation(7, c.getColumn()).getPiece() instanceof Pawn) {
				return true;
			}
		} else {
			return false;
		}
		
		return false;
	}
	
}
