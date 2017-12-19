package Piece;

public class Pawn extends Piece {
	
	boolean ep = false;
	boolean canEP = false;
	
	public Pawn(boolean exist, boolean color) {
		super(exist, color);
		// TODO Auto-generated constructor stub
		if(color==true) {
			unicodePiece = "\u2659";
		} else if(color==false) {
			unicodePiece = "\u265F";
		}
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		//put En passante movement code here after the check
		//Look at King move() code for an example
		
		super.move();
	}
	
	@Override
	public boolean canMove(int rowto, int columnto) {
		// TODO Auto-generated method stub
		boolean moves = false;
		//moving
		if(b.getLocation(rowto, columnto).isEmpty()) {
			//moving up two
			if(hasMoved==false && Math.abs(c.getRow()-rowto)==2) {
				moves=true;
				ep = true;
			//moving up one
			} else if((c.getRow()+1 == rowto && c.getColumn() == columnto) || (c.getRow()-1 == rowto && c.getColumn() == columnto)) {
					moves = true;
					ep = false;
			}
		//capturing
		} else if(!b.getLocation(rowto, columnto).isEmpty()) {
			if(!this.equals(b.getLocation(rowto, columnto).getPiece())) {
				if((c.getRow()+1 == rowto || c.getColumn()+1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()-1 == columnto)) {
					moves = true;
					ep = false;
				} else if((c.getRow()+1 == rowto || c.getColumn()-1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()+1 == columnto)) {
					moves = true;
					ep = false;
				}
			} /*else if(b.getLocation(rowto, columnto-1).getPiece() instanceof Pawn) {
				if(((Pawn) b.getLocation(rowto, columnto-1).getPiece()).canEP() && ((Pawn) b.getLocation(rowto, columnto-1).getPiece()).isEnpassante()) {
					if((c.getRow()+1 == rowto || c.getColumn()+1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()-1 == columnto)) {
						moves = true;
						ep = false;
					} else if((c.getRow()+1 == rowto || c.getColumn()-1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()+1 == columnto)) {
						moves = true;
						ep = false;
					}
				}
			} else if(b.getLocation(rowto, columnto+1).getPiece() instanceof Pawn) {
				if(((Pawn) b.getLocation(rowto, columnto+1).getPiece()).canEP() && ((Pawn) b.getLocation(rowto, columnto+1).getPiece()).isEnpassante()) {
					if((c.getRow()+1 == rowto || c.getColumn()+1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()-1 == columnto)) {
						moves = true;
						ep = false;
					} else if((c.getRow()+1 == rowto || c.getColumn()-1 == columnto) && (c.getRow()-1 == rowto || c.getColumn()+1 == columnto)) {
						moves = true;
						ep = false;
					}
				}
			}*/
		}
		
		return moves;
	}
	
	
	public boolean isEnpassante() {
		return ep;
	}
	
	public boolean canEP() {
		return canEP;
	}
	
	public void changeEP() {
		canEP = !canEP;
	}
	
	
}
