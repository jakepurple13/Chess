package Piece;

public class Bishop extends Piece {

	public Bishop(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2657";
		} else if(color==false) {
			unicodePiece = "\u265D";
		}
	}
	
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		if(b.getLocation(rowto, columnto).isEmpty() && diagnolEmpty(rowto, columnto)) {
			return true;
		//capturing
		} else if(!b.getLocation(rowto, columnto).isEmpty() && diagnolEmpty(rowto, columnto)) {
			if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				return true;
			}
		}
		return false;
	}

}
