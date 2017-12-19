package Piece;

public class King extends Piece {
	
	int newRow;
	int newCol;
	boolean castled = false;
	
	public King(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2654";
		} else if(color==false) {
			unicodePiece = "\u265A";
		}
	}
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		boolean moves = false;
		//castling	
		if(!hasMoved && (columnto - c.getColumn() == 2 && c.getRow() == rowto)) {
			//kingside
			if(b.getLocation(rowto, c.getColumn()+1).isEmpty() || b.getLocation(rowto, c.getColumn()+2).isEmpty()) {
				moves = true;
			}
		} else if(!hasMoved && (c.getColumn() - columnto == 2 && c.getRow() == rowto)) {
			//queenside
			if(b.getLocation(rowto, c.getColumn()-1).isEmpty() || b.getLocation(rowto, c.getColumn()-2).isEmpty() || b.getLocation(rowto, c.getColumn()-3).isEmpty()) {
				moves = true;
			}
		//moving
		} else if(b.getLocation(rowto, columnto).isEmpty()) {
			if((c.getRow()+1 == rowto || c.getColumn()+1 == columnto) || (c.getRow()-1 == rowto || c.getColumn()-1 == columnto)) {
				moves = true;
			}
		//capturing
		} else if(!(b.getLocation(rowto, columnto).isEmpty())) {
			if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				if((c.getRow()+1 == rowto || c.getColumn()+1 == columnto) || (c.getRow()-1 == rowto || c.getColumn()-1 == columnto)) {
					moves = true;
				}
			}
		
		}
		
		newRow = rowto;
		newCol = columnto;
		
		return moves;
		
	}
	
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(newCol - c.getColumn() == 2 && c.getRow() == newRow) {
			//kingside
			if(b.getLocation(newRow, c.getColumn()+1).isEmpty() || b.getLocation(newRow, c.getColumn()+2).isEmpty()) {
				b.movePiece(c.getRow(), 7, c.getRow(), 5);
				castled = true;
			}
		} else if(c.getColumn() - newCol == 2 && c.getRow() == newRow) {
			//queenside
			if(b.getLocation(newRow, c.getColumn()-1).isEmpty() || b.getLocation(newRow, c.getColumn()-2).isEmpty() || b.getLocation(newRow, c.getColumn()-3).isEmpty()) {
				b.movePiece(c.getRow(), 0, c.getRow(), 3);
				castled = true;
			}
		}
		
		super.move();
		
	}
	
	public boolean hasCastled() {
		return castled;
	}
	

}
