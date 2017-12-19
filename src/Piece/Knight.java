package Piece;

public class Knight extends Piece {

	public Knight(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2658";
		} else if(color==false) {
			unicodePiece = "\u265E";
		}
	}
	
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		if(Math.abs(rowto - c.getRow()) == 2 && Math.abs(columnto - c.getColumn()) == 1) {
			if(b.getLocation(rowto, columnto).isEmpty()) {
				return true;
			} else if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				return true;
			}
		}
		
		if(Math.abs(rowto - c.getRow()) == 1 && Math.abs(columnto - c.getColumn()) == 2) {
			if(b.getLocation(rowto, columnto).isEmpty()) {
				return true;
			} else if(!this.equals((b.getLocation(rowto, columnto).getPiece()))) {
				return true;
			}
		}
		
		return false;
	}

}
