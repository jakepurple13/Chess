package Piece;

public class Rook extends Piece {

	public Rook(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2656";
		} else if(color==false) {
			unicodePiece = "\u265C";
		}
	}
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		boolean moves = false;
		//moving
		if(b.getLocation(rowto, columnto).isEmpty() && lineEmpty(rowto, columnto)) {
			moves = true;
		//capturing
		} else if(!(b.getLocation(rowto, columnto).isEmpty()) && lineEmpty(rowto, columnto)) {
			if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				moves = true;
			}
		//castling	
		}// else if()
		
		return moves;
		
	}
	

}
