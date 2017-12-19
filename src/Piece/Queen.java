package Piece;

public class Queen extends Piece {

	public Queen(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2655";
		} else if(color==false) {
			unicodePiece = "\u265B";
		}
	}
	
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		
		if(b.getLocation(rowto, columnto).isEmpty() && (diagnolEmpty(rowto, columnto) || lineEmpty(rowto, columnto))) {
			return true;
		//capturing
		} else if(!b.getLocation(rowto, columnto).isEmpty() && (diagnolEmpty(rowto, columnto) || lineEmpty(rowto, columnto))) {
			if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				return true;
			}
		}
		
		return false;
	}

}
